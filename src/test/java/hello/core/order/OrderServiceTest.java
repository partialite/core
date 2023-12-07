package hello.core.order;

import hello.core.AppConfig;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {
    private MemberService memberService;
    private OrderService orderService;;

    @BeforeEach
    public void BeforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }


    @Test
    void createOrder(){
        memberService.join(new Member(1L, "memberA", Grade.VIP));
        Order order = orderService.createOrder(1L, "itemA",10000 );
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(2000);
    }

    /**
     * 필드 주입으로 선언할 경우, 아래 샘플 소스와 같이 외부에서 접근 할 수 있는 방법이 없다.
     * 결국 세터를 선언해야하므로 스프링 설정을 목적으로 하는 @Configuration 같은 곳에서만 특별한 용도로 사용한다.
     *
     * 의존관계 자동 주입은 스프링 컨테이너가 관리하는 스프링 빈이어야 동작한다.
     * 스프링 빈이 아닌 Member 같은 클래스에서 @Autowired 코드를 적용해도 아무 기능도 동작하지 않는다.
     */
    @Test
    void fieldInjectionTest(){
//        OrderServiceImpl orderService = new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());

//        orderService.setMemberRepository(new MemoryMemberRepository());
//        orderService.setDiscountPolicy(new FixDiscountPolicy());

//        orderService.createOrder(1L, "itemA", 10000);
    }
}
