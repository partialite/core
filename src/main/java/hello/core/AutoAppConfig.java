package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        /**
         * - @ComponentScan
         * 자동으로 @Component를 스캔해서 스프링 빈으로 등록한다.(스프링 빈의 기본 이름은 클래스명을 사용한다.(앞글자 소문자))
         *
         * - @ComponentScan 대상
         * 1. @Component : 컴포넌트 스캔에서 사용
         * 2. @Controlller : 스프링 MVC 컨트롤러에서 사용
         * 3. @Service : 스프링 비즈니스 로직에서 사용
         * 4. @Repository : 스프링 데이터 접근 계층에서 사용
         * 5. @Configuration : 스프링 설정 정보에서 사용
         *
         * - basePackages
         * 해당 경로부터 하이 패키지를 찾아서 조회한다.(예제와 같은 경우 member만 @ComponentScan의 대상이 )
         *
         * - excludeFilters : 제외 대상
         * @Configuration 내부에 @Component가 있으므로 자동스캔 대상에 포함된다. (AppConfig.java 예제에서 수동으로 스프링빈을 등록하고 있으므로 충돌난다.)
         * 보통은 아래와 같이 @Configuration붙은 대상을 제외하지는 않는다.
         *
         * - @Autowired
         * 생성자에 @Autowired를 지정하면, 스프링 컨테이너가 자동으로 해당 스프링 빈을 찾아서 주입한다.
         * 타입이 같은 빈을 찾아서 주입한다.
         * ac.getBean(MemberRepository.class)와 동일하다고 생각하면 된다.
         */
        basePackages = "hello.core.member",
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

}
