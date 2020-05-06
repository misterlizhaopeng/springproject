package cn.com.importsel;

import org.springframework.context.annotation.Import;

@Import(value = {C.class, MyImportSelector.class})
public class AnnotationConfig {
}
