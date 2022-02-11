package com.tc.webapp01.dao.impl;

import java.sql.*;
import java.util.Map;

import com.tc.webapp01.dao.ConnectionPool;
import com.tc.webapp01.dao.ConnectionPoolException;
import com.tc.webapp01.dao.DAOException;
import com.tc.webapp01.dao.UserDAO;
import com.tc.webapp01.entity.Applicant;
import com.tc.webapp01.entity.Request;
import com.tc.webapp01.entity.User;
import com.tc.webapp01.service.SQLFactory;

public class SQLUserDAO implements UserDAO {
    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    public static final String SELECT_LOGIN_PASSWORD_ROLES_ID_FROM_APPLICATIONSSYSTEM_USERS = "SELECT login,password,roles_id FROM applicationssystem.users;";
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final String ROLES_ID = "roles_id";
    public static final String TITILE = "titile";
    public static final String SELECT_FROM_APPLICATIONSSYSTEM_USERS = "SELECT * FROM applicationssystem.users;";
    public static final String USER_ID = "user_id";

    @Override
    public String authorization(String login, String password) throws DAOException, SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionPool.takeConnection();
            String SQL = SELECT_LOGIN_PASSWORD_ROLES_ID_FROM_APPLICATIONSSYSTEM_USERS;
            statement = connection.prepareStatement(SQL);
            statement.execute();
            resultSet = statement.getResultSet();
            String rLogin;
            String rPassword;
            int rRoles_id = 0;
            while (resultSet.next()) {
                rLogin = resultSet.getString(LOGIN);
                rPassword = resultSet.getString(PASSWORD);
                rRoles_id = resultSet.getInt(ROLES_ID);
                if (rLogin.equals(login) && rPassword.equals(password)) {
                    SQL = "SELECT titile FROM applicationssystem.roles WHERE id=" + rRoles_id + ";";

                    statement = connection.prepareCall(SQL);
                    statement.execute();
                    resultSet = statement.getResultSet();
                    resultSet.next();
                    return resultSet.getString(TITILE);
                }
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
    public Boolean registration(User user, Applicant applicant) throws DAOException, SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = connectionPool.takeConnection();
            String SQL = String.format("insert into users (login,password) VALUES(\"%s\",\"%s\");", user.getLogin(), user.getPassword());
            statement = connection.prepareStatement(SQL);
            statement.execute();
            SQL = String.format("SELECT user_id from users where login=\"%s\"", user.getLogin());
            statement = connection.prepareStatement(SQL);
            statement.execute();

            resultSet = statement.getResultSet();
            resultSet.next();
            Integer id = resultSet.getInt("user_id");

            SQL = String.format("insert into applicants (name,surname,passport,study_format,applicant_id) VALUES(\"%s\",\"%s\",\"%s\",\"%s\",\"%s\");", applicant.getName(), applicant.getSurname(), applicant.getPassport(), applicant.getStudyFormat(), id);
            statement = connection.prepareStatement(SQL);
            statement.execute();

            SQL = String.format("update users set applicant_id = \"%s\" where user_id=\"%s\";", id, id);
            statement = connection.prepareCall(SQL);
            statement.execute();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Database server connection has problem", e);
        } catch (SQLException e) {
            throw new DAOException("User isn't exist", e);
        } finally {
            connectionPool.closeConnection(connection, statement, resultSet);
        }

