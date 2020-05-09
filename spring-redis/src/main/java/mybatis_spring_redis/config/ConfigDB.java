package mybatis_spring_redis.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

@Configuration
public class ConfigDB {
    @Autowired
    Environment env;


    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.driver}")
    private String driver;
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.psw}")
    private String psw;

/* <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
        <property name="jdbcUrl" value="${jdbc.url}"></property>
        <property name="driverClass" value="${jdbc.driver}"></property>
        <property name="user" value="${jdbc.username}"></property>
        <property name="password" value="${jdbc.psw}"></property>
    </bean>*/

    /**
     * 配置数据库
     *
     * @return 数据连接池
     */
    @Bean
    public DataSource dataSource() throws PropertyVetoException {

        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setJdbcUrl(this.url);
        dataSource.setDriverClass(this.driver);
        dataSource.setUser(this.username);
        dataSource.setPassword(this.psw);
        return dataSource;
    }
}
