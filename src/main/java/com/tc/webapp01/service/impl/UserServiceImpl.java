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

    private static final String DATABASE_SERVER_CONNECTION_HAS_PROBLEM = "Database server connection has problem";
    private static final String REGISTRATION_EXCEPTION = "Registration exception";
    private static final String AUTHORIZATION_EXCEPTION = "Authorization exception";

    @Override
    public String authorisation(String login, String password) throws ServiceException {


        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO userDAO = daoFactory.getUserDAO();
        String role = null;
        try {
            role = userDAO.authorization(login, password);
        } catch (DAOException e) {
            throw new ServiceException(AUTHORIZATION_EXCEPTION, e);
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
            throw new ServiceException(REGISTRATION_EXCEPTION, e);
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
            throw new ServiceException(DATABASE_SERVER_CONNECTION_HAS_PROBLEM, e);
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
            throw new ServiceException(DATABASE_SERVER_CONNECTION_HAS_PROBLEM, e);
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
            throw new ServiceException(DATABASE_SERVER_CONNECTION_HAS_PROBLEM, e);
        }

    }

    @Override
    public Boolean saveApplicantData(Applicant applicant) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO userDAO = daoFactory.getUserDAO();
        boolean saveApplicant;
        try {
            saveApplicant = userDAO.saveApplicantData(applicant);
        } catch (DAOException e) {
            throw new ServiceException(DATABASE_SERVER_CONNECTION_HAS_PROBLEM, e);
        }

        return saveApplicant;
    }

    @Override
    public Applicant getUserData(String id) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO userDAO = daoFactory.getUserDAO();
        Applicant applicant = null;
        try {
            applicant = userDAO.getApplicantData(id);
        } catch (DAOException e) {
            throw new ServiceException(DATABASE_SERVER_CONNECTION_HAS_PROBLEM, e);
        }

        return applicant;
    }

    @Override
    public Boolean changePassword(String password, String password1, int id) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO userDAO = daoFactory.getUserDAO();
        Boolean change = false;
        try {
            change = userDAO.changePassword(password, password1,id);
        } catch (DAOException e) {
            throw new ServiceException(DATABASE_SERVER_CONNECTION_HAS_PROBLEM, e);
        }

        return change;
    }


}
