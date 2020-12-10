package com.his.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;

@MapperScan(basePackages = "com.his.mapper")
//@ComponentScan(basePackages = "com.his.service")
@PropertySource("classpath:jdbc.properties")
@Configuration
@EnableTransactionManagement
public class SpringConfig {

    @Autowired
    private Environment env;

    @Bean
    public DataSource dataSource() {
        DruidDataSource source = new DruidDataSource();
        source.setUrl(env.getProperty("jdbc.url"));
        source.setUsername(env.getProperty("jdbc.username"));
        source.setDriverClassName(env.getProperty("jdbc.driverClassName"));
        source.setPassword(env.getProperty("jdbc.password"));
        return source;
    }

    @Bean
    public PathMatchingResourcePatternResolver resolver() {
        return new PathMatchingResourcePatternResolver();
    }

/*
    @Bean
    public MybatisSqlSessionFactoryBean sqlSessionFactoryBean() throws IOException {
        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        sqlSessionFactoryBean.setConfigLocation(resolver().getResource("classpath:mybatis-config.xml"));
        sqlSessionFactoryBean.setMapperLocations(resolver().getResources("classpath:mapper/*Mapper.xml"));
        sqlSessionFactoryBean.setGlobalConfig(globalConfig());
        sqlSessionFactoryBean.setPlugins(mybatisPlusInterceptor());
        return sqlSessionFactoryBean;
    }
*/

    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager() {
        DataSourceTransactionManager manager = new DataSourceTransactionManager();
        manager.setDataSource(dataSource());
        return manager;
    }

    @Bean
    public GlobalConfig globalConfig() {
        GlobalConfig config = new GlobalConfig();
        config.setDbConfig(defaultDbConfig());
        return config;
    }

    @Bean
    public GlobalConfig.DbConfig defaultDbConfig() {
        GlobalConfig.DbConfig dbConfig = new GlobalConfig.DbConfig();
        dbConfig.setIdType(IdType.AUTO);
        return dbConfig;
    }

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        return interceptor;
    }
}
