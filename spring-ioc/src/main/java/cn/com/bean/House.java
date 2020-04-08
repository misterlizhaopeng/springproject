package cn.com.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component(value="house01")
public class House {
	@Value("1")
	private String id;
	@Value("${name}")
	private String name;
	
}
