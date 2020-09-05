package aop.Ins;

import org.springframework.stereotype.Component;

@Component
public class AnimalImpl implements  Animal {
    @Override
    public void output(String id) {
        System.out.println("proxy-method-继承接口的动物类...");
    }

    @Override
    public void passPamaTest(MyParamt myParamt,String second) {
        System.out.println("proxy-method-给切面的通知传递参数的实例方法：...");
    }
}
