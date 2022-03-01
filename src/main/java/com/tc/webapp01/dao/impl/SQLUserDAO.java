package com.tc.webapp01.dao.impl;

import java.sql.*;

import com.tc.webapp01.pool.ConnectionPool;
import com.tc.webapp01.pool.ConnectionPoolException;
import com.tc.webapp01.dao.DAOException;
import com.tc.webapp01.dao.UserDAO;
import com.tc.webapp01.entity.Applicant;
import com.tc.webapp01.entity.Request;
import com.tc.webapp01.entity.User;

public class SQLUserDAO implements UserDAO {
    private static final String INSERT_INTO_USERS_LOGIN_PASSWORD_VALUES_S_S = "insert into users (login,password) VALUES(?,?)";
    private static final String SELECT_USER_ID_FROM_USERS_WHERE_LOGIN_S = "SELECT user_id from users where login=?";
    private static final String INSERT_INTO_APPLICANTS_NAME_SURNAME_PASSPORT_STUDY_FORMAT_APPLICANT_ID_VALUES_S_S_S_S_S = "insert into applicants (name,surname,passport,study_format,applicant_id,privileges) VALUES(?,?,?,?,?,?);";
    private static final String UPDATE_USERS_SET_APPLICANT_ID_S_WHERE_USER_ID_S = "update users set applicant_id =? where user_id=?";
    private static final String INSERT_INTO_REQUEST_APPLICANTS_APPLICANT_ID_SUBJECTS_SUBJECTID_SCORE_SPECIALITY_ID_VALUES_S_S_S_S = "insert into request (applicants_applicant_id,subjects_subjectid,score,speciality_id) VALUES(?,?,?,?)";
    private static final String GET_USER_BY_LOGIN_AND_PASSWORD = "SELECT login,password,roles_id FROM applicationssystem.users WHERE login = ? AND password = ?";

    private static final String SELECT_LOGIN_PASSWORD_ROLES_ID_FROM_APPLICATIONSSYSTEM_USERS = "SELECT login,password,roles_id FROM applicationssystem.users;";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String ROLES_ID = "roles_id";
    private static final String TITILE = "titile";
    private static final String SELECT_FROM_APPLICATIONSSYSTEM_USERS = "SELECT * FROM applicationssystem.users;";
    private static final String USER_ID = "user_id";
    private static final String SELECT_TITILE_FROM_APPLICATIONSSYSTEM_ROLES_WHERE_ID = "SELECT titile FROM applicationssystem.roles WHERE id=";
    private static final String SELECT_LOGIN_FROM_APPLICATIONSSYSTEM_USERS_WHERE_LOGIN = "SELECT login FROM applicationssystem.users where login=";
    private static final String INSERT_INTO_APPLICANTS_NAME_SURNAME_PASSPORT_STUDY_FORMAT_VALUES = "insert into applicants (name,surname,passport,study_format) VALUES(?,?,?,?)";
    private static final String UPDATE_APPLICATIONSSYSTEM_APPLICANTS_SET_RETING_WHERE_APPLICANT_ID = "UPDATE applicationssystem.applicants SET reting=? WHERE applicant_id=?";

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public String authorization(String login, String password) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int rRoles_id = 0;
        String SQL;

