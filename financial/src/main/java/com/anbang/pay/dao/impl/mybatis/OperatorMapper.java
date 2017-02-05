package com.anbang.pay.dao.impl.mybatis;

import com.anbang.pay.commons.EntityDao;
import com.anbang.pay.entity.Operator;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ab053164wangyingling on 2017/1/21.
 */
@Mapper
@Repository("operatorMapper")
public interface OperatorMapper extends EntityDao{
    @Select("SELECT * FROM PAY_OPERATOR")
    @ResultType(Operator.class)
    List<Operator> findAll();

}
