package spring;

import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;

public class MemberPrinter {
	private DateTimeFormatter dateTimeFormatter;
	
	public MemberPrinter() {
		dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
	}
	
	public void print(Member member) {
		if (dateTimeFormatter == null) {
//			System.out.println(this.dateTimeFormatter);
			System.out.printf(
					"회원 정보: 아이디=%d, 이메일=%s, 이름=%s, 등록일=%tF\n", 
					member.getId(), member.getEmail(),
					member.getName(), member.getRegisterDateTime());
		} else {
			System.out.printf(
					"회원 정보: 아이디=%d, 이메일=%s, 이름=%s, 등록일=%s\n", 
					member.getId(), member.getEmail(),
					member.getName(), 
					dateTimeFormatter.format(member.getRegisterDateTime()));
		}
	}
	

//  첫 번째 방법
	@Autowired(required = false) // 자동 주입할 대상이 필수가 아닐 경우 -> 매칭되는 Bean이 없어도 예외 발생 X
	public void setDateFormatter(DateTimeFormatter dateTimeFormatter) {
		this.dateTimeFormatter = dateTimeFormatter;
	}

//  두 번째 방법
//  ***** 스프링5 버전부터는 의전 주입 대상에 자바 8의 Optional 사용 가능 ****	
//	@Autowired
//	public void setDateFormatter(Optional<DateTimeFormatter> formatterOpt) {
//		if (formatterOpt.isPresent()) {
//			this.dateTimeFormatter = formatterOpt.get();
//		} else {
//			this.dateTimeFormatter = null;
//		}
//	}
	
//  세 번째 방법
//	@Autowired
//	public void setDateFormatter(@Nullable DateTimeFormatter dateTimeFormatter) {
//		this.dateTimeFormatter = dateTimeFormatter;
//	}
	
//  @Nullable과 @Autowired false의 차이
//	전자를 사용하면 자동 주입할 빈이 존재하지 않아도 세터 메서드를 호출(null 할당)하는 반면 
//	후자는 대상 빈이 존재하지 않으면 세터 메서드를 호출하지 않는다.
		
}
