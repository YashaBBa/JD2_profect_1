package com.tc.webapp01.service.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tc.webapp01.dao.DAOException;
import com.tc.webapp01.dao.DAOFactory;
import com.tc.webapp01.dao.UserDAO;
import com.tc.webapp01.entity.Applicant;
import com.tc.webapp01.entity.Request;
import com.tc.webapp01.entity.User;
import com.tc.webapp01.service.SQLFactory;
import com.tc.webapp01.service.ServiceException;
import com.tc.webapp01.service.UserService;

public class UserServiceImpl implements UserService {

    @Override
    public String authorisation(String login, String password) throws ServiceException, SQLException {


        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO userDAO = daoFactory.getUserDAO();
        String role = null;
        try {
            role = userDAO.authorization(login, password);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return role;


    }

    @Override
    public boolean registration(User user, Applicant applicant) throws SQLException {

        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO userDAO = daoFactory.getUserDAO();
        Boolean role = null;
        try {
            role = userDAO.registration(user, applicant);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return role;

    }

    @Override
    public boolean loginExists(String login) throws SQLException {
        Connection connection = SQLFactory.getConnection();
        String SQL = "SELECT login FROM applicationssystem.users where login=\"" + login + "\";";

        CallableStatement statement = connection.prepareCall(SQL);
        statement.execute();
        ResultSet resultSet = statement.getResultSet();
        return resultSet.next();
    }

    @Override
    public boolean saveUserData(Applicant applicant) {
        return false;
    }

    @Override
    public int getUserID(String login, String password) {
        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO userDAO = daoFactory.getUserDAO();
        int id=0;
        try {
            id = userDAO.getUserID(login, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public boolean sendApplicantUrequest(Request request1) throws SQLException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO userDAO = daoFactory.getUserDAO();
        boolean correctSave;
        correctSave=userDAO.saveUserRequests(request1);
        return correctSave;
    }


}
