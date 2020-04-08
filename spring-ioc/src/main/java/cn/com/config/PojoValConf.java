package cn.com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

import cn.com.bean.ValueTest;

@PropertySource(value = { "classpath:/info.properties" })
public class PojoValConf {
	
	@Bean
	public ValueTest valueTest() {
		
		return new ValueTest();
	}

}
