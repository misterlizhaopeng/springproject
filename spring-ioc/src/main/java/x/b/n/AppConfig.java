package x.b.n;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.DefaultLifecycleProcessor;

public class AppConfig {

    @Bean
    public Cat cat() {
        return new Cat();
    }

    @Bean
    public BigDog bigDog() {
        return new BigDog();
    }

    //@Bean
    public LifeCycleTst lifeCycleTst() {
        return new LifeCycleTst();
    }


    @Bean
    public SmartLifeCycleTst smartLifeCycleTst() {
        return new SmartLifeCycleTst();
    }

    @Bean
    public DefaultLifecycleProcessor defaultLifecycleProcessor(){
        DefaultLifecycleProcessor defaultLifecycleProcessor = new DefaultLifecycleProcessor();
        defaultLifecycleProcessor.setTimeoutPerShutdownPhase(10000);
        return defaultLifecycleProcessor;
    }

//	@Bean
//	public Cat cat02() {
//		return new Cat();
//	}

}
