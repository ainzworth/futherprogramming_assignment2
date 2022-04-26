package com.example.assignment2.appConfig;

import com.example.assignment2.entity.*;
import com.example.assignment2.services.CarService;
import com.example.assignment2.services.DriverService;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class AppConfig {
    @Bean
    public Car car(){
        return new Car();
    }
    @Bean
    public Booking booking(){
        return new Booking();
    }
    @Bean
    public Customer customer(){
        return new Customer();
    }
    @Bean
    public Invoice invoice(){
        return new Invoice();
    }
    @Bean
    public Driver driver(){
        return new Driver();
    }

    // transactionManagement

    @Bean
    public DriverService driverService(){
        return new DriverService();
    }
    @Bean
    public CarService carService(){
        return new CarService();
    }

    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory){
        HibernateTransactionManager tx = new HibernateTransactionManager(sessionFactory);
        return tx;
    }
    @Bean
    public LocalSessionFactoryBean sessionFactoryBean(){
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.put("hibernate.show_sql", true);
        properties.put("hibernate.hbm2ddl.auto", "update");
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        //To use postgresql
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/demo");
        dataSource.setUsername("postgres");
        dataSource.setPassword("123");
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setHibernateProperties(properties);
        sessionFactoryBean.setPackagesToScan("entity");
        return sessionFactoryBean;
    }

}
