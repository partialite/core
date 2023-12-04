package hello.core.singleton;

import org.assertj.core.api.Assertions;

public class SingletonService {
    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance() {
        return instance;
    }

    /**
     * private 하게 생성자를 선언하여 외부에서 호출하지 못하도록 한다.
     */
    private SingletonService() {
    }

    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }

}
