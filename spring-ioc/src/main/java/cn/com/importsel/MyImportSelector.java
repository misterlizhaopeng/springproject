package cn.com.importsel;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportSelector implements ImportSelector{

	@Override
	public String[] selectImports(AnnotationMetadata annotationMetadata) {

		// 1.public interface AnnotationMetadata extends ClassMetadata, AnnotatedTypeMetadata;
		// 		元注解：注解上的注解，Spring将其定义为元注解(meta-annotation),如 @Component标注在 @Service上，@Component就被称作为元注解；
		// 2.ClassMetadata
		//		此接口的所有方法，基本上都跟class有关：
		//返回最原始的那个类的名称
		String className = annotationMetadata.getClassName();
		//是否是注解
		boolean isAnnotation = annotationMetadata.isAnnotation();



		return new String [] {"cn.com.importsel.A","cn.com.importsel.B"};
	}

}
