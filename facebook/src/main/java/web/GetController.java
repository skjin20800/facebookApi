package web;

import javax.servlet.http.HttpServletRequest;

import anno.GetMapping;
import domain.user.User;
import service.UserService;
import web.dto.CMRespDto;

public class GetController<T> {
	
	UserService userService = new UserService();
	
    public GetController() {
        super();
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
