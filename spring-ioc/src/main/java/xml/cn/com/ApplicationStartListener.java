package xml.cn.com;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;



public class ApplicationStartListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        //初始化root容器之后，会初始化对应的子容器，此处判断没有子容器，跳过即可；

        if(event.getApplicationContext().getParent() == null){
            return;
        }


        System.out.println("---------------->容器初始化完成的操作");
        ApplicationContext ctx= event.getApplicationContext();
        System.out.println("---------------->容器是："+ctx);
        System.out.println("---------------->容器的父容器是："+ctx.getParent());
    }
}
