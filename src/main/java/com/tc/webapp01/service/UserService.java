package com.tc.webapp01.service;

import com.tc.webapp01.entity.Applicant;
import com.tc.webapp01.entity.Request;
import com.tc.webapp01.entity.User;

import java.sql.SQLException;

public interface UserService {

    String authorisation(String login, String password) throws ServiceException, SQLException;

    boolean registration(User newUser,Applicant applicant) throws ServiceException, SQLException;

    boolean loginExists(String login) throws SQLException;

    boolean saveUserData(Applicant applicant);


    int getUserID(String login, String login1);

    boolean sendApplicantUrequest(Request request1) throws SQLException;
}
