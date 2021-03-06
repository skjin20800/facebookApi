package domain.likes;

import java.sql.Timestamp;

public class Likes {

	private int id; 
	private int userId;
	private int boardId;
	private int replyId;
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
	public int getReplyId() {
		return replyId;
	}
	public void setReplyId(int replyId) {
		this.replyId = replyId;
	}
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	
	public static Likes builder() {
		return new Likes();
	}
	
	public Likes id(int id) {
		this.id = id;
		return this;
	}
	
	public Likes userId(int userId) {
		this.userId = userId;
		return this;
	}
	
	public Likes boardId(int boardId) {
		this.boardId = boardId;
		return this;
	}
	
	public Likes replyId(int replyId) {
		this.replyId = replyId;
		return this;
	}
	
	public Likes createDate (Timestamp createDate) {
		this.createDate = createDate;
		return this;
	}
	
	
	
}