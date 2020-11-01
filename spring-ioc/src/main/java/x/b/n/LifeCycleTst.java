package x.b.n;

import org.springframework.context.Lifecycle;

/**
 * @ClassName lp.annotation.com.LifeCycleTst
 * @Deacription TODO   Lifecycle生命周期: 常规的LifeCycle接口只是在容器上下文显式的调用start()/stop()方法时，才会去回调LifeCycle的实现类的start stop方法逻辑,并不意味着在上下文刷新时自动启动.然后看例子：SmartLifeCycleTst
 * @Author LP
 * @Date 2020/11/1 19:37
 * @Version 1.0
 **/
public class LifeCycleTst implements Lifecycle {
    //
    private volatile boolean running = false;

    public LifeCycleTst(){
        System.out.println("LifeCycleTst.LifeCycleTst()");
    }
    @Override
    public void start() {

        System.out.println("LifeCycleTst.start()");
        running = true;
    }

    @Override
    public void stop() {
        System.out.println("LifeCycleTst.stop()");
        running = false;
    }

    @Override
    public boolean isRunning() {
        return running;
    }
}

