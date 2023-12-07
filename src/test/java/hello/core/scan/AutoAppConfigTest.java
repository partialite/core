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
public class AutoAppConfigTest {
    @Test
    void basicScan(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

        MemberService memberService = ac.getBean(MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
    }
}
