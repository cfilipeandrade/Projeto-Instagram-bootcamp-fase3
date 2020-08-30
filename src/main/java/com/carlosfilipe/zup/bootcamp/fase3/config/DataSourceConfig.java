package com.carlosfilipe.zup.bootcamp.fase3.config;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource dataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url("jdbc:mysql://127.0.0.1:3306/instagram");
        dataSourceBuilder.driverClassName("com.mysql.jdbc.Driver");
        dataSourceBuilder.username(Config.getConfig("mysql.nomeUsuario"));
        dataSourceBuilder.password(Config.getConfig("mysql.senha"));
        return (DataSource) dataSourceBuilder.build();
    }

    
}