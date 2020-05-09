package lp.annotation.com;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import lp.annotation.c.A;

/*
 *
 * @ComponentScan 解释：默认扫描当前包的组件和子包的组件，如果设置了basePackages，会扫描对应的包和子包的所有的组件；
 * basePackageClasses 解释：如果指定了该值所指的类，那么spring会扫描对应该类所在包以及子包的所有组件
 *
 * */
@ComponentScan(basePackageClasses = {House.class})//,A.class
//spring 注解加载外部资源文件，就使用这个注解，然后spring容器可以通过代码【ctx.getEnvironment().getProperty("target")】获取测试；
@PropertySource(value = {"classpath:/info.properties"})//注解@Value如果想获取配置文件（properties文件）中某一项的值，只需要此处引用一下即可，类属性便可以获取指定的值；
/*
 * 注解@ImportResource 作用：在注解装配bean的情况下结合xml的装配bean的方式，这时组件也可以把xml中的bean通过注解@Autowired进行自动装配到想要的地方；
 * 这样就能实现了注解装配bean和xml装配bean的混合使用；
 *
 *
 * */
@ImportResource(locations = {"classpath:/bean2.xml", "classpath:bean3.xml"})
public class HouseConfig {


    @Value("${target}")
    private String target;

    @Bean
    public A a() {
        System.out.println("------------------------------------------->" + target);
        return new A();
    }
}
