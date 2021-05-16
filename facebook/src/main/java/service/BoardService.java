package service;

import java.util.List;

import domain.boards.Boards;
import domain.replys.Replys;
import repository.BoardDao;
import repository.ReplyDao;
import web.dto.boards.ListRespDto;
import web.dto.boards.SaveReqDto;

public class BoardService {
	
	private static BoardService instance = new BoardService();

	public static BoardService getInstance() {
		return instance;
	}	

	BoardDao boardDao= BoardDao.getInstance();
	ReplyDao replyDao= ReplyDao.getInstance();
	
	
	public ListRespDto 피드() {

		ListRespDto listRespDto = new ListRespDto();
				
		listRespDto.setBoard(boardDao.findById(1));
		listRespDto.setReplys(replyDao.findById(1));
		
		return listRespDto;				
	}
	

	public int 글쓰기(SaveReqDto dto) {
		if(dto.getUserId() < 1 || dto.getTitle() == null || dto.getContent() == null) {
			return -1;
		}else {
			return boardDao.save(dto);	
		}		
	}
}
