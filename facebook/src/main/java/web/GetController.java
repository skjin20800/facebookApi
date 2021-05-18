package web;

import java.util.List;

import anno.GetMapping;
import service.BoardService;
import service.UserService;
import web.dto.CMRespDto;
import web.dto.boards.ListRespDto;

//Get은 Request받아서 사용가능하다 [이번 테스트에서는 사용 x]
public class GetController {
	
	UserService userService = UserService.getInstance();
	BoardService boardService = BoardService.getInstance();
	
    public GetController() {
        super();
    }
    
    // 
	@GetMapping("/feed")
	public CMRespDto<?> feed() {
		List<ListRespDto> boardDetailDto = boardService.피드();
			 
			if(boardDetailDto != null) {
				return new CMRespDto<>(1, "feed complete", boardDetailDto);
			}else {
				return new CMRespDto<>(-1,"feed fail");
					}
		}
    



	
	
}
