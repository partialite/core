package hello.core.lifecycle;

import lombok.Setter;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


/**
 * 스프링에서 생명주기 콜백 3가지 방법
 * 1. 인터페이스(InitializingBean, DisposableBean)
 * => 사용하지 않음
 * - InitializingBean은 afterPropertiesSet() 메서드로 초기화를 지원한다.
 * - DisposableBean은 destroy() 메서드로 소멸을 지원한다.
 *
 * 인터페이스(InitializingBean, DisposableBean) 단점
 * - 스프링 전용 인터페이스이다.
 * - 초기화, 소멸 메서드의 이름을 변경할 수 없다.
 * - 코드 수정이 불가한 외부 라이브러리에 적용할 수 없다.
 *
 *
 * 2. @Bean(initMethod = "", destroyMethod = "")
 * - 메서드 이름을 자유롭게 줄 수 있다.
 * - 스프링 빈이 스프링 코드에 의존하지 않는다.
 * - 코드가 아니라 설정 정보를 사용하기 떄문에 외부 라이브러리에서도 초기화, 종료 메서드를 지정할 수 있다.
 * - 종료 메서드의 default 값이 (inferred)로 되어있는데 close나 shutdown이라는 이름의 메서드를 자동으로 호출해준다.
 *
 * 3. @PostConstruct, @PreDestroy
 * - 스프링에서 권고하는 방식 (자바에서 공식적으로 지원)
 * - 애노테이션만 붙이면 되므로 매우 편리하다.
 * - 외부 라이브러리에서는 사용 불가하므로 2번과 같은 방식을 사용한다.
 */
@Setter
//public class NetworkClient implements InitializingBean, DisposableBean {
public class NetworkClient {

    private String url;

    public NetworkClient(){
        System.out.println("생성자 호출 url : " + url);
    }

    // 서비스 시작시 호출
    public void connect(){
        System.out.println("connect : " + url);
    }

    public void call(String message) {
        System.out.println("call : " + url);
        System.out.println("message : " + message);
    }

    public void disconnect(){
        System.out.println("close : " + url);
    }

    /**
     * 의존관계 주입이 끝나면 실행된다.
     */
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        connect();
//        call("초기화 연결 메시지");
//    }

    /**
     * 스프링 컨테이너가 종료되고 실행된다.
     */
//    @Override
//    public void destroy() throws Exception {
//        disconnect();
//    }

    @PostConstruct
    public void init(){
        connect();
        call("초기화 연결 메시지");
    }

    @PreDestroy
    public void close(){
        disconnect();
    }
}
