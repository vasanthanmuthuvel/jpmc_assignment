package pojo_Posts;

public class Payload {
	private long userId;
	private String title;
	private String body;
	
	public Payload(long userId, String title, String body) {

		this.userId = userId;
		this.title = title;
		this.body = body;
	}
	
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	
	
}
