package web;

import anno.RequestMapping;
import domain.user.User;
import service.UserService;
import web.dto.CMRespDto;
import web.dto.JoinReqDto;

public class FrontController<T> {
	
	UserService userService = new UserService();
	
    public FrontController() {
        super();
    }
    
	@RequestMapping("/joinReq")
	public CMRespDto<?> join(JoinReqDto dto) {
		System.out.println("회원가입실행"+dto);
		User user = userService.회원가입(dto);

		return new CMRespDto<>(1,"실행됨");
	}




	
	
}
