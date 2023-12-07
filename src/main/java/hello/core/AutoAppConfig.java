package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        /**
         * - @ComponentScan
         * 자동으로 @Component를 스캔해서 스프링 빈으로 등록한다.(스프링 빈의 기본 이름은 클래스명을 사용한다.(앞글자 소문자))
         * ★★★ 중요! : 요즘엔 basePackages, basePackageClasses 값들을 지정하지 않는다.
         *             기본값으로 @ComponentScan이 붙은 설정 정보의 패키지를 포함한 하위 패키지를 스캔한다.
         *             (프로젝트 최상단에 해당 @ComponentScan 설정 정보를 위치한다. 스프링 부트에서도 해당 방식으로 제공함)
         *
         * - @ComponentScan 대상
         * 1. @Component : 컴포넌트 스캔에서 사용
         * 2. @Controlller : 스프링 MVC 컨트롤러에서 사용
         * 3. @Service : 스프링 비즈니스 로직에서 사용
         * 4. @Repository : 스프링 데이터 접근 계층에서 사용
         * 5. @Configuration : 스프링 설정 정보에서 사용
         *
         * - basePackages
         * 해당 경로를 포함한 하위 패키지를 탐색하고 스프링 빈으로 등록한다.(예제와 같은 경우 member만 @ComponentScan의 대상이 된다.)
         *
         * - basePackageClasses
         * 지정한 클래스의 패키지를 탐색하고 스프링 빈으로 등록한다.(예제와 같은 경우 hello.core를 포함한 하위 패키지를 탐색한다.)
         *
         * - includeFilters : 컴포넌트 스캔 추가할 대상을 지정한다.
         * - excludeFilters : 컴포넌트 스캔 제외할 대상을 지정한다.
         *
         * @Configuration 내부에 @Component가 있으므로 자동스캔 대상에 포함된다. (AppConfig.java 예제에서 수동으로 스프링빈을 등록하고 있으므로 충돌난다.)
         * 보통은 아래와 같이 @Configuration붙은 대상을 제외하지는 않는다.
         *
         * - @Autowired
         * 생성자에 @Autowired를 지정하면, 스프링 컨테이너가 자동으로 해당 스프링 빈을 찾아서 주입한다.
         * 타입이 같은 빈을 찾아서 주입한다.
         * ac.getBean(MemberRepository.class)와 동일하다고 생각하면 된다.
         */
        basePackages = "hello.core.member",
        basePackageClasses = AutoAppConfig.class,
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
/**
 * @Controller : 스프링 MVC 컨트롤러로 인식
 * @Repository : 스프링 데이터 접근 계층으로 인식하고, 데이터 계층의 예외를 스프링 예외로 변환해준다.
 * @Configuration : 앞서 보았듯이 스프링 설정 정보로 인식하고, 스프링 빈이 싱글톤을 유지하도록 추가 처리를 한다.
 * @Service : 사실 @Service 는 특별한 처리를 하지 않는다. 대신 개발자들이 핵심 비즈니스 로직이 여기에 있겠구나 라고 비즈니스 계층을 인식하는데 도움이 된다.
 */
public class AutoAppConfig {
    /**
     * 수동으로 스프링 빈에 등록한다.
     */
//    @Bean("memoryMemberRepository")
//    public MemberRepository memberRepository(){
//        return new MemoryMemberRepository();
//    }
}
