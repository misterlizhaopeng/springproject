package cn.com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import cn.com.bean.Cat;
import cn.com.bean.Dog;

@Configuration
@ComponentScan("cn.com.bean")
public class AnnotationBeanLoad {
	
//	@Bean
//	public Dog dog(Cat cat) {
//		Dog dog = new Dog(cat);
//		System.out.println("Dog的值："+dog);
//		System.out.println("Dog引用的cat的值："+cat);
//		return dog;
//	}
	
	
	
	
	@Scope("singleton")//singleton
	@Bean
	public Cat cat() {
		Cat cat=new Cat();
		System.out.println("Cat的值："+cat);
		return cat;
	}
	
}
