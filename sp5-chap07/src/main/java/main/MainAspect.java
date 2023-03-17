package main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import chap07.Calculator;
import config.AppCtx;

public class MainAspect {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = 
				new AnnotationConfigApplicationContext(AppCtx.class);
		
		Calculator cal = ctx.getBean(Calculator.class);
		
//		RecCalculator cal = ctx.getBean("calculator", RecCalculator.class);
//		Bean named 'calculator' is expected to be of type 'chap07.RecCalculator' 
//		but was actually of type 'jdk.proxy2.$Proxy18'
//		"calculator" 빈의 실제 타입은 Calculator를 상속한 프록시 타입이므로 RecCalculator 타입 변환 불가해서 예외 발생
		
		long fiveFact = cal.factorial(5);
		System.out.println(fiveFact);
		System.out.println(cal.getClass().getName()); // 스프링이 생성한 프록시 타입
		ctx.close();
	}
}
