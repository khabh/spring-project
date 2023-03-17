//package chap07;
//
//public class ImpeCalculator implements Calculator {
//	@Override
//	public long factorial(long num) {
//		long start = System.currentTimeMillis();
//		long result = 1;
//		for (long i = 1; i <= num; i++) {
//			result *= i;
//		}
//		long end = System.currentTimeMillis();
//		System.out.printf("ImpeCalculator(%d) 실행 시간 : %d\n", num, end - start);
//		return result;
//	}
//}
package chap07;

public class ImpeCalculator implements Calculator {

	@Override
	public long factorial(long num) {
		long result = 1;
		for (long i = 1; i <= num; i++) {
			result *= i;
		}
		return result;
	}

}
