package cn.com.bean;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class MyApplicationContextAware implements ApplicationContextAware{
	private ApplicationContext app;
	@Override
	public void setApplicationContext(ApplicationContext app) throws BeansException {
		this.app=app;
//		System.out.println("start...");
		System.out.println("当前容器的信息为："+app);
//		System.out.println("end...");
	}

}
