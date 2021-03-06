package web;

import anno.PostMapping;
import domain.user.User;
import service.BoardService;
import service.LikeService;
import service.ReplyService;
import service.UserService;
import web.dto.CMRespDto;
import web.dto.auth.JoinReqDto;
import web.dto.auth.LoginReqDto;
import web.dto.boards.SaveReqDto;
import web.dto.likes.LikeBoardReqDto;
import web.dto.likes.LikeReplyReqDto;
import web.dto.replys.ReplyReqDto;
import web.dto.user.InfoReqDto;

public class PostController {
	
	UserService userService = UserService.getInstance();
	BoardService boardService = BoardService.getInstance();
	ReplyService replyService = ReplyService.getInstance();
	LikeService likeService = LikeService.getInstance();
	
    public PostController() {
        super();
    }
    
    
    
	@PostMapping("/likeReply")
	public CMRespDto<?> likeReply(LikeReplyReqDto dto ) {
			int result = likeService.댓글좋아요(dto);
			
			if(result == 1) {
				return new CMRespDto<>(result,"like complete");
			}else {
				return new CMRespDto<>(-1,"already like");
					}		
		}
    
    
	@PostMapping("/likeBoard")
	public CMRespDto<?> likeBoard(LikeBoardReqDto dto ) {
			int result = likeService.게시글좋아요(dto);
			
			if(result == 1) {
				return new CMRespDto<>(result,"like complete");
			}else {
				return new CMRespDto<>(-1,"already like");
					}		
		}
    
	@PostMapping("/replySave")
	public CMRespDto<?> replySave(ReplyReqDto dto ) {
			int result = replyService.댓글쓰기(dto);
			if(result == 1) {
				return new CMRespDto<>(result,"replySave complete");
			}else {
				return new CMRespDto<>(-1,"replySave fail");
					}		
		}
    
	
	@PostMapping("/boardSave")
	public CMRespDto<?> boardSave(SaveReqDto dto  ) {
			int result = boardService.글쓰기(dto);
			if(result == 1) {
				return new CMRespDto<>(result,"boardSave complete");
			}else {
				return new CMRespDto<>(-1,"boardSave fail");
					}		
		
		}
	
	@PostMapping("/userInfo")
	public CMRespDto<?> userInfo(InfoReqDto dto) {
		User user = userService.회원정보(dto.getUsername());		
		if(user != null) {
			return new CMRespDto<>(1,"userInfo complete",user);	
		}else {
			return new CMRespDto<>(1,"userInfo fail");
		}
	}
	
    
	@PostMapping("/join")
	public CMRespDto<?> join(JoinReqDto dto) {
		int result = userService.회원가입(dto);
		
		if(result ==1) {
			return new CMRespDto<>(result,"join complete");	
		}else {
			return new CMRespDto<>(-1,"join fail");
		}
	}
	
	@PostMapping("/login")
	public CMRespDto<?> login(LoginReqDto dto) {
		User user = userService.로그인(dto);		
		if(user != null) {
			return new CMRespDto<>(1,"login complete",user.toString());
		}else {
			return new CMRespDto<>(-1,"login fail");
				}		
		}
	
	@PostMapping("/logout")
	public CMRespDto<?> logout() { // Get으로 만들어도 되지만 관리하기 편하게 POST로 구현
			return new CMRespDto<>(-1,"logout");
		}
	
	}


