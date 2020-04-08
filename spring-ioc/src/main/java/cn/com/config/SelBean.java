package cn.com.config;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class SelBean implements ImportSelector{

	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {
		 
		
		return new String [] {"cn.com.bean.Cat","cn.com.bean.Dog"};
	}

}
