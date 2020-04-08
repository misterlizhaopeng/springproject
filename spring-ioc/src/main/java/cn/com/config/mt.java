package cn.com.config;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;
import java.util.List;

public class mt {
	@Test
	public void componentTest() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(HouseConfig.class);
		String[] names = ctx.getBeanDefinitionNames();
		List<String> list = Arrays.asList(names);
		list.forEach(a->{
			System.out.println(a);
		});

//		House bean = ctx.getBean("house01",House.class);
//		System.out.println("---------->"+bean);
	}
}
