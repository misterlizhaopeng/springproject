package cn.com.config;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import cn.com.bean.Cat;
import cn.com.bean.Dog;

public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

	
	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		
			RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(Dog.class);
			registry.registerBeanDefinition("Dog",rootBeanDefinition);
	}
}
