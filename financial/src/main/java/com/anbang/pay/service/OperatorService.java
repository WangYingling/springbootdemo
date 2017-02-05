package com.anbang.pay.service;

import com.anbang.pay.entity.Operator;

import java.util.List;
import java.util.Map;

/**
 *用户管理Service接口定义
 * Created by Administrator on 2017/1/8.
 */
public interface OperatorService {
    /**
     * 操作员登陆
     * @param operator
     * @return
     */
     Operator login(Operator operator);

    /**
     * 检查用户登录名是否唯一
     * @return
     */
     boolean checkUserNameUnique(Operator operator);

    /**
     * 新增用户
     * @param operator
     * @return
     */
     Operator createOperator(Operator operator);

    /**
     * 更新用户信息
      * @param operator
     * @param params
     * @return
     */
     Operator updateOperator(Operator operator, Map<String,Object> params);

    /**
     * 查找用户信息
     * @param operator
     * @return
     */
     List<Operator> findOperators(Operator operator);

    /**
     * 根据ID获取用户信息
     * @param id
     * @return
     */
    Operator findById(Long id);
}
