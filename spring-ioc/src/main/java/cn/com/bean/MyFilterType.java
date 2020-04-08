package cn.com.bean;

import java.io.IOException;

import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

public class MyFilterType implements TypeFilter {

	@Override
	public boolean match(MetadataReader arg0, MetadataReaderFactory arg1) throws IOException {
		
		AnnotationMetadata metadata = arg0.getAnnotationMetadata();
		String className = metadata.getClassName();
//		if (className.contains("Cat") || className.contains("Person")) {
		 if (className.contains("BigCat") 
				 || className.contains("BigDog")
				 || className.contains("Cat")
				 || className.contains("Dog")) {
             return true;//返回true，则表示把[当前类]的实例会添加到spring容器中，当前类在哪里找，就是MyFilterType所在组件扫描的包范围里找
      }
      return false;
	}

}
