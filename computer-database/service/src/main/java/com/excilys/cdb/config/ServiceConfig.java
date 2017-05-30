package com.excilys.cdb.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = { "com.excilys.cdb.service", "com.excilys.cdb.mapper" })
@Import(DataConfig.class)
public class ServiceConfig {

}
