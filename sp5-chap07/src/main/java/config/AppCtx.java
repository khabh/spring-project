package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import aspect.ExeTimeAspect;
import chap07.Calculator;
import chap07.RecCalculator;

@Configuration
@EnableAspectJAutoProxy 
// 스프링이 @Aspect 애노테이션이 붙은 빈 객체를 찾아서 @Pointcut과 Around 설정을 사용한다
public class AppCtx {
	
	@Bean
	public ExeTimeAspect exTimeAspect() {
		return new ExeTimeAspect();
	}
	
	@Bean
	public Calculator calculator() {
		return new RecCalculator();
	}
}
