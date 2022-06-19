package com.ttipl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableJpaRepositories(basePackages = { "com.ttipl.repository" })
@EntityScan("com.ttipl.pojo")
@EnableTransactionManagement
@EnableAspectJAutoProxy
@SpringBootApplication
@ComponentScan("com.ttipl")
public class CbtOnlineExamAdminApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(CbtOnlineExamAdminApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(CbtOnlineExamAdminApplication.class);
	}
}
