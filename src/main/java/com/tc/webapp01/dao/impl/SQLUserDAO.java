package com.tc.webapp01.dao.impl;

import java.sql.*;
import java.util.Map;

import com.tc.webapp01.dao.DAOException;
import com.tc.webapp01.dao.UserDAO;
import com.tc.webapp01.entity.Applicant;
import com.tc.webapp01.entity.Request;
import com.tc.webapp01.entity.User;
import com.tc.webapp01.service.SQLFactory;

public class SQLUserDAO implements UserDAO {


    @Override
    public String authorization(String login, String password) throws DAOException, SQLException {
        Connection connection = SQLFactory.getConnection();
        String SQL = "SELECT login,password,roles_id FROM applicationssystem.users;";

        CallableStatement statement = connection.prepareCall(SQL);
        statement.execute();
        ResultSet resultSet = statement.getResultSet();
        String rLogin;
        String rPassword;
        int rRoles_id = 0;

        while (resultSet.next()) {
            rLogin = resultSet.getString("login");
            rPassword = resultSet.getString("password");
            rRoles_id = resultSet.getInt("roles_id");
            if (rLogin.equals(login) && rPassword.equals(password)) {
                SQL = "SELECT titile FROM applicationssystem.roles WHERE id=" + rRoles_id + ";";

                statement = connection.prepareCall(SQL);
                statement.execute();
                resultSet = statement.getResultSet();
                resultSet.next();
                return resultSet.getString("titile");
            }
        }
        try {
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
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
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return false;
    }

    @Override
    public int getUserID(String login, String password) throws SQLException {
        Connection connection = SQLFactory.getConnection();
        String SQL = "SELECT * FROM applicationssystem.users;";

        CallableStatement statement = connection.prepareCall(SQL);
        statement.execute();
        ResultSet resultSet = statement.getResultSet();
        String rLogin;
        String rPassword;


        while (resultSet.next()) {
            rLogin = resultSet.getString("login");
            rPassword = resultSet.getString("password");
            if (rLogin.equals(login) && rPassword.equals(password)) {

                return resultSet.getInt("user_id");
            }
        }
        try {
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return 0;
    }

    @Override
    public boolean saveUserRequests(Request request1) throws SQLException {
        Connection connection = SQLFactory.getConnection();

        String SQL = String.format("insert into request (applicants_applicant_id,subjects_subjectid,score,speciality_id) VALUES(\"%s\",\"%s\",\"%s\",\"%s\");"
                , request1.getApplicantsID(), request1.getSubjectsID(), request1.getScore(),request1.getSpecialityID());

        CallableStatement statement = connection.prepareCall(SQL);
        statement.execute();
        try {

            statement.close();
            connection.close();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return true;
    }


}
