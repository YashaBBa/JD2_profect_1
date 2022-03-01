package com.tc.webapp01.service;

import com.tc.webapp01.entity.Applicant;
import com.tc.webapp01.entity.Request;
import com.tc.webapp01.entity.User;

public interface UserService {

    String authorisation(String login, String password) throws ServiceException;

    boolean registration(User newUser,Applicant applicant) throws ServiceException;

    boolean loginExists(String login) throws  ServiceException;

    boolean saveUserData(Applicant applicant);


    int getUserID(String login, String login1) throws ServiceException;

    boolean sendApplicantUrequest(Request request1) throws  ServiceException;
    public Boolean saveApplicantData(Applicant applicant) throws  ServiceException;
}
