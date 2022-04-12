package com.tc.webapp01.dao.impl;

import java.sql.*;
import java.util.Date;
import java.util.GregorianCalendar;

import com.tc.webapp01.pool.ConnectionPool;
import com.tc.webapp01.pool.ConnectionPoolException;
import com.tc.webapp01.dao.DAOException;
import com.tc.webapp01.dao.UserDAO;
import com.tc.webapp01.entity.Applicant;
import com.tc.webapp01.entity.Request;
import com.tc.webapp01.entity.User;

public class SQLUserDAO implements UserDAO {

    public static final String NAME = "name";
    private static final String INSERT_INTO_USERS_LOGIN_PASSWORD_VALUES_S_S = "insert into users (login,password) VALUES(?,?)";
    private static final String SELECT_USER_ID_FROM_USERS_WHERE_LOGIN_S = "SELECT user_id from users where login=?";
    private static final String INSERT_INTO_APPLICANTS_NAME_SURNAME_PASSPORT_STUDY_FORMAT_APPLICANT_ID_VALUES_S_S_S_S_S = "insert into applicants (name,surname,passport,study_format,applicant_id,privileges) VALUES(?,?,?,?,?,?);";
    private static final String UPDATE_USERS_SET_APPLICANT_ID_S_WHERE_USER_ID_S = "update users set applicant_id =? where user_id=?";
    private static final String INSERT_INTO_REQUEST_APPLICANTS_APPLICANT_ID_SUBJECTS_SUBJECTID_SCORE_SPECIALITY_ID_VALUES_S_S_S_S = "insert into request (applicants_applicant_id,subjects_subjectid,score,speciality_id,look,position,priority,date) VALUES(?,?,?,?,?,?,?,?);";
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
    private static final String INSERT_INTO_APPLICANTS_NAME_SURNAME_PASSPORT_STUDY_FORMAT_VALUES = "UPDATE  applicationssystem.applicants SET name=?,surname=?,passport=?,study_format=? WHERE applicant_id=?";

