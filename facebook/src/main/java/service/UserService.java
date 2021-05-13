package service;

import domain.user.User;
import repository.UserDao;
import web.dto.JoinReqDto;
import web.dto.LoginReqDto;

public class UserService {

	UserDao userDao= new UserDao();

	public int 회원가입(JoinReqDto dto) {
		return userDao.save(dto);
	}
	
	public User 로그인(LoginReqDto dto) {

		return userDao.findByUsernameAndPassword(dto);
	}



	
}
