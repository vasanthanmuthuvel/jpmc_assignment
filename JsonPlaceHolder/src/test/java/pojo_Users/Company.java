package pojo_Users;

public class Company {
	private String name;
	private String catchPhrase;
	private String bs;
	
	public Company(String name,String catchPhrase,String bs) {
		this.bs=bs;
		this.name=name;
		this.catchPhrase=catchPhrase;
			
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCatchPhrase() {
		return catchPhrase;
	}
	public void setCatchPhrase(String catchPhrase) {
		this.catchPhrase = catchPhrase;
	}
	public String getBs() {
		return bs;
	}
	public void setBs(String bs) {
		this.bs = bs;
	}
	
	
}
