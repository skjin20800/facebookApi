package domain.boards;

import java.sql.Timestamp;

public class Boards {

	private int id;
	private int userId;
	private int likeCount; //DB테이블에 컬럼이 없다
	private String title;
	private String content;	
	private Timestamp createDate;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	
	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public static Boards builder() {
		return new Boards();
	}
	
	public Boards id(int id) {
		this.id = id;
		return this;
	}
	
	public Boards userId(int userId) {
		this.userId = userId;
		return this;
	}
	
	public Boards title (String title) {
		this.title = title;
		return this;
	}
	
	public Boards content (String content) {
		this.content = content;
		return this;
	}
	
	public Boards createDate (Timestamp createDate) {
		this.createDate = createDate;
		return this;
	}
	
	public Boards likeCount (int likeCount) {
		this.likeCount = likeCount;
		return this;
	}
	@Override
	public String toString() {
		return "Boards [id=" + id + ", userId=" + userId + ", likeCount=" + likeCount + ", title=" + title
				+ ", content=" + content + ", createDate=" + createDate + "]";
	}
	

	
	
}


