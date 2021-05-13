package web;

import anno.PostMapping;
import service.UserService;
import web.dto.CMRespDto;
import web.dto.JoinReqDto;

public class PostController<T> {
	
	UserService userService = new UserService();
	
    public PostController() {
        super();
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


	
	
}
