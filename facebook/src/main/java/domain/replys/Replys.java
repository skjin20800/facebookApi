package domain.replys;

import java.sql.Timestamp;

public class Replys {

	private int id;
	private int userId;
	private int boardId;
	private int likeCount;
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
	public int getBoardId() {
		return boardId;
	}
	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}
	
	
	public int getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
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
	
	public static Replys builder() {
		return new Replys();
	}
	
	public Replys id(int id) {
		this.id = id;
		return this;
	}
	
	public Replys userId(int userId) {
		this.userId = userId;
		return this;
	}
	
	public Replys boardId (int boardId) {
		this.boardId = boardId;
		return this;
	}
	
	public Replys content (String content) {
		this.content = content;
		return this;
	}
	
	public Replys createDate (Timestamp createDate) {
		this.createDate = createDate;
		return this;
	}
	@Override
	public String toString() {
		return "Replys [id=" + id + ", userId=" + userId + ", boardId=" + boardId + ", likeCount=" + likeCount
				+ ", content=" + content + ", createDate=" + createDate + "]";
	}
	
}


