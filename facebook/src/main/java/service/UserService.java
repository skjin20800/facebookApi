package service;

import domain.user.User;
import repository.UserDao;
import web.dto.auth.JoinReqDto;
import web.dto.auth.LoginReqDto;

public class UserService {
	
	private static UserService instance = new UserService();

	public static UserService getInstance() {
		return instance;
	}

	UserDao userDao= UserDao.getInstance();

	public int 회원가입(JoinReqDto dto) {
		return userDao.save(dto);
	}
	
	public User 로그인(LoginReqDto dto) {

		return userDao.findByUsernameAndPassword(dto);
	}
	
	public User 회원정보(String Username) {

		return userDao.findByUsername(Username);
	}



	
}
