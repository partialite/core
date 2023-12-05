package hello.core.singleton;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class StatefulServiceTest {

    /**
     * 싱글톤 방식의 주의점
     * 1. 싱글톤 패턴이든, 스프링 같은 싱글톤 컨테이너를 사용하든, 객체 인스턴스를 하나만 생성해서 공유하는 싱글톤 방식은 여러 클라이언트가
     *    하나의 같은 객체 인스턴스를 공유하기 때문에 싱글톤 객체는 상태를 유지(stateful)하게 설계하면 안된다.
     * 2. 무상태(stateless)로 설계해야 한다.
     * 3. 스프링 빈의 필드에 공유 값을 설정하면 정말 큰 장애가 발생할 수 있다.
     */
    @Test
    @DisplayName("상태를 유지하는 싱글톤 객체 생성")
    void statefulServiceSingleton(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        StatefulService statefulService1 = ac.getBean("statefulService", StatefulService.class);
        StatefulService statefulService2 = ac.getBean("statefulService", StatefulService.class);

        // 사용자A : 1000
        statefulService1.order("userA", 1000);
        // 사용자B : 2000
        statefulService2.order("userB", 2000);

        // 사용자A 금액 조회
        int price = statefulService1.getPrice();
        System.out.println("price : " + price);

        // 결과 : 사용자A 금액은 1000이 아닌 2000이 조회됨
        Assertions.assertThat(price).isEqualTo(1000);
    }
}