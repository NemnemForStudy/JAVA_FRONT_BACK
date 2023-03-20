package service;

import database.DAO.UserDAO;

public class UserService {
	private UserDAO userDAO;
	
	public UserService() {
		this.userDAO = new UserDAO();
	}
}
