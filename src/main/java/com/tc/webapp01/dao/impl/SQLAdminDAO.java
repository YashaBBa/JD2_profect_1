package com.tc.webapp01.dao.impl;

import com.tc.webapp01.dao.AdminDAO;
import com.tc.webapp01.entity.Applicant;
import com.tc.webapp01.entity.Properties;
import com.tc.webapp01.entity.Request;
import com.tc.webapp01.entity.Speciality;
import com.tc.webapp01.service.SQLFactory;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLAdminDAO implements AdminDAO {
    @Override
    public List<Request> getSpecialistsList() throws SQLException {
        Connection connection = SQLFactory.getConnection();
        String SQL = "SELECT * FROM applicationssystem.request;";

        CallableStatement statement = connection.prepareCall(SQL);
        statement.execute();
        ResultSet resultSet = statement.getResultSet();
        List<Request> requestList = new ArrayList<>();
        while (resultSet.next()) {
            Request request = new Request();

            request.setApplicant(getApplicantData(resultSet.getInt("applicants_applicant_id")));
            request.setSpeciality(getSpecialityName(resultSet.getInt("speciality_id")));
            request.setSubject(getSubjectName(resultSet.getInt("subjects_subjectid")));
            request.setSpecialityID(resultSet.getInt("speciality_id"));
            request.setRequestID(resultSet.getInt("request_id"));
            request.setScore(resultSet.getInt("score"));
            request.setSubjectsID(resultSet.getInt("subjects_subjectid"));
            request.setApplicantsID(resultSet.getInt("applicants_applicant_id"));

            requestList.add(request);

        }
        try {
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return requestList;
    }

    @Override
    public List<Applicant> getApplicantsList() throws SQLException {
        return null;
    }

    public Applicant getApplicantData(int applicantID) throws SQLException {
        Connection connection = SQLFactory.getConnection();
        String SQL = "SELECT * FROM applicationssystem.applicants;";

        CallableStatement statement = connection.prepareCall(SQL);
        statement.execute();
        ResultSet resultSet = statement.getResultSet();

        while (resultSet.next()) {
            if (applicantID == resultSet.getInt("applicant_id")) {
                Applicant applicant = new Applicant();

                applicant.setApplicantSpeciality(resultSet.getInt("speciality_id"));
                applicant.setApplicant_id(resultSet.getInt("applicant_id"));
                applicant.setName(resultSet.getString("name"));
                applicant.setSurname(resultSet.getString("surname"));
                applicant.setPassport(resultSet.getString("passport"));
                return applicant;
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
    public String getSpecialityName(int specID) throws SQLException {
        Connection connection = SQLFactory.getConnection();
        String SQL = "SELECT * FROM applicationssystem.specialty;";

        CallableStatement statement = connection.prepareCall(SQL);
        statement.execute();
        ResultSet resultSet = statement.getResultSet();

        while (resultSet.next()) {
            {
                if (specID == resultSet.getInt("idspecialty")) {
                    return resultSet.getString("specialty");

                }

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
    public Boolean applyRequest(int specID, int applicantID) throws SQLException {
        Connection connection = SQLFactory.getConnection();
        String SQL = "UPDATE applicationssystem.users SET roles_id=3 WHERE user_id=" + applicantID + ";";

        CallableStatement statement = connection.prepareCall(SQL);
        statement.execute();
        System.out.println(specID);

        SQL = "UPDATE applicationssystem.applicants SET speciality_id=" + specID + " WHERE applicant_id=" + applicantID + ";";
        statement = connection.prepareCall(SQL);
        statement.execute();

        try {

            statement.close();
            connection.close();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public Boolean deleteRequest(int applicantID) throws SQLException {
        Connection connection = SQLFactory.getConnection();
        String SQL = "DELETE FROM applicationssystem.request WHERE applicants_applicant_id=" + applicantID + ";";

        CallableStatement statement = connection.prepareCall(SQL);
        statement.execute();
        try {

            statement.close();
            connection.close();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public Boolean deleteRequest(int applicantID, int specialityID) throws SQLException {
        Connection connection = SQLFactory.getConnection();
        String SQL = "DELETE FROM applicationssystem.request WHERE applicants_applicant_id=" + applicantID + " AND speciality_id=" + specialityID + ";";

        CallableStatement statement = connection.prepareCall(SQL);
        statement.execute();
        try {

            statement.close();
            connection.close();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;

    }

    @Override
    public int saveAndGetNewSpecialityID(Speciality speciality) throws SQLException {
        Connection connection = SQLFactory.getConnection();
        String SQL = String.format("INSERT INTO applicationssystem.specialty(specialty,minimum_score,faculties_idfaculties) VALUES(\"%s\",\"%s\",\"%s\");"
                , speciality.getSpeciality(), speciality.getScore(), speciality.getFacultyID());

        CallableStatement statement = connection.prepareCall(SQL);
        statement.execute();
        SQL = "SELECT * FROM applicationssystem.specialty;";
        statement = connection.prepareCall(SQL);
        statement.execute();
        ResultSet resultSet = statement.getResultSet();

        while (resultSet.next()) {
            {
                if (speciality.getSpeciality().equals(resultSet.getString("specialty"))) {
                    return resultSet.getInt("idspecialty");

                }
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
    public void savePropetriesForSpeciality(Properties properties, int specialityID) throws SQLException {
        Connection connection = SQLFactory.getConnection();
        String SQL = String.format("INSERT INTO applicationssystem.scpecialty_properties(preferential_places,places,cost,specialty_id) VALUES(\"%s\",\"%s\",\"%s\",\"%s\");"
                ,properties.getPriferentPlacec(),properties.getPlaces(),properties.getCost(),specialityID);

        CallableStatement statement = connection.prepareCall(SQL);
        statement.execute();

        try {

            statement.close();
            connection.close();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }



    @Override
    public void saveSpecialityAndSubjectsConnection(Integer listOfSubjects, int minScore, int specialityID) throws SQLException {
        Connection connection = SQLFactory.getConnection();
        String SQL = String.format("INSERT INTO applicationssystem.scpecialty_properties_has_subjects(scpecialty_properties_idtable1,subjects_subjectid,minimum_possitive_score) VALUES(\"%s\",\"%s\",\"%s\");"
                ,specialityID,listOfSubjects,minScore);

        CallableStatement statement = connection.prepareCall(SQL);
        statement.execute();
        try {

            statement.close();
            connection.close();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public String getSubjectName(int specID) throws SQLException {
        Connection connection = SQLFactory.getConnection();
        String SQL = "SELECT * FROM applicationssystem.subjects;";

        CallableStatement statement = connection.prepareCall(SQL);
        statement.execute();
        ResultSet resultSet = statement.getResultSet();

        while (resultSet.next()) {
            {
                if (specID == resultSet.getInt("subjectid")) {
                    return resultSet.getString("subject");

                }

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

}
