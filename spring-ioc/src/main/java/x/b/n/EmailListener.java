package x.b.n;

import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 3.邮件监听（异步执行）
 */
@Component
public class EmailListener implements ApplicationListener<OrderEvent> {


    @Override
    @Async
    public void onApplicationEvent(OrderEvent event) {
        System.out.println(Thread.currentThread() + "...邮件监听到..." + event.getMessage()+ "......" + event.getSource());
    }
}