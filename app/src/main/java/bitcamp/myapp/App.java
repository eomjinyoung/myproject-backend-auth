package bitcamp.myapp;

import bitcamp.myapp.common.LoginUserArgumentResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@SpringBootApplication // Gradle에서 bootRun 태스크를 수행할 때 실행시킬 메인 클래스를 지정하는 애노테이션
public class App implements WebMvcConfigurer {
  // Web MVC 관련 추가 사항은 WebMvcConfigurer 구현체의 메서드를 통해 설정한다.
  // 예) HandlerInterceptor, HandlerMethodArgumentResolver 등

  // 스프링부트의 프로퍼티 값(application.properties)을 커스터마이징 할 때 사용하는 객체
  private final ConfigurableEnvironment configurableEnvironment;

  public App(ConfigurableEnvironment configurableEnvironment) {
    this.configurableEnvironment = configurableEnvironment;
  }


  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
    // request handler의 특정 파라미터 값을 처리하기 위해 개발자가 만든 객체를 등록한다.
    resolvers.add(new LoginUserArgumentResolver());
  }

  public static void main(String[] args) {
    System.out.println("App 실행!");
    SpringApplication.run(App.class, args);
  }

}
