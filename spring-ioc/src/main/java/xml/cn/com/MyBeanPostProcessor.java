package xml.cn.com;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * 只要bean的实例产生，就会执行before和after方法
 * @author  misterLip
 * @date   2020年3月25日
 */
public class MyBeanPostProcessor implements BeanPostProcessor {

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("BeanPostProcessor-BeforeInitialization：执行完【接口ApplicationContextAware】的方法之后，【接口InitializingBean】的方法之前执行");
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("BeanPostProcessor-AfterInitialization：执行完自定义初始化方法之后，【bean的生存期】之前执行");
		return bean;
	}

}
