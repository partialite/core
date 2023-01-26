package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {

    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10% 할인이 적용되어야한다.")
    void vip_o(){
        int discount = discountPolicy.discount(new Member(1L, "VIP", Grade.VIP), 10000);
        Assertions.assertThat(discount).isEqualTo(1000);
    }

}