package com.anbang.pay.commons;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.support.DaoSupport;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/1/1.
 */
public abstract class BaseIBatis3Dao<E,PK extends Serializable> extends DaoSupport implements EntityDao<E,PK> {
    protected final Log log = LogFactory.getLog(getClass());

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    private SqlSessionTemplate sqlSessionTemplate;

    @Override
    protected void checkDaoConfig() throws IllegalArgumentException {
        Assert.notNull(sqlSessionFactory,"sqlSessionFactory must be not null");
    }

    public SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
        this.sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
    }

    public SqlSessionTemplate getSqlSessionTemplate() {
        log.info(sqlSessionFactory+",template"+this.sqlSessionTemplate);
        if(this.sqlSessionTemplate==null){
            this.sqlSessionTemplate=new SqlSessionTemplate(sqlSessionFactory);
        }
        return sqlSessionTemplate;
    }

    @Override
    public E getById(PK primaryKey) {
        E object = (E)getSqlSessionTemplate().selectOne(getFindByPrimaryKeyStatement(), primaryKey);
        return object;
    }

    @Override
    public int deleteByVO(E entity) throws DataAccessException {
        int affectCount= getSqlSessionTemplate().delete(getDeleteByVoStatement(), entity);
        return affectCount;

    }

    @Override
    public int deleteById(PK id) {
        int affectCount = getSqlSessionTemplate().delete(getDeleteStatement(), id);
        return affectCount;
    }

    @Override
    public E save(E entity) {
        prepareObjectForSaveOrUpdate(entity);
        int affectCount = getSqlSessionTemplate().insert(getInsertStatement(), entity);
        return entity;
    }

    @Override
    public E update(E entity) {
        prepareObjectForSaveOrUpdate(entity);
        int affectCount = getSqlSessionTemplate().update(getUpdateStatement(), entity);
        return entity;
    }



    /*public E getObject(String statement, E entity)  {
        E object = (E)getSqlSessionTemplate().getObject(statement, entity);
        return object;
    }*/
    @Override
    public boolean isUnique(E entity) {
       List<E> list= getSqlSessionTemplate().selectList(getAllByVoStatement(),entity);
////        E object = (E)getSqlSessionTemplate().selectOne(getFindByUNKeyStatement(), entity);
//        return object;
//        throw new UnsupportedOperationException();
        if(list==null||list.size()<2)
            return true;
        else return false;
    }

    @Override
    public int flush() {
        /// TO DO MYBATIS ignore
        return 0;
    }

    @Override
    public List<E> getAll() throws DataAccessException {
        List<E> list= getSqlSessionTemplate().selectList(getAllByVoStatement(),null);
        return list;
    }

    @Override
    public List<E> getAllByVO(E entity) {
        List<E> list= getSqlSessionTemplate().selectList(getAllByVoStatement(),entity);
        return list;
    }

    @Override
    public E getUniqueByVO(E entity) {
       E one= (E) getSqlSessionTemplate().selectOne(getAllByVoStatement(),entity);
        return  one;


    }


    /**
     * 用于子类覆盖,在insert,update之前调用
     * @param o
     */
    protected void prepareObjectForSaveOrUpdate(E o) {
    }
    /*@Override
    public String getIbatisMapperNamesapce() {
        throw new RuntimeException("not yet implement");
    }*/

    public String getFindByPrimaryKeyStatement() {
        return getIbatisMapperNamesapce()+".getById";
    }

    public String getInsertStatement() {
        return getIbatisMapperNamesapce()+".insert";
    }

    public String getUpdateStatement() {
        return getIbatisMapperNamesapce()+".update";
    }

    public String getDeleteStatement() {
        return getIbatisMapperNamesapce()+".delete";
    }

    public String getCountStatementForPaging(String statementName) {
        return statementName +".count";
    }



    public String getDeleteByVoStatement() {
        return getIbatisMapperNamesapce()+".deleteByVO";
    }

    /**
     * 根据条件查询
     * @return
     */
    public String getAllByVoStatement(){return getIbatisMapperNamesapce()+".getByVO";}
 /*public String getResultByParameter() {
        return getIbatisMapperNamesapce()+".getById";
    }*/
    /*protected Page pageQuery(String statementName, PageRequest pageRequest) {
        return pageQuery(getSqlSessionTemplate(),statementName,getCountStatementForPaging(statementName),pageRequest);
    }*/

    /*根据属性查询list*/
   /* protected List listQuery(String statementName, PageRequest pageRequest) {
        List list = sqlSessionTemplate.selectList(statementName, pageRequest);
        return list;
    }*/

    /*public static Page pageQuery(SqlSessionTemplate sqlSessionTemplate,String statementName,String countStatementName, PageRequest pageRequest) {

        Number totalCount = (Number) sqlSessionTemplate.selectOne(countStatementName,pageRequest);
        if(totalCount == null || totalCount.longValue() <= 0) {
            return new Page(pageRequest,0);
        }

        Page page = new Page(pageRequest,totalCount.intValue());

        //其它分页参数,用于不喜欢或是因为兼容性而不使用方言(Dialect)的分页用户使用. 与getSqlMapClientTemplate().queryForList(statementName, parameterObject)配合使用
        Map filters = new HashMap();
        filters.put("offset", page.getFirstResult());
        filters.put("pageSize", page.getPageSize());
        filters.put("lastRows", page.getFirstResult() + page.getPageSize());
        filters.put("sortColumns", pageRequest.getSortColumns());

        Map parameterObject = PropertyUtils.describe(pageRequest);
        filters.putAll(parameterObject);

        List list = sqlSessionTemplate.selectList(statementName, filters,page.getFirstResult(),page.getPageSize());
        page.setResult(list);
        return page;
    }
*/



    public static class SqlSessionTemplate {

        SqlSessionFactory sqlSessionFactory;

        public SqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
            this.sqlSessionFactory = sqlSessionFactory;
        }

        public Object execute(SqlSessionCallback action)  {
            SqlSession session = null;
            try {
                session = sqlSessionFactory.openSession();
                Object result = action.doInSession(session);
                return result;
            }finally {
                if(session != null) session.close();
            }
        }

        public Object getObject(final String statement,final Object parameter) {
            return execute(new SqlSessionCallback() {
                @Override
                public Object doInSession(SqlSession session) {
                    return session.selectOne(statement, parameter);
                }
            });
        }

        public Object selectOne(final String statement,final Object parameter) {
            return execute(new SqlSessionCallback() {
                @Override
                public Object doInSession(SqlSession session) {
                    return session.selectOne(statement, parameter);
                }
            });
        }

        public List selectList(final String statement,final Object parameter,final int offset,final int limit) {
            return (List)execute(new SqlSessionCallback() {
                @Override
                public Object doInSession(SqlSession session) {
                    return session.selectList(statement, parameter, new RowBounds(offset,limit));
                }
            });
        }

        public List selectList(final String statement,final Object parameter) {
            return (List)execute(new SqlSessionCallback() {
                @Override
                public Object doInSession(SqlSession session) {
                    return session.selectList(statement, parameter);
                }
            });
        }

        public int delete(final String statement,final Object parameter) {
            return (Integer)execute(new SqlSessionCallback() {
                @Override
                public Object doInSession(SqlSession session) {
                    return session.delete(statement, parameter);
                }
            });
        }

        public int update(final String statement,final Object parameter) {
            return (Integer)execute(new SqlSessionCallback() {
                @Override
                public Object doInSession(SqlSession session) {
                    return session.update(statement, parameter);
                }
            });
        }

        public int insert(final String statement,final Object parameter) {
            return (Integer)execute(new SqlSessionCallback() {
                @Override
                public Object doInSession(SqlSession session) {
                    return session.insert(statement, parameter);
                }
            });
        }
    }

    public static interface SqlSessionCallback {

        public Object doInSession(SqlSession session);

    }


}