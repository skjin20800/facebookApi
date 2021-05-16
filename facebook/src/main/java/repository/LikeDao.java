package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import config.DBConn;
import domain.likes.Likes;
import web.dto.likes.LikeBoardReqDto;

public class LikeDao {
	
	private static LikeDao instance = new LikeDao();

	public static LikeDao getInstance() {
		return instance;
	}	
	
	// 게시글 좋아요 검사
	public Likes findByUserIdAndBoardId(LikeBoardReqDto dto) { 
		String sql = "SELECT id, userId, boardId, replyId, createDate FROM likes WHERE userId = ? AND boardId = ? ";
		Connection conn = DBConn.getInstance();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getUserId());
			pstmt.setInt(2, dto.getBoardId());
			rs = pstmt.executeQuery();
	
			if(rs.next()) {
				Likes like = Likes.builder()
						.id(rs.getInt("id"))
						.userId(rs.getInt("userId"))
						.boardId(rs.getInt("boardId"))
						.replyId(rs.getInt("replyId"))
						.createDate(rs.getTimestamp("createDate"));
				
				return like;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally { // 무조건 실행
			DBConn.close(conn, pstmt);
		}
		return null;
	}


	public int saveBoard(LikeBoardReqDto dto) { // 댓글쓰기
		String sql = "INSERT INTO likes(userId, boardId, createDate) VALUES(?,?, now())";
		Connection conn = DBConn.getInstance();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getUserId());
			pstmt.setInt(2, dto.getBoardId());
			int result = pstmt.executeUpdate();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally { // 무조건 실행
			DBConn.close(conn, pstmt);
		}
		return -1;
	}
	
	
	
	
}
