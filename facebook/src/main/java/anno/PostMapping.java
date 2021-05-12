package anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})//실행타입 : 필드(.FIELD), 메서드(.METHOD), 클레스(.TYPE) 
@Retention(RetentionPolicy.RUNTIME) //실행시점 : 런타임(.RUNTIME) or 컴파일시점 정하기(.SOURCE)
public @interface PostMapping {
	String value();
}