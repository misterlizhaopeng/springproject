package spring.expr.lp;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MT {
	@Test
	public void testExprOne() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		
		String[] names = ctx.getBeanDefinitionNames();
		if (names.length<=0) {
			System.out.println("spring容器中没有对应的bean");
		}
		List<String> asList = Arrays.asList(names);
		asList.forEach(a->{
			System.out.println(a);
		});
		
		System.out.println("------------->");
		Role bean = ctx.getBean(Role.class);
		System.out.println(bean.toString());
		
		System.out.println("------------->");
		String role_name_1 = ctx.getEnvironment().getProperty("note");
		System.out.println(role_name_1);
		
		System.out.println("ElBean------------->");
		ElBean elBean = ctx.getBean(ElBean.class);
		System.out.println(elBean.toString());
		
		
		
	}
}
