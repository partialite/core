package hello.core.scope;

import ch.qos.logback.core.net.server.Client;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

public class PrototypeProviderTest {

    @Test
    void providerTest(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);
        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        Assertions.assertThat(clientBean1.logic()).isEqualTo(1);

        ClientBean clientBean2= ac.getBean(ClientBean.class);
        Assertions.assertThat(clientBean2.logic()).isEqualTo(1);
    }

    @RequiredArgsConstructor
    static class ClientBean {
//        private final ApplicationContext ac;
        private final Provider<PrototypeBean> prototypeBeanProvider;

        public int logic(){
            /**
             * ac.getBean() 을 통해서 항상 새로운 프로토타입 빈이 생성되는 것을 확인할 수 있다.
             * 의존관계를 외부에서 주입(DI) 받는게 아니라 이렇게 직접 필요한 의존관계를 찾는 것을 Dependency Lookup (DL) 의존관계 조회(탐색) 이라한다.
             * 그런데 이렇게 스프링의 애플리케이션 컨텍스트 전체를 주입받게 되면, 스프링 컨테이너에 종속적인 코드가 되고, 단위 테스트도 어려워진다.
             * 지금 필요한 기능은 지정한 프로토타입 빈을 컨테이너에서 대신 찾아주는 딱! DL 정도의 기능만 제공하는 무언가가 있으면 된다.
             * DL 서비스를 제공하는 것이 바로 ObjectProvider이다.
             * ObjectProvider 는 지금 딱 필요한 DL 정도의 기능만 제공한다.
             * ObjectProvider: ObjectFactory 상속, 옵션, 스트림 처리등 편의 기능이 많고, 별도의 라이브러리 필요 없음, 스프링에 의존
             */
//            PrototypeBean prototypeBean = ac.getBean(PrototypeBean.class);
            PrototypeBean prototypeBean = prototypeBeanProvider.get();
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }

    @Getter
    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public void addCount(){
            count++;
        }

        @PostConstruct
        public void init(){
            System.out.printf("PrototypeBean.init : " + this);
        }

        @PreDestroy
        public void destroy(){
            System.out.printf("PrototypeBean.destroy : " + this);
        }
    }
}
