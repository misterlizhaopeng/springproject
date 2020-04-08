package cn.com.config;

import org.springframework.beans.factory.FactoryBean;

import cn.com.bean.Blue;

public class MyFactoryBean implements FactoryBean<Blue> {

	@Override
	public Blue getObject() throws Exception {
		// TODO Auto-generated method stub
		return new Blue();
	}

	@Override
	public Class<?> getObjectType() {
		// TODO Auto-generated method stub
		return Blue.class;
	}

	@Override
	public boolean isSingleton() {
		// TODO Auto-generated method stub
		return false;
	}

}
