package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonTest {

    @Test
    @DisplayName("Spring 없는 순수한 DI 컨테이너")
    void pureContainer(){
        AppConfig appConfig = new AppConfig();

        // 1. 조회 : 호출할 때 마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();

        // 2. 조회 : 호출할 떄 마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();

        // 3. 결과 : 참조 값이 다른 것을 확인 (memberService1 != memberService2)
        Assertions.assertThat(memberService1).isNotSameAs(memberService2);
    }

    /**
     * 싱글톤 패턴의 문제점
     * 1. 싱글톤 패턴을 구현하는 코드 자체가 많이 들어간다.
     * 2. 의존관계상 클라이언트가 구체 클래스에 의존한다. DIP를 위반한다.
     * 3. 클라이언트가 구체 클래스에 의존해서 OCP 원칙을 위반할 가능성이 높다.
     * 4. 테스트하기 어렵다.
     * 5. 내부 속성을 변경하거나 초기화 하기 어렵다.
     * 6. private 생성자로 자식 클래스를 만들기 어렵다.
     * 7. 결론적으로 유연성이 떨어진다.
     * 8. 안티패턴으로 불리기도 한다
     */
    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singleTonServiceTest() {
        // 1. 조회 : 호출할 때 동일한 객체를 조회
        SingletonService instance1 = SingletonService.getInstance();

        // 2. 조회 : 호출할 때 동일한 객체를 조회
        SingletonService instance2 = SingletonService.getInstance();

        // 3. 결과 : 참조 값이 같은 것을 확인 (memberService1 == memberService2)
        Assertions.assertThat(instance1).isSameAs(instance2);
    }

    /**
     * 싱글톤 컨테이너 장점
     * 1. 스프링 컨테이너는 패턴을 적용하지 않아도, 객체 인스턴스를 싱글톤으로 관리한다.
     * 2. 스프링 컨테이너는 싱글톤 컨테이너 역할을 한다. 이렇게 싱글톤 객체를 생성하고 관리하는 기능을 싱글톤 레지스트리라 한다.
     * 3. 스프링 컨테이너의 기능 덕분에 싱글턴 패턴의 단점을 해결하면서 싱글톤을 유지할 수 있다.
     * 4. 싱글톤 패턴을 위한 지저분한 코드가 들어가지 않아도 된다.
     * 5. DIP(의존성), OCP(객체지향), 테스트, private 생성자로 부터 자유롭게 싱글톤을 사용할 수 있다.
     */
    @Test
    @DisplayName("싱글톤 컨테이너와 싱글톤")
    void springContainer(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        // 1. 조회 : 호출할 때마다 같은 객체를 반환
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);

        // 2. 조회 : 호출할 때마다 같은 객체를 반환
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        // 3. 결과 : 참조 값이 같은 것을 확인 (memberService1 == memberService2)
        Assertions.assertThat(memberService1).isSameAs(memberService2);
    }

}
