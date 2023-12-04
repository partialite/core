package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
}
