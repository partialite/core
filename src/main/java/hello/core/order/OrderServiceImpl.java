package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
/**
 * - @RequiredArgsConstructor
 * final이 붙은 전역변수를 파라미터로 받는 생성자를 만들어준다.
 *
 */
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
    /**
     * 1. 생성자 주입 방식
     * 생성자가 하나만 있는 경우, @Autowired 를 사용하지 않아도 자동으로 의존 주입된다.
     *
     * 최근에는 생성자를 딱 1개 두고, @Autowired 를 생략하는 방법을 주로 사용한다.
     * 여기에 Lombok 라이브러리의 @RequiredArgsConstructor 함께 사용하면 기능은 다 제공하면서, 코드는 깔끔하게 사용할 수 있다
     */
    @Getter
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

//    @Autowired
//    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        System.out.println("memberRepository : " + memberRepository);
//        System.out.println("discountPolicy : " + discountPolicy);
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }

    /**
     * 1. @Autowired 의존관계 자동 주입
     */
//    @Autowired
//    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy rateDiscountPolicy) {
//        System.out.println("memberRepository : " + memberRepository);
//        System.out.println("discountPolicy : " + rateDiscountPolicy);
//        this.memberRepository = memberRepository;
//        this.discountPolicy = rateDiscountPolicy;
//    }

    /**
     * 2. @Qualifier 의존관계 자동 주입
     */
//    @Autowired
//    public OrderServiceImpl(MemberRepository memberRepository, @Qualifier("mainDiscountPolicy") DiscountPolicy discountPolicy) {
//    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
//        System.out.println("memberRepository : " + memberRepository);
//        System.out.println("discountPolicy : " + discountPolicy);
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }

    /**
     * 3. @primary 의존관계 자동 주입 => 가장 많이 사용
     */
//    @Autowired
//    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        System.out.println("memberRepository : " + memberRepository);
//        System.out.println("discountPolicy : " + discountPolicy);
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }

    /**
     * 2. setter 주입 방식
     * 선택, 변경 가능성이 있는 의존 관계에서 사용 (이외에는 잘 사용하지 않음)
     */
//    private MemberRepository memberRepository;
//    private DiscountPolicy discountPolicy;
//
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }
//
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        this.discountPolicy = discountPolicy;
//    }

    /**
     * 3. 필드 주입 방식
     * 외부에서 변경이 완전 불가능해서 테스트하기 힘들다는 단점이 있음.
     * 최근에는 사용하지 않는 추세.
     */
//    @Autowired private MemberRepository memberRepository;
//    @Autowired private DiscountPolicy discountPolicy;

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member findMember = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(findMember, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

//    // 테스트 용도
//    public MemberRepository getMemberRepository() {
//        return memberRepository;
//    }
}
