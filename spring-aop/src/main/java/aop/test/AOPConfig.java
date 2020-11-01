package aop.test;

//import aop.Ins.UserService;
import aop.Ins.UserServiceImpl;
import aop.aspect.Aspect01;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 @EnableAspectJAutoProxy 原理分析：

 该注解上面有spring的注册组件的注解：import
    @Import(AspectJAutoProxyRegistrar.class)
        通过方法(方法的名称大致意思为注册或者升级的意思)：registerOrEscalateApcAsRequired(AnnotationAwareAspectJAutoProxyCreator.class, registry, source) 注册一个bean 的【定义信息 BeanDefinition】，名字为internalAutoProxyCreator，如下代码：
        org.springframework.aop.config.internalAutoProxyCreator = AnnotationAwareAspectJAutoProxyCreator；

    对于bean ：AnnotationAwareAspectJAutoProxyCreator，通过源码分析，其实现了 BeanPostProcessor 、BeanFactoryAware 接口，如下代码所示：
        【
            AbstractAutoProxyCreator extends ProxyProcessorSupport implements SmartInstantiationAwareBeanPostProcessor, BeanFactoryAware；
            接口：SmartInstantiationAwareBeanPostProcessor extends InstantiationAwareBeanPostProcessor
            接口：InstantiationAwareBeanPostProcessor extends BeanPostProcessor
        】

AnnotationAwareAspectJAutoProxyCreator
 ->AspectJAwareAdvisorAutoProxyCreator
    ->AbstractAdvisorAutoProxyCreator
        ->AbstractAutoProxyCreator
            extends ProxyProcessorSupport implements SmartInstantiationAwareBeanPostProcessor, BeanFactoryAware


初始化容器的-流程：




 */

//2 开启切面
@EnableAspectJAutoProxy
@ComponentScan(basePackageClasses = UserServiceImpl.class)
public class AOPConfig {

    //3.创建一个切面实例
    @Bean
    public Aspect01 getAspect01_go(){
        return new Aspect01();
    }

}
