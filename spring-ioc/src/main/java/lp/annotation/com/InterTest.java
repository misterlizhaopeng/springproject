package lp.annotation.com;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

/*
 * 
 * 
 * 集成测试需要的pom；
 * 
 * 
 *  <dependency>
          <groupId>junit</groupId>
          <artifactId>junit</artifactId>
          <version>4.12</version>
          <scope>compile</scope>
      </dependency>
      
 * <dependency>
	    <groupId>org.springframework</groupId>
	    <artifactId>spring-test</artifactId>
	    <version>4.3.0.RELEASE</version>
	</dependency>
 * 
 * */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HouseConfig.class) // spring容器的配置文件的入口（我的理解）
@ActiveProfiles(value = "dev") // test
public class InterTest {

	@Autowired
	private House house;

	@Autowired
	private Family family;

	@Test
	public void testHouse() {
		// 忽然想到如何获取spring容器，网上搜了一下，但是没找到想要的答案，于是想到了让当前的一个bean实现（implements）ApplicationContextAware接口，在通过getter、setter方法暴露出来这个对象即可获取spring容器，这里就用到了bean的生命周期的知识了；
		// 为何想到获取spring容器：因为想在这个集成测试类中看看@ActiveProfiles指定的bean实例加载进来了没有；
		ApplicationContext ctx = house.getApplicationContext();
		String[] names = ctx.getBeanDefinitionNames();
		//lambda表达式，在此测试，只能List类型的 才可以forEach遍历，字符串数组类型的不行，这一点没有c#强大：
		List<String> ns = Arrays.asList(names);
		ns.forEach(aa -> {
			System.out.println("lambda->:" + aa);
		});
		
		
		// for (String ss : names) {
		// System.out.println(ss);
		// }
		//
		System.out.println(house.toString());
		System.out.println(family.toString());
	}

}
