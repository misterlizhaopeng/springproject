package lp.annotation.com;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
public class ComponentProfile {

	@Bean
	@Profile(value="test")
	public BeanComponentOne componentOne() {
	BeanComponentOne beanComponentOne = new BeanComponentOne();
	return beanComponentOne;
	}
	
	@Bean
	@Profile(value="dev")
	public BeanComponentTwo componentTwo() {
		return new BeanComponentTwo();
	}
}
