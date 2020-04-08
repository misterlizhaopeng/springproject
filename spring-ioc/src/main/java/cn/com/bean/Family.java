package cn.com.bean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Component;

@Component
public class Family {
	public Family() {
		System.out.println("构造函数...Family");
	}
	
	@PostConstruct
	public void init() {
		System.out.println("go init...Family");
	}
	@PreDestroy
	public void destroy() {
		System.out.println("go destroy...Family");
	}
}
