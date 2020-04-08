package x.b.n;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
public class AppConfig {

	@Bean
	public Cat cat() {
		return new Cat();
	}
	
	@Bean
	public Cat cat02() {
		return new Cat();
	}
	
}
