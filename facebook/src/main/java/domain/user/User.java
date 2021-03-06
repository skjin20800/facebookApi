package domain.user;

import java.sql.Timestamp;

public class User {

	private int id;
	private String username;
	private String password;
	private String nickname;
	private int age;
	private Timestamp createDate;
	
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	
	public static User builder() {
		return new User();
	}
	
	public User id(int id) {
		this.id = id;
		return this;
	}
	
	public User username (String username) {
		this.username = username;
		return this;
	}
	
	public User password (String password) {
		this.password = password;
		return this;
	}
	
	public User nickname (String nickname) {
		this.nickname = nickname;
		return this;
	}
	
	public User age (int age) {
		this.age = age;
		return this;
	}
	
	public User createDate (Timestamp createDate) {
		this.createDate = createDate;
		return this;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", nickname=" + nickname
				+ ", age=" + age + ", createDate=" + createDate + "]";
	}
	

}

