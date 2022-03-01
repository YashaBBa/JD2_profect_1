package com.tc.webapp01.dao;

import com.tc.webapp01.entity.Applicant;
import com.tc.webapp01.entity.Request;
import com.tc.webapp01.entity.User;

import java.sql.SQLException;

public interface UserDAO {
	String authorization(String login, String password) throws DAOException;
	Boolean registration(User user, Applicant applicant) throws DAOException;


    int getUserID(String login, String password) throws DAOException;

	boolean saveUserRequests(Request request1) throws  DAOException;

    boolean loginExists(String login) throws DAOException;


    boolean saveApplicantData(Applicant applicant) throws DAOException;
}

