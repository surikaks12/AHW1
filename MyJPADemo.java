package com.company;


import com.mysql.cj.jdbc.MysqlDataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.*;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

public class MyJPADemo {
    private DataSource getDataSource() {
        final MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser("root");
        dataSource.setPassword("testing");
        dataSource.setUrl("jdbc:postgresql://localhost:3306/office");
        return dataSource;
    }

    private Properties getProperties() {
        final Properties properties = new Properties();
        properties.put( "hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect" );
        properties.put( "hibernate.connection.driver_class", "com.mysql.jdbc.Driver" );
        return properties;
    }

    private EntityManagerFactory entityManagerFactory(DataSource dataSource, Properties hibernateProperties ){
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("com/company");
        em.setJpaVendorAdapter( new HibernateJpaVendorAdapter() );
        em.setJpaProperties( hibernateProperties );
        em.setPersistenceUnitName( "demo-unit" );
        em.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        em.afterPropertiesSet();
        return em.getObject();
    }



    public static void main(String[] args) {
        MyJPADemo jpaDemo = new MyJPADemo();
        DataSource dataSource = jpaDemo.getDataSource();
        Properties properties = jpaDemo.getProperties();
        EntityManagerFactory entityManagerFactory = jpaDemo.entityManagerFactory(dataSource, properties);
        EntityManager em = entityManagerFactory.createEntityManager();
        PersistenceUnitUtil unitUtil = entityManagerFactory.getPersistenceUnitUtil();

    }
    private static void insertToColor(EntityManager em) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Department c = new Department();
        c.setName("IT");
        c.setId("2");
        em.persist(c);
        tx.commit();
    }

    private static void getDepartmentById(EntityManager em) {
        Query query = em.createQuery("select d from Department d left join fetch d.name ts where d.id = ?1");
        query.setParameter(1, "2");
        Department d = (Department) query.getSingleResult();
        System.out.println(d);
    }

    private static void addToJunctionTable1(EntityManager em) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Department d = new Department();
        p.setName("1th dep");
        Employee e = new Employee();
        //persist c first to get new id
        em.persist(e);
        e.setName("1th col");
        //build connection between c and p
        Department_Employee pc = new Department_Employee();
        pc.setEmp(e);
        pc.setDep(d);
        e.addDep_Emp(pc);

        em.persist(d);
        tx.commit();
    }


}