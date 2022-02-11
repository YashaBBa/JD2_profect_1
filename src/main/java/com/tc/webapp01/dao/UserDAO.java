package com.tc.webapp01.dao;

import com.tc.webapp01.entity.Applicant;
import com.tc.webapp01.entity.Request;
import com.tc.webapp01.entity.User;

import java.sql.SQLException;

public interface UserDAO {
	String authorization(String login, String password) throws DAOException, SQLException;
	Boolean registration(User user, Applicant applicant)throws DAOException, SQLException;


    int getUserID(String login, String password) throws SQLException, DAOException;

	boolean saveUserRequests(Request request1) throws SQLException, DAOException;
}
