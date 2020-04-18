package xml.cn.com;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class TestInitializingBean implements InitializingBean, ApplicationContextAware {

    ApplicationContext ctx;

    @Override
    public void afterPropertiesSet() throws Exception {

        this.initStrategies(ctx);
    }

    public void initStrategies(ApplicationContext context) {
        System.out.println("方法afterPropertiesSet()执行之后....，有执行了自定义方法并且传过来容器对象" + context);
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ctx = applicationContext;
    }
}
