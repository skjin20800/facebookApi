package web.dto.auth;

public class JoinReqDto {

	private String username;
	private String password;
	private String nickname;
	private int age;
	

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	@Override
	public String toString() {
		return "JoinReqDto [username=" + username + ", password=" + password + ", nickname=" + nickname
				+ ", age=" + age + "]";
	}
	
	
}
