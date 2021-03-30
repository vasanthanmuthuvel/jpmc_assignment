package pojo_Comments;

public class Payload {
	private long postId;
	private String name;
	private String email;
	private String body;
	
	public Payload(long postId, String name, String email, String body) {
		this.postId = postId;
		this.name = name;
		this.email = email;
		this.body = body;
	}
	public long getPostId() {
		return postId;
	}

	public void setPostId(long postId) {
		this.postId = postId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	

	
	
}
