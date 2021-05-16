package web;

import javax.servlet.http.HttpServletRequest;

import anno.PostMapping;
import domain.user.User;
import service.BoardService;
import service.UserService;
import web.dto.CMRespDto;
import web.dto.auth.JoinReqDto;
import web.dto.auth.LoginReqDto;
import web.dto.boards.SaveReqDto;

public class PostController<T> {
	
	UserService userService = new UserService();
	BoardService boardService = new BoardService();
	
    public PostController() {
        super();
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
			return new CMRespDto<>(-1,"login fail",user.toString());
				}		
		}
	
	@PostMapping("/logout")
	public CMRespDto<?> logout(LoginReqDto dto) { // Get으로 만들어도 되지만 관리하기 편하게 POST로 구현
		
			return new CMRespDto<>(-1,"logout");
		}
	
	}


