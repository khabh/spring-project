package aspect;


import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

@Aspect
@Order(1)
public class ExeTimeAspect {

	@Pointcut("execution(public * chap07..*(..))")
	// chap07 패키지와 그 하위 패키지에 위치한 타입의 public 메서드를 Pointcut으로 설정한다.
	// execution(수식어패턴? 리턴타입패턴 클래스이름패턴? 메서드이름패턴(파라미터패턴))
	// *으로 모든 값 표현, ..로 0개 이상이라는 의미 표현
	public void publicTarget() {
	}
	
	@Around("publicTarget()")
	// chap07 패키지와 그 하위 패키지에 속한 빈 객체의 public 메서드에 Around measure 메서드를 적용한다.
	public Object measure(ProceedingJoinPoint joinPoint) throws Throwable {
		// ProceedingJoinPoint 타입 파라미터는 프록시 대상 객체의 메서드 호출 시 사용
		long start = System.nanoTime();
		try {
			Object result = joinPoint.proceed(); // 프록시 대상 객체의 메서드 호출 
			return result;
		} finally {
			long finish = System.nanoTime();
			Signature sig = joinPoint.getSignature();
			System.out.printf("%s.%s(%s) 실행 시간: %d ns\n",
					joinPoint.getTarget().getClass().getSimpleName(),
					sig.getName(), Arrays.toString(joinPoint.getArgs()),
					finish - start);
		}
	}
	
}