        try {
            connection = connectionPool.takeConnection();
            statement = connection.prepareStatement(GET_USER_BY_LOGIN_AND_PASSWORD);

            statement.setString(1, login);
            statement.setString(2, password);
            statement.execute();
            resultSet = statement.getResultSet();

            if (resultSet.next()) {
                rRoles_id = resultSet.getInt(ROLES_ID);
                SQL = SELECT_TITILE_FROM_APPLICATIONSSYSTEM_ROLES_WHERE_ID + rRoles_id;

                statement = connection.prepareCall(SQL);
                statement.execute();
                resultSet = statement.getResultSet();
                resultSet.next();
                return resultSet.getString(TITILE);
            }

        } catch (ConnectionPoolException e) {
            throw new DAOException("Database server connection has problem", e);
        } catch (SQLException e) {
            throw new DAOException("User isn't exist", e);
        } finally {
            connectionPool.closeConnection(connection, statement, resultSet);
        }
        return null;
    }

    @Override
    public Boolean registration(User user, Applicant applicant) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = connectionPool.takeConnection();

            String SQL = INSERT_INTO_USERS_LOGIN_PASSWORD_VALUES_S_S;
            statement = connection.prepareStatement(SQL);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.execute();

            SQL = SELECT_USER_ID_FROM_USERS_WHERE_LOGIN_S;
            statement = connection.prepareStatement(SQL);
            statement.setString(1, user.getLogin());
            statement.execute();

            resultSet = statement.getResultSet();
            resultSet.next();
            Integer id = resultSet.getInt(USER_ID);


            SQL = INSERT_INTO_APPLICANTS_NAME_SURNAME_PASSPORT_STUDY_FORMAT_APPLICANT_ID_VALUES_S_S_S_S_S;
            statement=connection.prepareStatement(SQL);
            statement.setString(1, applicant.getName());
            statement.setString(2, applicant.getSurname());
            statement.setString(3, applicant.getPassport());
            statement.setString(4, applicant.getStudyFormat());
            statement.setInt(5, id);
            statement.setString(6, applicant.getPrivileges());
            statement.execute();


            SQL = UPDATE_USERS_SET_APPLICANT_ID_S_WHERE_USER_ID_S;
            statement = connection.prepareStatement(SQL);
            statement.setInt(1, id);
            statement.setInt(2, id);
            statement.execute();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Database server connection has problem", e);
        } catch (SQLException e) {
            throw new DAOException("User already exist", e);
        } finally {
            connectionPool.closeConnection(connection, statement, resultSet);
        }

        return false;
    }

    @Override
    public int getUserID(String login, String password) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String SQL = SELECT_FROM_APPLICATIONSSYSTEM_USERS;

        try {
            connection = connectionPool.takeConnection();
            statement = connection.prepareStatement(SQL);
            statement.execute();
            resultSet = statement.getResultSet();
            String rLogin;
            String rPassword;


            while (resultSet.next()) {
                rLogin = resultSet.getString(LOGIN);
                rPassword = resultSet.getString(PASSWORD);
                if (rLogin.equals(login) && rPassword.equals(password)) {

                    return resultSet.getInt(USER_ID);
                }
            }
        } catch (ConnectionPoolException e) {
            throw new DAOException("Database server connection has problem", e);
        } catch (SQLException e) {
            throw new DAOException("SQL command exception", e);
        } finally {
            connectionPool.closeConnection(connection, statement, resultSet);
        }

        return 0;
    }

    @Override
    public boolean saveUserRequests(Request request1) throws  DAOException {
        Connection connection = null;
        PreparedStatement statement = null;

        String SQL = INSERT_INTO_REQUEST_APPLICANTS_APPLICANT_ID_SUBJECTS_SUBJECTID_SCORE_SPECIALITY_ID_VALUES_S_S_S_S;


        try {
            connection = connectionPool.takeConnection();
            statement = connection.prepareStatement(SQL);
            statement.setInt(1, request1.getApplicantsID());
            statement.setInt(2, request1.getSubjectsID());
            statement.setInt(3, request1.getScore());
            statement.setInt(4, request1.getSpecialityID());
            statement.execute();
            SQL = UPDATE_APPLICATIONSSYSTEM_APPLICANTS_SET_RETING_WHERE_APPLICANT_ID;
            statement = connection.prepareStatement(SQL);
            statement.setInt(1, request1.getScore());
            statement.setInt(2, request1.getApplicantsID());
            statement.execute();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Database server connection has problem", e);
        } catch (SQLException e) {
            throw new DAOException("SQL command exception", e);
        } finally {
            connectionPool.closeConnection(connection, statement);
        }
        return true;
    }

    @Override
    public boolean loginExists(String login) throws DAOException{
        Connection connection = null;
        PreparedStatement statement = null;
        String SQL = SELECT_LOGIN_FROM_APPLICATIONSSYSTEM_USERS_WHERE_LOGIN + login;
        try {
            connection = connectionPool.takeConnection();
            statement = connection.prepareStatement(SQL);
            statement.execute();
            ResultSet resultSet = statement.getResultSet();
            return resultSet.next();
        } catch (SQLException e) {
            return false;
        } catch (ConnectionPoolException e) {
            throw new DAOException("Database server connection has problem", e);
        } finally {
            connectionPool.closeConnection(connection, statement);
        }

    }

    @Override
    public boolean saveApplicantData(Applicant applicant) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;

        String SQL = INSERT_INTO_APPLICANTS_NAME_SURNAME_PASSPORT_STUDY_FORMAT_VALUES;
        try {
            connection = connectionPool.takeConnection();
            statement = connection.prepareStatement(SQL);
            statement.setString(1, applicant.getName());
            statement.setString(2, applicant.getSurname());
            statement.setString(3, applicant.getPassport());
            statement.setString(4, applicant.getStudyFormat());
            statement.execute();
        } catch (SQLException e) {
            throw new DAOException("SQL command exception", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("Database server connection has problem", e);
        } finally {
            connectionPool.closeConnection(connection, statement);
        }


        return true;
    }


}
