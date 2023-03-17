package main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.AppCtx;
import spring.ChangePasswordService;
import spring.MemberNotFoundException;
import spring.WrongIdPasswordException;

public class MainForCPS {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = 
				new AnnotationConfigApplicationContext(AppCtx.class);
		
		ChangePasswordService cps = 
				ctx.getBean(ChangePasswordService.class);
		try {
			cps.changePassword("madvirus@madvirus.net", "1234", "1111");
			System.out.println("password changed");
		} catch (MemberNotFoundException e) {
			System.out.println("Member not found");
		} catch (WrongIdPasswordException e) {
			System.out.println("Wrong password");
		}
		
		ctx.close();
	}
}
