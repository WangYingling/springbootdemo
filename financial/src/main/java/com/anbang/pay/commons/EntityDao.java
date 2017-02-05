package com.anbang.pay.commons;

import org.springframework.dao.DataAccessException;

import java.io.Serializable;
import java.util.List;

/**
 * 公用的DAO接口定义
 * Created by Administrator on 2017/1/1.
 */
public interface EntityDao <E,PK extends Serializable>{
    /**
     * 根据主键获取记录对象
     * @param id
     * @return
     * @throws DataAccessException
     */
     E getById(PK id) throws DataAccessException;

    /**
     * 根据主键删除记录对象
     * @param id
     * @return
     * @throws DataAccessException
     */
     int deleteById(PK id) throws DataAccessException;

    /**
     * 根据实体属性删除记录对象
     * @param entity
     * @return
     * @throws DataAccessException
     */
     int deleteByVO(E entity) throws DataAccessException;

    /**
     * 删除对象
     * @param entity
     * @return
     * @throws DataAccessException
     */
     E save(E entity) throws DataAccessException;

    /**
     * 更新对象
     * @param entity
     * @return
     * @throws DataAccessException
     */
     E update(E entity) throws DataAccessException;

    /**
     *  根据id检查是否插入或是更新数据
     * @param entity
     * @return
     * @throws DataAccessException
     */
     E saveOrUpdate(E entity) throws DataAccessException;

    /**
     * 判断记录对象是否唯一
     * @param entity
     * @param uniquePropertyNames
     * @return
     * @throws DataAccessException
     */
     boolean isUnique(E entity, String uniquePropertyNames) throws DataAccessException;

    /**
     * 判断对象是否唯一
     * @param entity
     * @return
     * @throws DataAccessException
     */
    boolean isUnique(E entity);
    /** 用于hibernate.flush() 有些dao实现不需要实现此类  */
     int flush() throws DataAccessException;

    /**
     * 查找全部记录
     * @return
     * @throws DataAccessException
     */
     List<E> getAll() throws DataAccessException;

    /**
     * 查询符合条件的记录
     * @param entity
     * @return
     */
     List<E> getAllByVO(E entity);

    /**
     * 查询符合条件的唯一记录
     * @param entity
     * @return
     */
     E getUniqueByVO(E entity);

    /**
     * 获取映射的命名空间
     * @return
     */
     String getIbatisMapperNamesapce();

}
