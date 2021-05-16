package web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import anno.GetMapping;
import domain.user.User;
import service.BoardService;
import service.UserService;
import web.dto.CMRespDto;
import web.dto.boards.ListRespDto;

public class GetController<T> {
	
	UserService userService = UserService.getInstance();
	BoardService boardService = BoardService.getInstance();
	
    public GetController() {
        super();
    }
    
	@GetMapping("/feed")
	public CMRespDto<?> feed() {
		List<ListRespDto> boardDetailDto = boardService.피드();
			 
			if(boardDetailDto != null) {
				return new CMRespDto<>(1, "feed complete", boardDetailDto);
			}else {
				return new CMRespDto<>(-1,"feed fail");
					}
		}
    
	@GetMapping("/userInfo")
	public CMRespDto<?> userInfo(HttpServletRequest request) {

		String username = request.getParameter("username");
		User user = userService.회원정보(username);		
		if(user != null) {
			return new CMRespDto<>(1,"userInfo complete",user);	
		}else {
			return new CMRespDto<>(1,"userInfo fail");
		}
		
	}


	
	
}
