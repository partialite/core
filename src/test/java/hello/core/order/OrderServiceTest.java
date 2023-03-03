package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
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

}
