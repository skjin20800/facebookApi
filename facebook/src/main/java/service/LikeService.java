package service;

import domain.likes.Likes;
import repository.LikeDao;
import web.dto.likes.LikeBoardReqDto;
import web.dto.likes.LikeReplyReqDto;

public class LikeService {
	
	private static LikeService instance = new LikeService();

	public static LikeService getInstance() {
		return instance;
	}
	
	LikeDao likeDao = LikeDao.getInstance();

	public int 게시글좋아요(LikeBoardReqDto dto) {
		
		// 좋아요 검사
		Likes likes = likeDao.findByUserIdAndBoardId(dto);
		
		if(likes == null) { // 좋아요 유무 확인
			//좋아요 실행
			return	likeDao.saveBoard(dto);
		}else {
			//이미 좋아요 상태
			return -1;
		}				
	}
	
	public int 댓글좋아요(LikeReplyReqDto dto) {
		
		// 좋아요 검사
		Likes likes = likeDao.findByUserIdAndReplyId(dto);
		
		if(likes == null) { // 좋아요 유무 확인
			//좋아요 실행
			return	likeDao.saveReply(dto);
		}else {
			//이미 좋아요 상태
			return -1;
		}				
	}

	
	}



	

