package spring;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Client implements InitializingBean, DisposableBean{
	private String host;
	
	public void setHost(String host) {
		this.host = host;
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("afterPropertiesSet 실행");
	}
	
	public void sendHost() {
		System.out.println("send to " + host);
	}
	
	@Override
	public void destroy() throws Exception {
		System.out.println("destroy 실행");
	}
}
