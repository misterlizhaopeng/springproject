package mybatis_spring_redis.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

@Configuration
@ComponentScan(basePackages = "mybatis_spring_redis")
@PropertySource(value = {"classpath:/db.properties"})
// 使用事务驱动管理器
@EnableTransactionManagement
// 实现接口TransactionManagementConfigurer，这样可以配置注解驱动事务
public class MapperConfig implements TransactionManagementConfigurer {


    //此处拿不到配置信息的值，所以把数据源放到ConfigDB文件配置了，，不清楚什么原因，先记下，注意！！！！
//    @Value("${jdbc.url}")
//    private String url;
//    @Value("${jdbc.driver}")
//    private String driver;
//    @Value("${jdbc.username}")
//    private String username;
//    @Value("${jdbc.psw}")
//    private String psw;



    @Autowired
    Environment env;

/* <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
        <property name="jdbcUrl" value="${jdbc.url}"></property>
        <property name="driverClass" value="${jdbc.driver}"></property>
        <property name="user" value="${jdbc.username}"></property>
        <property name="password" value="${jdbc.psw}"></property>
    </bean>*/

  /*  *//**
     * 配置数据库
     *
     * @return 数据连接池
     *//*
    @Bean
    public DataSource dataSource() throws PropertyVetoException {

        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setJdbcUrl(this.url);
        dataSource.setDriverClass(this.driver);
        dataSource.setUser(this.username);
        dataSource.setPassword(this.psw);
        return dataSource;
    }*/


    /* <!--配置sqlSessionFactory,集成MyBatis -->
    <bean  id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean" >
        <property name="dataSource" ref="dataSource"></property>
        <property name="configLocation" value="classpath:mybatis-config.xml"></property>
    </bean>*/

    /**
     * * 配置SqlSessionFactoryBean
     *
     * @return SqlSessionFactoryBean
     */
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactoryBean getSqlSessionFactory(@Autowired DataSource dataSource) {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        // 配置MyBatis配置文件
        Resource resource = new ClassPathResource("mybatis-config.xml");
        sqlSessionFactoryBean.setConfigLocation(resource);
        return sqlSessionFactoryBean;
    }

    /* <bean id="mapperScannerConfigurer" class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <!--按指定包下进行扫描，会逐层深入扫描，对于多个包的扫描，可以用半角分开-->
        <property name="basePackage" value="mybatisSpringRedis"></property>
        <!--指定标注才被扫描为mapper-->
        <property name="annotationClass" value="org.springframework.stereotype.Repository"></property>
        <!--sqlSessionFactoryBeanName代表SqlSessionFactory，如果 sqlSessionTemplateBeanName 存在，sqlSessionFactoryBeanName 无效；-->
        <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory"></property>
    </bean>*/

    /**
     * * 通过自动扫描，发现MyBatis Mapper接口
     *
     * @return Mapper扫描器
     */
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer msc = new MapperScannerConfigurer();
        msc.setBasePackage("mybatis_spring_redis");
        msc.setAnnotationClass(Repository.class);
        msc.setSqlSessionFactoryBeanName("sqlSessionFactory");
        return msc;
    }



    /*
     * 事物管理器
     *
     * */
    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager(@Autowired DataSource dataSource) {
        DataSourceTransactionManager platformTransactionManager = new DataSourceTransactionManager();
        platformTransactionManager.setDataSource(dataSource);
        return platformTransactionManager;
    }


/* <!--事务管理器-->
    <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <property name="dataSource" ref="dataSource"></property>
    </bean>

    <!--开启注解事务-->
    <tx:annotation-driven transaction-manager="transactionManager"/>

    */

    @Autowired
    private PlatformTransactionManager platformTransactionManager;
    /**
     * 实现接口方法，注册注解事务，当@Transactional 使用的时候产生数据库事务
     */
    @Override
    //@Bean(name = "annotationDrivenTransactionManager")
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return platformTransactionManager;
    }
}