        return false;
    }



  /* @Override
    public String authorization(String login, String password) throws DAOException, SQLException {
        Connection connection = SQLFactory.getConnection();
        String SQL = SELECT_LOGIN_PASSWORD_ROLES_ID_FROM_APPLICATIONSSYSTEM_USERS;

        CallableStatement statement = connection.prepareCall(SQL);
        statement.execute();
        ResultSet resultSet = statement.getResultSet();
        String rLogin;
        String rPassword;
        int rRoles_id = 0;

        while (resultSet.next()) {
            rLogin = resultSet.getString(LOGIN);
            rPassword = resultSet.getString(PASSWORD);
            rRoles_id = resultSet.getInt(ROLES_ID);
            if (rLogin.equals(login) && rPassword.equals(password)) {
                SQL = "SELECT titile FROM applicationssystem.roles WHERE id=" + rRoles_id + ";";

                statement = connection.prepareCall(SQL);
                statement.execute();
                resultSet = statement.getResultSet();
                resultSet.next();
                return resultSet.getString(TITILE);
            }
        }
        try {
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }*/

   /* @Override
    public Boolean registration(User user, Applicant applicant) throws DAOException, SQLException {
        Connection connection = SQLFactory.getConnection();
        String SQL = String.format("insert into users (login,password) VALUES(\"%s\",\"%s\");", user.getLogin(), user.getPassword());
        CallableStatement statement = connection.prepareCall(SQL);
        statement.execute();
        SQL = String.format("SELECT user_id from users where login=\"%s\"", user.getLogin());
        statement = connection.prepareCall(SQL);
        statement.execute();

        ResultSet resultSet = statement.getResultSet();
        resultSet.next();
        Integer id = resultSet.getInt("user_id");

        SQL = String.format("insert into applicants (name,surname,passport,study_format,applicant_id) VALUES(\"%s\",\"%s\",\"%s\",\"%s\",\"%s\");", applicant.getName(), applicant.getSurname(), applicant.getPassport(), applicant.getStudyFormat(), id);
        statement = connection.prepareCall(SQL);
        statement.execute();

        SQL = String.format("update users set applicant_id = \"%s\" where user_id=\"%s\";", id, id);
        statement = connection.prepareCall(SQL);
        statement.execute();
        try {
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }*/

    /* @Override
     public int getUserID(String login, String password) throws SQLException {
         Connection connection = SQLFactory.getConnection();
         String SQL = SELECT_FROM_APPLICATIONSSYSTEM_USERS;

         CallableStatement statement = connection.prepareCall(SQL);
         statement.execute();
         ResultSet resultSet = statement.getResultSet();
         String rLogin;
         String rPassword;


         while (resultSet.next()) {
             rLogin = resultSet.getString(LOGIN);
             rPassword = resultSet.getString(PASSWORD);
             if (rLogin.equals(login) && rPassword.equals(password)) {

                 return resultSet.getInt(USER_ID);
             }
         }
         try {
             resultSet.close();
             statement.close();
             connection.close();
         } catch (SQLException e) {
             System.out.println(e.getMessage());
         }
         return 0;
     }*/
    @Override
    public int getUserID(String login, String password) throws SQLException, DAOException {
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
            throw new DAOException("User isn't exist", e);
        } finally {
            connectionPool.closeConnection(connection, statement, resultSet);
        }




        return 0;
    }

    @Override
    public boolean saveUserRequests(Request request1) throws SQLException, DAOException {
        Connection connection = null;
        PreparedStatement statement = null;



        String SQL = String.format("insert into request (applicants_applicant_id,subjects_subjectid,score,speciality_id) VALUES(\"%s\",\"%s\",\"%s\",\"%s\");"
                , request1.getApplicantsID(), request1.getSubjectsID(), request1.getScore(), request1.getSpecialityID());

        try {
            connection=connectionPool.takeConnection();
            statement = connection.prepareStatement(SQL);
            statement.execute();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Database server connection has problem", e);
        } catch (SQLException e) {
            throw new DAOException("User isn't exist", e);
        } finally {
            connectionPool.closeConnection(connection, statement);
        }




        return true;
    }

    /*@Override
    public boolean saveUserRequests(Request request1) throws SQLException {
        Connection connection = SQLFactory.getConnection();

        String SQL = String.format("insert into request (applicants_applicant_id,subjects_subjectid,score,speciality_id) VALUES(\"%s\",\"%s\",\"%s\",\"%s\");"
                , request1.getApplicantsID(), request1.getSubjectsID(), request1.getScore(), request1.getSpecialityID());

        CallableStatement statement = connection.prepareCall(SQL);
        statement.execute();
        try {

            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return true;
    }*/


}
