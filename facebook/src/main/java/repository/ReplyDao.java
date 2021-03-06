package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import config.DBConn;
import domain.replys.Replys;
import web.dto.replys.ReplyReqDto;

public class ReplyDao {
	
	
	private static ReplyDao instance = new ReplyDao();
	private LikeDao likeDao = LikeDao.getInstance();

	public static ReplyDao getInstance() {
		return instance;
	}	
	
	//게시글 ID로 댓글 리스트 출력
	public List<Replys> findById(int boardId) { 
		String sql = "SELECT id, userId, boardId, content, createDate FROM replys WHERE boardId = ? ";
		Connection conn = DBConn.getInstance();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardId);
			rs = pstmt.executeQuery();
	
			List<Replys> replyList = new ArrayList<>();
			
			while(rs.next()) {
				Replys reply = Replys.builder()
						.id(rs.getInt("id"))
						.boardId(rs.getInt("boardId"))
						.content(rs.getString("content"))
						.userId(rs.getInt("userId"))
						.createDate(rs.getTimestamp("createDate"));
				
				reply.setLikeCount(likeDao.findByReplyId(reply)); //좋아요 갯수 삽입
				
				replyList.add(reply);
			}
			return replyList;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally { // 무조건 실행
			DBConn.close(conn, pstmt);
		}
		return null;
	}

	
	// 댓글 쓰기
	public int save(ReplyReqDto dto) {
		String sql = "INSERT INTO replys(userId, boardId, content, createDate) VALUES(?,?,?, now())";
		Connection conn = DBConn.getInstance();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getUserId());
			pstmt.setInt(2, dto.getBoardId());
			pstmt.setString(3, dto.getContent());
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