    private static final String UPDATE_APPLICATIONSSYSTEM_APPLICANTS_SET_RETING_WHERE_APPLICANT_ID = "UPDATE applicationssystem.applicants SET reting=? WHERE applicant_id=?";
    private static final String SELECT_REQUEST_ID_FROM_APPLICATIONSSYSTEM_REQUESTS_WHERE_APPLICANTS_APPLICANT_ID = "SELECT applicants_applicant_id,speciality_id FROM applicationssystem.request  WHERE applicants_applicant_id=? AND subjects_subjectid=6;";
    private static final String SQL_COMMAND_EXCEPTION = "SQL command exception";
    private static final String DATABASE_SERVER_CONNECTION_HAS_PROBLEM = "Database server connection has problem";
    private static final String UPDATE_APPLICATIONSSYSTEM_USERS_SET_PASSWORD_WHERE_USER_ID = "UPDATE applicationssystem.users SET password=? WHERE user_id=?";
    private static final String SELECT_PASSWORD_FROM_APPLICATIONSSYSTEM_USERS_WHERE_USER_ID = "SELECT password FROM applicationssystem.users WHERE user_id=?";
    private static final String PRIVILEGES = "privileges";
    private static final String SURNAME = "surname";
    private static final String PASSPORT = "passport";
    private static final String SELECT_FROM_APPLICATIONSSYSTEM_APPLICANTS_WHERE_APPLICANT_ID = "SELECT * FROM applicationssystem.applicants WHERE applicant_id=?";

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
            throw new DAOException(DATABASE_SERVER_CONNECTION_HAS_PROBLEM, e);
        } catch (SQLException e) {
            throw new DAOException(SQL_COMMAND_EXCEPTION, e);
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
            statement = connection.prepareStatement(SQL);
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
            throw new DAOException(DATABASE_SERVER_CONNECTION_HAS_PROBLEM, e);
        } catch (SQLException e) {
            throw new DAOException(SQL_COMMAND_EXCEPTION, e);
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
            throw new DAOException(DATABASE_SERVER_CONNECTION_HAS_PROBLEM, e);
        } catch (SQLException e) {
            throw new DAOException(SQL_COMMAND_EXCEPTION, e);
        } finally {
            connectionPool.closeConnection(connection, statement, resultSet);
        }

        return 0;
    }

    @Override
    public boolean saveUserRequests(Request request1) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String allUserRequests = SELECT_REQUEST_ID_FROM_APPLICATIONSSYSTEM_REQUESTS_WHERE_APPLICANTS_APPLICANT_ID;
        String SQL = INSERT_INTO_REQUEST_APPLICANTS_APPLICANT_ID_SUBJECTS_SUBJECTID_SCORE_SPECIALITY_ID_VALUES_S_S_S_S;
        GregorianCalendar calendar = (GregorianCalendar) GregorianCalendar.getInstance();
        Date date = calendar.getTime();


        int priority = 0;


        try {
            connection = connectionPool.takeConnection();
            statement = connection.prepareStatement(allUserRequests);
            statement.setInt(1, request1.getApplicantsID());
            statement.execute();
            resultSet = statement.getResultSet();

            if (resultSet != null) {
                while (resultSet.next()) {

                    priority++;


                }

            } else {
                priority = 1;
            }
            statement = connection.prepareStatement(SQL);
            statement.setInt(1, request1.getApplicantsID());
            statement.setInt(2, request1.getSubjectsID());
            statement.setInt(3, request1.getScore());
            statement.setInt(4, request1.getSpecialityID());
            statement.setInt(5, 1);
            statement.setInt(6, 1);
            statement.setInt(7, priority);
            statement.setDate(8, new java.sql.Date(date.getTime()));


            statement.execute();
            SQL = UPDATE_APPLICATIONSSYSTEM_APPLICANTS_SET_RETING_WHERE_APPLICANT_ID;
            statement = connection.prepareStatement(SQL);
            statement.setInt(1, request1.getScore());
            statement.setInt(2, request1.getApplicantsID());
            statement.execute();
        } catch (ConnectionPoolException e) {
            throw new DAOException(DATABASE_SERVER_CONNECTION_HAS_PROBLEM, e);
        } catch (SQLException e) {
            throw new DAOException(SQL_COMMAND_EXCEPTION, e);
        } finally {
            connectionPool.closeConnection(connection, statement, resultSet);
        }
        return true;
    }

    @Override
    public boolean loginExists(String login) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        String SQL = SELECT_LOGIN_FROM_APPLICATIONSSYSTEM_USERS_WHERE_LOGIN + login;
        try {
            connection = connectionPool.takeConnection();
            statement = connection.prepareStatement(SQL);
            statement.execute();
            ResultSet resultSet = statement.getResultSet();
            return resultSet.next();
        } catch (ConnectionPoolException e) {
           return false;

        } catch (SQLException e) {
            throw new DAOException(SQL_COMMAND_EXCEPTION, e);
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
            statement.setInt(5, applicant.getApplicantId());
            statement.execute();
        } catch (ConnectionPoolException e) {
            throw new DAOException(DATABASE_SERVER_CONNECTION_HAS_PROBLEM, e);
        } catch (SQLException e) {
            throw new DAOException(SQL_COMMAND_EXCEPTION, e);
        } finally {
            connectionPool.closeConnection(connection, statement);
        }


        return true;
    }

    @Override
    public Applicant getApplicantData(String id) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String SQL = SELECT_FROM_APPLICATIONSSYSTEM_APPLICANTS_WHERE_APPLICANT_ID;
        Applicant applicant = new Applicant();

        try {
            connection = connectionPool.takeConnection();
            statement = connection.prepareStatement(SQL);
            statement.setInt(1, Integer.parseInt(id));
            statement.execute();
            resultSet = statement.getResultSet();
            resultSet.next();
            applicant.setApplicantId(Integer.valueOf(id));
            applicant.setPrivileges(resultSet.getString(PRIVILEGES));
            applicant.setName(resultSet.getString(NAME));
            applicant.setSurname(resultSet.getString(SURNAME));
            applicant.setPassport(resultSet.getString(PASSPORT));


        } catch (ConnectionPoolException e) {
            throw new DAOException(DATABASE_SERVER_CONNECTION_HAS_PROBLEM, e);
        } catch (SQLException e) {
            throw new DAOException(SQL_COMMAND_EXCEPTION, e);
        } finally {
            connectionPool.closeConnection(connection, statement, resultSet);
        }

        return applicant;
    }

    @Override
    public Boolean changePassword(String password, String password1, int id) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String SQL = SELECT_PASSWORD_FROM_APPLICATIONSSYSTEM_USERS_WHERE_USER_ID;
        Applicant applicant = new Applicant();

        try {
            connection = connectionPool.takeConnection();
            statement = connection.prepareStatement(SQL);
            statement.setInt(1, id);
            statement.execute();
            resultSet = statement.getResultSet();
            resultSet.next();
            if (resultSet.getString(PASSWORD).equals(password)) {
                SQL = UPDATE_APPLICATIONSSYSTEM_USERS_SET_PASSWORD_WHERE_USER_ID;
                statement = connection.prepareStatement(SQL);
                statement.setString(1, password1);
                statement.setInt(2, id);
                statement.execute();
                return true;

            }else {
                return false;
            }


        } catch (ConnectionPoolException e) {
            throw new DAOException(DATABASE_SERVER_CONNECTION_HAS_PROBLEM, e);
        } catch (SQLException e) {
            throw new DAOException(SQL_COMMAND_EXCEPTION, e);
        } finally {
            connectionPool.closeConnection(connection, statement, resultSet);
        }


    }


}
