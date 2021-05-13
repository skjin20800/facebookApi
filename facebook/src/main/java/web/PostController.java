package web;

import anno.PostMapping;
import domain.user.User;
import service.UserService;
import web.dto.CMRespDto;
import web.dto.JoinReqDto;
import web.dto.LoginReqDto;

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


