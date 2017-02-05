package com.anbang.pay.configs.db;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * MyBatis配置类
 * Created by ab053164wangyingling on 2017/1/17.
 */
@Configuration
@EnableTransactionManagement//事务管理机制启用
public class MyBatisConfig {
    //注入数据源
    @Resource(name = "dataSource")
    DataSource dataSource;
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sessionFactory(){
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
//        bean.setTypeAliasesPackage("cn.mrdear.entity");
        try {
            return bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
    /**
     * 用于实际查询的sql工具,传统dao开发形式可以使用这个,基于mapper代理则不需要注入
     * @param sqlSessionFactory
     * @return
     */
    @Bean(name = "sqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
    /**
     * 事务管理,具体使用在service层加入@Transactional注解
     */
    @Bean(name = "transactionManager")
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
