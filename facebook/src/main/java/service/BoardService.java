package service;

import repository.BoardDao;
import web.dto.boards.SaveReqDto;

public class BoardService {

	BoardDao boardDao= new BoardDao();

	public int 글쓰기(SaveReqDto dto) {
		if(dto.getUserId() < 1 || dto.getTitle() == null || dto.getContent() == null) {
			return -1;
		}else {
			return boardDao.save(dto);	
		}		
	}




	
}
