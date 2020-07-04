package com.allenliu.news.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * 核心业务数据源配置
 *
 * @author Allen Liu
 */
@Configuration
@Primary
@MapperScan(basePackages = CoreDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "coreSqlSessionFactory")
public class CoreDataSourceConfig {

    static final String PACKAGE = "com.allenliu.news.mapper";

    @Bean(name = "coreDataSource")
    @Primary
    @ConfigurationProperties(prefix = "core.db")
    public DataSource coreDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        return dataSource;
    }

    @Bean(name = "coreTransactionManager")
    @Primary
    public DataSourceTransactionManager coreTransactionManager() {
        return new DataSourceTransactionManager(coreDataSource());
    }

    @Bean(name = "coreSqlSessionFactory")
    @Primary
    public SqlSessionFactory coreSqlSessionFactory(@Qualifier("coreDataSource") DataSource coreDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(coreDataSource);

        sessionFactory.setTypeAliasesPackage(PACKAGE);
        // 分页插件

        PageInterceptor pageHelper = new PageInterceptor();
        Properties properties = new Properties();
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("returnPageInfo", "check");
        properties.setProperty("params", "pageNum=pageNum;pageSize=pageSize;count=countSql");
        pageHelper.setProperties(properties);

        // 添加插件
        sessionFactory.setPlugins(new Interceptor[]{pageHelper});

        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setUseGeneratedKeys(true);
        sessionFactory.setConfiguration(configuration);

        // 添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            sessionFactory.setMapperLocations(resolver.getResources("classpath*:mapper/*Mapper.xml"));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return sessionFactory.getObject();
    }
}
