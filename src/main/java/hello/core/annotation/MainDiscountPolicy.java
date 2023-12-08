package hello.core.annotation;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;

/**
 * 애노테이션을 정의해서 사용할 수 있다.
 * 아래 샘플은 @MainDiscountPolicy로 의존관계 자동 주입 시, @Qualifier("mainDiscountPolicy") 대신 @MainDiscountPolicy를 사용한다.
 * 하지만 애노테이션을 정의하는 방법은 유지보수 시, 혼란을 가중 시킬 수 있어서 필요한 경우에만 사용해야한다.
 *
 * 샘플소스 : RateDiscountPolicy.java, OrderServiceImpl.java, AutoAppConfigTest.java
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Qualifier("mainDiscountPolicy")
public @interface MainDiscountPolicy {


}
