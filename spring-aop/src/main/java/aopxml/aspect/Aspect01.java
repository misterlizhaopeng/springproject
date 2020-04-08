package aopxml.aspect;

import aopxml.Ins.MyParamt;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.DeclareParents;


/*
1.xml 实现aop步骤：
    1. 创建连接点
    2. 创建切面
    3. 在xml文件中配置切面
    4. mt 测试

2.给通知传递参数
    1.在配置节点上，配置参数arg-names，和切点表达式里面的args()的参数，如下所示：
            <!--给通知传递参数;-->
            <!--注意：如果存在多个参数，arg-names的值和后面的args里面值必须一致，和连接点（或者说被切的方法）的方法参数名称可以不一致，只需保证一一对应即可;
                    ck:https://www.cnblogs.com/dand/p/10283247.html
            -->
            <aop:before method="before01" arg-names="aa,b"
                        pointcut="execution(* aopxml.Ins.AnimalImpl.passPamaTest(..))
                        and args(aa,b)"/>
    2.执行连接点方法，测试查看该前置通知方法before01 的的情况
    3.给通知传递参数遇到的问题（arg-names和切点表达式args()的参数不一致，老是出错）

3.引入
    1.在切面的配置文件节点aop:aspect下面配置如下：
        <!--引入：增强指定的bean；types-matching:被增强的类；增强接口：implement-interface；增强借口实现：default-impl-->
        <aop:declare-parents types-matching="aopxml.Ins.AnimalImpl+"
                                 implement-interface="aopxml.Ins.VerifierAnimal"
                                 default-impl="aopxml.Ins.VerifierAnimalImpl"/>
    2.把被增强的类实例强转增强接口，进行测试
    3.引入遇到的问题（由于把包引错，导致转换失败）

 */
public class Aspect01 {

    public void pointc(){

    }


    public void before(){
        System.out.println("[aspectj-xml] before...");
    }

    public void before01(MyParamt myParamt, String b){
        System.out.println("[aspectj-xml] before01 parameter pass...");
    }

    public void after(){
        System.out.println("[aspectj-xml] after...");
    }


    public void AfterReturning(){
        System.out.println("[aspectj-xml] AfterReturning...");
    }


    public void AfterThrowing(){
        System.out.println("[aspectj-xml] AfterThrowing...");
    }



//    public void Around(ProceedingJoinPoint jp){
//        System.out.println("aspectj Around before...");
//        try {
//            jp.proceed();
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//        }
//        System.out.println("aspectj Around after...");
//    }

}