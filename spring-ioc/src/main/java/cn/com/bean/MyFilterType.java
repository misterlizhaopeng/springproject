package cn.com.bean;

import java.io.IOException;

import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

public class MyFilterType implements TypeFilter {

	@Override
	public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
		
		AnnotationMetadata metadata = metadataReader.getAnnotationMetadata();
		String className = metadata.getClassName();
		//if (className.contains("Cat") || className.contains("Person")) {
		//返回true，则表示把[当前类]的实例会添加到spring容器中，当前类在哪里找，就是MyFilterType所在组件扫描的包范围里找
		 if (className.contains("BigCat") 
				 || className.contains("BigDog")
				 || className.contains("Cat")
				 || className.contains("Dog")) {
             return true;
      }
      return false;
	}

}
