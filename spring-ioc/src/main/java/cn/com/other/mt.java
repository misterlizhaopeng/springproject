package cn.com.other;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class mt {
	@Test
	public void componentTest() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(HouseConfig.class);
		String[] names = ctx.getBeanDefinitionNames();
		for (String ss : names) {
			System.out.println(ss);
		}
		
		
//		House bean = ctx.getBean("house01",House.class);
//		System.out.println("---------->"+bean);
	}
}
