package web;

import anno.GetMapping;
import service.UserService;
import web.dto.CMRespDto;
import web.dto.JoinReqDto;

public class FrontController<T> {
	
	UserService userService = new UserService();
	
    public FrontController() {
        super();
    }
    
	@GetMapping("/joinReq")
	public CMRespDto<?> join(JoinReqDto dto) {
		System.out.println("회원가입실행"+dto);
		int result = userService.회원가입(dto);
		
		return new CMRespDto<>(result,"회원가입 완료");
	}




	
	
}
