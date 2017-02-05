package com.anbang.pay.dao.impl.mybatis;

import com.anbang.pay.commons.BaseIBatis3Dao;
import com.anbang.pay.dao.OperatorDao;
import com.anbang.pay.entity.Operator;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户实体对象的DAO实现
 * 对应表PAY_OPEREATORS
 * Created by YINGLING.WANG on 2017/1/1.
 */
@Repository
public class OperatorDaoImpl extends BaseIBatis3Dao<Operator,Long> implements OperatorDao
{

    /**
     * 保存或更新对象
     *
     * @param entity
     * @return
     * @throws DataAccessException
     */
    @Override
    public Operator saveOrUpdate(Operator entity) throws DataAccessException {
//        if(entity.getId()==null)
//            return save(entity);
//        else
//            return update(entity);
        return null;
    }

        @Override
    public boolean isUnique(Operator entity, String uniquePropertyNames) throws DataAccessException {
        return false;
    }

        @Override
    public String getIbatisMapperNamesapce() {
        return "Operator";
    }
}
