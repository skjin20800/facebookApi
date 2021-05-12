package service;

import domain.user.User;
import repository.UserDao;
import web.dto.JoinReqDto;

public class UserService {

	UserDao userDao= new UserDao();

	public int 회원가입(JoinReqDto dto) {
		return userDao.save(dto);
	}
	
	public User 로그인() {

		return null;
	}



	
}
