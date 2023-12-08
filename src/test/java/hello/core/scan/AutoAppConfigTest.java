package hello.core.scan;

import hello.core.AutoAppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 자동 빈, 자동 빈 충돌의 경우 => 오류 발생
 * 수동 빈, 자동 빈 충돌의 경우 => 수동 빈 등록(아래 로그가 남는다.)
 * Overriding bean definition for bean 'memoryMemberRepository' with a different definition: replacing
 *
 * 스프링 부트에서는 수동 빈과 자동 빈 등록이 충돌나면 오류가 발생되도록 기본값이 바뀌었다.
 */

/**
 * 의존관계 자동주입 방법 3가지
 *
 * 1. @Autowired
 * @Autowired 는 타입 매칭을 시도하고, 이때 여러 빈이 있으면 필드 이름, 파라미터 이름으로 빈 이름을 추가
 * 매칭한다
 *
 * 2. @Qualifier
 * Qualifier 는 추가 구분자를 붙여주는 방법이다. 주입시 추가적인 방법을 제공하는 것이지 빈 이름을
 * 변경하는 것은 아니다.
 *
 * 3. @Primary
 * @Primary 는 우선순위를 정하는 방법이다. @Autowired 시에 여러 빈이 매칭되면 @Primary 가 우선권을
 * 가진다.
 *
 * 샘플 소스 : *DisCountPolicy.java, OrderServiceImpl,java, AutoAppConfigTest.java
 */
public class AutoAppConfigTest {
    @Test
    void basicScan(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

        MemberService memberService = ac.getBean(MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
    }
}
