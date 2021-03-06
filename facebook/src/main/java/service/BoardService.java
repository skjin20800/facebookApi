package service;

import java.util.ArrayList;
import java.util.List;

import domain.boards.Boards;
import repository.BoardDao;
import repository.LikeDao;
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
	LikeDao likeDao= LikeDao.getInstance();
	
	// 피드에 List<게시글+List<댓글>>형태로 데이터를 넘겨준다.
	public List<ListRespDto> 피드() {

		List<ListRespDto> listRespDtos = new ArrayList<>();
		List<Boards> boards = boardDao.findAll();
		
		for (Boards board : boards) {
			
			ListRespDto listRespDto = new ListRespDto();
			Boards brd = boardDao.findById(board.getId()); // DB에서 select한 board 한건 담기
			brd.setLikeCount(likeDao.findByBoardId(board)); // 좋아요 삽입
		
			listRespDto.setBoard(brd); // board담기 
			listRespDto.setReplys(replyDao.findById(board.getId())); // reply리스트 담기
		
			listRespDtos.add(listRespDto); 
		}
		return listRespDtos;
	}
		
	// 글쓰기
	public int 글쓰기(SaveReqDto dto) {
		if(dto.getUserId() < 1 || dto.getTitle() == null || dto.getContent() == null) {
			return -1;
		}else {
			return boardDao.save(dto);	
		}		
	}
}
