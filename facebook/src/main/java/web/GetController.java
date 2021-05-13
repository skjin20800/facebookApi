package web;

import anno.GetMapping;
import anno.PostMapping;
import service.UserService;
import web.dto.CMRespDto;
import web.dto.JoinReqDto;

public class GetController<T> {
	
	UserService userService = new UserService();
	
    public GetController() {
        super();
    }
    
	@GetMapping("/test")
	public CMRespDto<?> test() {

			return new CMRespDto<>(-1,"로그인완료 get");
	
	}


	
	
}
