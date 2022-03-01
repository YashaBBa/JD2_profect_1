package com.tc.webapp01.service.impl;

import com.tc.webapp01.dao.DAOException;
import com.tc.webapp01.dao.DAOFactory;
import com.tc.webapp01.dao.UserDAO;
import com.tc.webapp01.entity.Applicant;
import com.tc.webapp01.entity.Request;
import com.tc.webapp01.entity.User;
import com.tc.webapp01.service.ServiceException;
import com.tc.webapp01.service.UserService;

public class UserServiceImpl implements UserService {

    @Override
    public String authorisation(String login, String password) throws ServiceException {


        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO userDAO = daoFactory.getUserDAO();
        String role = null;
        try {
            role = userDAO.authorization(login, password);
        } catch (DAOException e) {
            throw new ServiceException("Authorization exception",e);
        }
        return role;


    }

    @Override
    public boolean registration(User user, Applicant applicant) throws ServiceException {

        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO userDAO = daoFactory.getUserDAO();
        Boolean role = null;
        try {
            role = userDAO.registration(user, applicant);
        } catch (DAOException e) {
            throw new ServiceException("Registration exception",e);
        }
        return role;

    }

    @Override
    public boolean loginExists(String login) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO userDAO = daoFactory.getUserDAO();
        try {
            return userDAO.loginExists(login);
        } catch (DAOException e) {
            throw new ServiceException("Database server connection has problem", e);
        }
    }

    @Override
    public boolean saveUserData(Applicant applicant) {
        return false;
    }

    @Override
    public int getUserID(String login, String password) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO userDAO = daoFactory.getUserDAO();
        int id = 0;
        try {
            id = userDAO.getUserID(login, password);
        } catch (DAOException e) {
            throw new ServiceException("Database server connection has problem", e);
        }
        return id;
    }

    @Override
    public boolean sendApplicantUrequest(Request request1) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO userDAO = daoFactory.getUserDAO();
        boolean correctSave;
        try {
            correctSave = userDAO.saveUserRequests(request1);
            return correctSave;
        } catch (DAOException e) {
            throw new ServiceException("Database server connection has problem", e);
        }

    }

    @Override
    public Boolean saveApplicantData(Applicant applicant) throws ServiceException{
        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO userDAO = daoFactory.getUserDAO();
        boolean saveApplicant;
        try {
            saveApplicant = userDAO.saveApplicantData(applicant);
        } catch (DAOException e) {
            throw new ServiceException("Database server connection has problem", e);
        }

        return saveApplicant;
    }


}
