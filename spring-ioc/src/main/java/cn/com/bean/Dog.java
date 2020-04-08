package cn.com.bean;

import org.springframework.stereotype.Component;

@Component
public class Dog {
	
	public Dog(Cat cat) {
		System.out.println("Dog引用的cat的值："+cat);
	}

}
