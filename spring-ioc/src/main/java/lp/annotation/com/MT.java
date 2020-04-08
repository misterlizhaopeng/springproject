package lp.annotation.com;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import cc.nn.gg.XmlBean01;
import lp.annotation.com.House;

public class MT {

	
	
	
	@Test
	public void componentTest() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(HouseConfig.class);

//		String p = ctx.getEnvironment().getProperty("target");
//		System.out.println(p);
		// House house = ctx.getBean(House.class);
		// System.out.println(house.toString());
		

		String[] names = ctx.getBeanDefinitionNames();
		for (String ss : names) {
			System.out.println(ss);
		}

//		Autowired_Test autowired_Test = ctx.getBean("autowired_Test", Autowired_Test.class);
//		autowired_Test.output();

		// XmlBean01 xmlBean01 = ctx.getBean("xmlBean01",XmlBean01.class);
		// System.out.println(xmlBean01);

		// House bean = ctx.getBean(House.class);
		// System.out.println(bean.toString());
	}
}
