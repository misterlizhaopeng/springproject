package cn.com.test;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import cn.com.bean.ValueTest;
import cn.com.config.AnnotationBean;
import cn.com.config.HouseConfig;
import cn.com.config.PojoValConf;

public class MT {
	@Test
	public void componentTest() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(HouseConfig.class);
		String[] names = ctx.getBeanDefinitionNames();
		System.out.println(1);
		for (String ss : names) {
			System.out.println(ss);
		}
		
		
//		House bean = ctx.getBean("house01",House.class);
//		System.out.println("---------->"+bean);
	}
	
	
	
	@Test
	public void ttttttttttt() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(PojoValConf.class);
		
//		String string = ctx.getEnvironment().getProperty("idx");
//		System.out.println(string);
		
		
		ValueTest vv = ctx.getBean(ValueTest.class);
		System.out.println(vv.toString());
		
	}
//	private static final String Cat = null;

//	@SuppressWarnings("resource")
	@Test
	public void test() {
//		@SuppressWarnings("resource")
//		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AnnotationBean.class);
//		Person bean = context.getBean(Person.class);
//		System.out.println(bean);
//		
//		
//		String[] beanNamesForType = context.getBeanNamesForType(Person.class);
//		for (String string : beanNamesForType) {
//			System.out.println(string);
//		}
//		
 
//		Object b1 = context.getBean("person");
//		Object b2 = context.getBean("person");
//		System.out.println(b1==b2);//false
		
		
		
//		Person bean = context.getBean("p1", Person.class);
//		 System.out.println(bean);
//		
		
//	
//		String[] names = context.getBeanDefinitionNames();
//		for (String string : names) {
//			System.out.println(string);
//		}
		
		
		
	
//		Object bean = context.getBean("my-factoryBean");
//		System.out.println(bean.toString());
//		
//		
//		Object b2 = context.getBean("my-factoryBean");
//		System.out.println(b2.toString());
		
		
		
//		  Object b = context.getBean("red");
//		  System.out.println(b.toString());
//		  context.destroy();
		
		
		
		
//		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AnnotationBeanInitDestroy.class);
//		ctx.close();
//		
		
		
		
		
		
//		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(PojoValConf.class);
//		Object bean = ctx.getBean("valueTest");
//		System.out.println(bean);
		
		
//		String[] bs = ctx.getBeanDefinitionNames();
//		for (String string : bs) {
//			
//			System.out.println(string);
//		}
		
		
		
//		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AnnotationSerDao.class);
//		String[] names = ctx.getBeanDefinitionNames();
//		for (String s : names) {
//			System.out.println(s);
//		}
//		
//		UserService userService =ctx.getBean("userServiceImpl",UserService.class);
//		Integer i = userService.calc(11,90);
//		System.out.println(i);
		
		
//		AnnotationConfigApplicationContext ctx=new AnnotationConfigApplicationContext(AnnotationBeanLoad.class);
//		String[] ns = ctx.getBeanDefinitionNames();
//		for (String s : ns) {
//			System.out.println(s);
//		}
		
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AnnotationBean.class);
		
		Class<?> t = ctx.getType("dog");
		System.out.println("t:===>"+t);
		String[] aliases = ctx.getAliases("dog");
		System.out.println(aliases.toString());
//		Dog dog = ctx.getBean(Dog.class);
//		System.out.println(dog);
//		System.out.println("--------------------------------------");
		
		
//		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("");
//		Class<?> type2 = context.getType("dog");
		
		
		String[] ss = ctx.getBeanDefinitionNames();
		for (String s : ss) {
			System.out.println(s);
		}
	}
}
