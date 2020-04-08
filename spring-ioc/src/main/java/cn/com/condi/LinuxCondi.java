package cn.com.condi;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class LinuxCondi implements Condition {

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
	     //获取bean定义的类
        BeanDefinitionRegistry registry = context.getRegistry();
        
        Environment environment = context.getEnvironment();
        String property = environment.getProperty("os.name");
        if (property.contains("linux"))
               return true;
        return false;
	}

}
