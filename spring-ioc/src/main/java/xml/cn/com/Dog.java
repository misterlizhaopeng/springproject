package xml.cn.com;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;


public class Dog implements BeanNameAware,BeanFactoryAware,ApplicationContextAware,InitializingBean,DisposableBean{
	
	/**
	 * 得到自己的名字
	 */
	@Override
	public void setBeanName(String name) {
		System.out.println("执行接口BeanNameAware的方法setBeanName(String name)；我的 名字："+name);
	}
	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		System.out.println("执行接口BeanFactory的方法setBeanFactory(BeanFactory beanFactory)；我是bean："+beanFactory.getBean(Dog.class));
	}
	/**
	 * 让bean知道自己的容器
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		System.out.println("执行接口ApplicationContextAware的方法setApplicationContext(ApplicationContext applicationContext)；容器的Id："+applicationContext.getId());
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("执行接口InitializingBean的方法afterPropertiesSet()；属性设置完成了");
	}
	
	//自定义初始化方法（在AfterBeanPostProcess之前执行，在AfterBeanPostProcess之后销毁方法之前，为bean的生存期；）
	public void contomInit() {
		System.out.println("自定义初始化方法");
	}
	
	
	@Override
	public void destroy() throws Exception {
		System.out.println("执行接口DisposableBean的方法destroy()；我被销毁了");
	}

	
	//自定义销毁方法（在销毁方法之后执行）
	public void contomDestroy() {
		System.out.println("自定义销毁方法");
	}
}
