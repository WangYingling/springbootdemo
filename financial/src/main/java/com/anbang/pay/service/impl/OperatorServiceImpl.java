package com.anbang.pay.service.impl;

import com.anbang.pay.dao.OperatorDao;
import com.anbang.pay.dao.impl.mybatis.OperatorMapper;
import com.anbang.pay.entity.Operator;
import com.anbang.pay.service.OperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by yingling.wang on 2017/1/8.
 */
@Service
@Transactional
public class OperatorServiceImpl implements OperatorService{
    @Autowired
    private OperatorDao operatorDao;
   /*@Autowired
   private OperatorMapper operatorMapper;*/
    @Override
    public Operator login(Operator operator) {
        return null;
    }

    @Override
    public boolean checkUserNameUnique(Operator operator) {
        return false;
    }


    @Override
    public Operator createOperator(Operator operator) {
        return null;
    }

    @Override
    public Operator updateOperator(Operator operator, Map<String, Object> params) {
        return null;
    }

    @Override
    public List<Operator> findOperators(Operator operator) {
//        if(operator==null)
            return operatorDao.getAllByVO(operator);//查找全部用户信息
//        if(operator!=null)
//            return  operatorDao.getAllByVO(operator);
//        return null;
    }

    @Override
    public Operator findById(Long id) {
        return null;
    }
}
