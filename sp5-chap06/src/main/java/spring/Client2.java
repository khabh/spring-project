package spring;


public class Client2 {
	private String host;
	
	public void setHost(String host) {
		this.host = host;
	}
	
	public void connect() {
		System.out.println("connect() 실행 ");
	}
	
	public void sendHost() {
		System.out.println("client2 - send to " + host);
	}
	
	public void close() {
		System.out.println("client2 close 실행");
	}
}
