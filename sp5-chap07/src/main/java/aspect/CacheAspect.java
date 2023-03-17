package aspect;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;

@Aspect
@Order(2)
public class CacheAspect {
	private Map<Long, Object> cache = new HashMap<>();
	
	@Around("aspect.ExeTimeAspect.publicTarget()")
	public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
		Long num = (Long)joinPoint.getArgs()[0];
		if (cache.containsKey(num)) {
			System.out.printf("CacheAspect: Cache has key [%d]\n", num);
			return cache.get(num);
		}
		
		Object result = joinPoint.proceed();
		cache.put(num, result);
		System.out.printf("CacheAspect: Cache add [%d]\n", num);
		
		return result;
	}
}
