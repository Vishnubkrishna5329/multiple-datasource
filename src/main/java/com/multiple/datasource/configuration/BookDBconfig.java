package com.multiple.datasource.configuration;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "bookEntityFactoryManager", 
	basePackages = {"com.multiple.datasource.book.repo"},
	transactionManagerRef = "bookTransactionManager")
public class BookDBconfig {

	@Bean(name = "bookDataSource")
	@ConfigurationProperties(prefix = "spring.book.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name="bookEntityFactoryManager")
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(@Qualifier("bookDataSource") DataSource ds,
			EntityManagerFactoryBuilder builder) {
		
		Map<String,Object> properties= new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", "update");
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL55Dialect");
		return builder.dataSource(ds)
				.packages("com.multiple.datasource.book.entity")
				.properties(properties)
				.persistenceUnit("Book")
				.build();
	}
	
	@Bean(name="bookTransactionManager")
	public PlatformTransactionManager transactionManager(@Qualifier("bookEntityFactoryManager") EntityManagerFactory entityManager) {
		
		return new JpaTransactionManager(entityManager);
	}
}
