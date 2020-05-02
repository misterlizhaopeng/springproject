package spring.expr.lp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@ComponentScan(basePackageClasses = Role.class)
@PropertySource(value = "classpath:prop.properties")
public class AppConfig {
    @Bean
    public A getA() {
        return new A();
    }
}
