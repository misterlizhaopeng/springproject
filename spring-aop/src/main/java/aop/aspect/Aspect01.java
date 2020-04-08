package aop.aspect;

import aop.Ins.MyParamt;
import aop.Ins.VerifierAnimal;
import aop.Ins.VerifierAnimalImpl;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;



/*
Java注解实现spring方式的aop

顺延的技术点：
    反射、动态代理、拦截器 + 动态代理、aop；

aop 实现种类：
    实现aop的技术不仅仅包含spring，其他技术也存在，对于spring实现的aop技术有多种，当前@AspectJ方式是最常用的，
    其他的还有【AspectJ注入切面】、【XML配置AOP】、【使用ProxyFactoryBean和对应的接口实现AOP】

spring aop 如何选择哪种代理方式
    被代理的对象有接口默认使用jdk代理，否则cglib；

aop相关概念：
    切面：当前类就是一个切面，类似于一个拦截器
    通知：该切面中的方法
    连接点：被切的方法
    切点：@Pointcut，意思是被切对象的条件是什么，有切点表达式表示
    织入：生成代理对象，把切面内容放入到流程中，这里可以考虑一下jdk动态代理的bind和invoke两步方法；
    引入：为指定的类增加类和方法，增强指定bean 的功能（分两步可以对一个对象进行引入别的接口，下面有介绍）

实现aop的步骤：
    1.定义连接点（真实对象的执行方法）
    2.定义切面（@AspectJ标注）
    3.在spring容器的上@EnableAspectJAutoProxy，并且实例化切面的 bean
    最后，获取spring容器，拿到真是对象并执行被切面切到的方法（连接点）查看执行顺序

给切面通知传递参数：
    1.在切点处设置传进来的参数，如：
        //前置通知：在执行被代理对象的方法之前执行
        @Before("execution(* aop.Ins.*.*(..))&& args(a,b)")
        public void before(MyParamt a,String b){
            System.out.println("我是切面：aspectj 中的通知，从调用方法中传过来的name参数是:"+a.getName()+",第二个参数："+b);
            System.out.println("aspectj before...");
        }
        注意：对于连接点传递的参数不能匹配args，则不执行切面的通知
    2.在连接点处设置对应的方法，如：
        @Override
        public void passPamaTest(MyParamt myParamt,String second) {
            System.out.println("给切面的通知传递参数的实例方法：...");
        }
    3.测试即可

* */


//1 定义切面
@Aspect
public class Aspect01 {

    // 引入步骤：1.引入此接口 2.测试；
    // value:表示为value指定的类进行增强，引入一个接口VerifierAnimal，"+"表示AnimalImpl的所有子类；defaultImpl：表示当前接口的默认实现类；
    @DeclareParents(value = "aop.Ins.AnimalImpl+",defaultImpl = VerifierAnimalImpl.class)//aop.Ins.UserServiceImpl+,AnimalImpl
    public VerifierAnimal verifierAnimal;

    //切点：
    //@Pointcut("execution(* aspect.Ins.*.*.(..))")  错误的配置方法
    @Pointcut("execution(* aop.Ins.*.*(..))")
    public void pointc(){

    }

    //前置通知：在执行被代理对象的方法之前执行
    @Before("execution(* aop.Ins.*.*(..))&& args(a,b)")
    public void before(MyParamt a,String b){
        System.out.println("我是切面：aspectj 中的通知，从调用方法中传过来的name参数是:"+a.getName()+",第二个参数："+b);
        System.out.println("aspectj before...");
    }

    //前置通知：在执行被代理对象的方法之前执行
    @Before("execution(* aop.Ins.*.*(..))&& args(a,b)")
    public void b(String a,String b){
        System.out.println("->aspectj before...");
    }

    //后置通知：在执行被代理对象的方法之后执行
    @After("pointc()")
    public void after(){
        System.out.println("aspectj after...");
    }

    //返回通知：在执行被代理对象的方法之前执行
    @AfterReturning("pointc()")
    public void AfterReturning(){
        System.out.println("aspectj AfterReturning...");
    }

    @AfterThrowing("pointc()")
    public void AfterThrowing(){
        System.out.println("aspectj AfterThrowing...");
    }


    //@Around("pointc()")
    public void Around(ProceedingJoinPoint jp){
        System.out.println("aspectj Around before...");
        try {
            jp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("aspectj Around after...");
    }

}