package service;

import repository.ReplyDao;
import web.dto.replys.ReplyReqDto;

public class ReplyService {

	private static ReplyService instance = new ReplyService();

	public static ReplyService getInstance() {
		return instance;
	}
	
	ReplyDao replyDao= ReplyDao.getInstance();

	public int 댓글쓰기(ReplyReqDto dto) {
		if(dto.getUserId() < 1 || dto.getBoardId() < 1 || dto.getContent() == null) {
			return -1;
		}else {
			return replyDao.save(dto);	
		}		
	}
}
