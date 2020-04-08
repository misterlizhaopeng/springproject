package cn.com.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

//@Component
public class School implements InitializingBean, DisposableBean {

	public School() {
		System.out.println("执行School构造函数...");
	}
	@Override
	public void destroy() throws Exception {
		System.out.println("go destroy");

	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("方法参数设置之后");


	}

}
