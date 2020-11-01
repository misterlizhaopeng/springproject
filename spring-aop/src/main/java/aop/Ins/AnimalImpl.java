package aop.Ins;

import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Component;

@Component
public class AnimalImpl implements  Animal {
    @Override
    public void output(String id) {
        System.out.println("proxy-method-继承接口的动物类...");
    }

    @Override
    public void passPamaTest(MyParamt myParamt,String second) {

        ((Animal)AopContext.currentProxy()).output("");
        // 注意的是jdk动态代理，类内部的方法之间的互相调用，不会触发spring代理，也就是平常说的代理对象的下动作的增强，需要通过
        //AopContext.currentProxy() 获取当前的代理，才可以使用增强功能；

        System.out.println("proxy-method-给切面的通知传递参数的实例方法：...");
    }
}
