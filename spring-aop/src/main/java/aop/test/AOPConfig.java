package aop.test;

//import aop.Ins.UserService;
import aop.Ins.UserServiceImpl;
import aop.aspect.Aspect01;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


//2 开启切面
@EnableAspectJAutoProxy
@ComponentScan(basePackageClasses = UserServiceImpl.class)
public class AOPConfig {

    //3.创建一个切面实例
    @Bean
    public Aspect01 getAspect01(){
        return new Aspect01();
    }

}
