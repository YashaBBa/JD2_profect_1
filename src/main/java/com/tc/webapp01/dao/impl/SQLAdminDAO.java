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

    public static final String SELECT_FROM_APPLICATIONSSYSTEM_REQUEST = "SELECT * FROM applicationssystem.request;";
    public static final String SELECT_FROM_APPLICATIONSSYSTEM_APPLICANTS = "SELECT * FROM applicationssystem.applicants;";
    public static final String SELECT_FROM_APPLICATIONSSYSTEM_SPECIALTY = "SELECT * FROM applicationssystem.specialty;";
    public static final String SELECT_FROM_APPLICATIONSSYSTEM_SPECIALTY1 = "SELECT * FROM applicationssystem.specialty;";
    public static final String SPECIALTY = "specialty";
    public static final String IDSPECIALTY = "idspecialty";
    public static final String SELECT_FROM_APPLICATIONSSYSTEM_SUBJECTS = "SELECT * FROM applicationssystem.subjects;";
    public static final String APPLICANTS_APPLICANT_ID = "applicants_applicant_id";
    public static final String SPECIALITY_ID = "speciality_id";
    public static final String SUBJECTS_SUBJECTID = "subjects_subjectid";
    public static final String REQUEST_ID = "request_id";
    public static final String SCORE = "score";
    public static final String APPLICANT_ID = "applicant_id";
    public static final String SPECIALITY_ID1 = "speciality_id";
    public static final String APPLICANT_ID1 = "applicant_id";
    public static final String NAME = "name";
    public static final String SURNAME = "surname";
    public static final String PASSPORT = "passport";
    public static final String IDSPECIALTY1 = "idspecialty";
    public static final String SPECIALTY1 = "specialty";
    public static final String SUBJECTID = "subjectid";
    public static final String SUBJECT = "subject";

    @Override
    public List<Request> getSpecialistsList() throws SQLException {
        Connection connection = SQLFactory.getConnection();
        String SQL = SELECT_FROM_APPLICATIONSSYSTEM_REQUEST;

        CallableStatement statement = connection.prepareCall(SQL);
        statement.execute();
        ResultSet resultSet = statement.getResultSet();
        List<Request> requestList = new ArrayList<>();
        while (resultSet.next()) {
            Request request = new Request();

            request.setApplicant(getApplicantData(resultSet.getInt(APPLICANTS_APPLICANT_ID)));
            request.setSpeciality(getSpecialityName(resultSet.getInt(SPECIALITY_ID)));
            request.setSubject(getSubjectName(resultSet.getInt(SUBJECTS_SUBJECTID)));
            request.setSpecialityID(resultSet.getInt(SPECIALITY_ID));
            request.setRequestID(resultSet.getInt(REQUEST_ID));
            request.setScore(resultSet.getInt(SCORE));
            request.setSubjectsID(resultSet.getInt(SUBJECTS_SUBJECTID));
            request.setApplicantsID(resultSet.getInt(APPLICANTS_APPLICANT_ID));

            requestList.add(request);

        }
        try {
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
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
        String SQL = SELECT_FROM_APPLICATIONSSYSTEM_APPLICANTS;

        CallableStatement statement = connection.prepareCall(SQL);
        statement.execute();
        ResultSet resultSet = statement.getResultSet();

        while (resultSet.next()) {
            if (applicantID == resultSet.getInt(APPLICANT_ID)) {
                Applicant applicant = new Applicant();

                applicant.setApplicantSpeciality(resultSet.getInt(SPECIALITY_ID1));
                applicant.setApplicant_id(resultSet.getInt(APPLICANT_ID1));
                applicant.setName(resultSet.getString(NAME));
                applicant.setSurname(resultSet.getString(SURNAME));
                applicant.setPassport(resultSet.getString(PASSPORT));
                return applicant;
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

    }

    @Override
    public String getSpecialityName(int specID) throws SQLException {
        Connection connection = SQLFactory.getConnection();
        String SQL = SELECT_FROM_APPLICATIONSSYSTEM_SPECIALTY;

        CallableStatement statement = connection.prepareCall(SQL);
        statement.execute();
        ResultSet resultSet = statement.getResultSet();

        while (resultSet.next()) {
            {
                if (specID == resultSet.getInt(IDSPECIALTY1)) {
                    return resultSet.getString(SPECIALTY1);

                }

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
        } catch (SQLException e) {
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
        } catch (SQLException e) {
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
        } catch (SQLException e) {
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
        SQL = SELECT_FROM_APPLICATIONSSYSTEM_SPECIALTY1;
        statement = connection.prepareCall(SQL);
        statement.execute();
        ResultSet resultSet = statement.getResultSet();

        while (resultSet.next()) {
            {
                if (speciality.getSpeciality().equals(resultSet.getString(SPECIALTY))) {
                    return resultSet.getInt(IDSPECIALTY);

                }
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
    }

    @Override
    public void savePropetriesForSpeciality(Properties properties, int specialityID) throws SQLException {
        Connection connection = SQLFactory.getConnection();
        String SQL = String.format("INSERT INTO applicationssystem.scpecialty_properties(preferential_places,places,cost,specialty_id) VALUES(\"%s\",\"%s\",\"%s\",\"%s\");"
                , properties.getPriferentPlacec(), properties.getPlaces(), properties.getCost(), specialityID);

        CallableStatement statement = connection.prepareCall(SQL);
        statement.execute();

        try {

            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    @Override
    public void saveSpecialityAndSubjectsConnection(Integer listOfSubjects, int minScore, int specialityID) throws SQLException {
        String SQL = String.format("INSERT INTO applicationssystem.scpecialty_properties_has_subjects(scpecialty_properties_idtable1,subjects_subjectid,minimum_possitive_score) VALUES(\"%s\",\"%s\",\"%s\");"
                , specialityID, listOfSubjects, minScore);
        Connection connection = SQLFactory.getConnection();


        CallableStatement statement = connection.prepareCall(SQL);
        statement.execute();
        try {

            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public String getSubjectName(int specID) throws SQLException {
        Connection connection = SQLFactory.getConnection();
        String SQL = SELECT_FROM_APPLICATIONSSYSTEM_SUBJECTS;

        CallableStatement statement = connection.prepareCall(SQL);
        statement.execute();
        ResultSet resultSet = statement.getResultSet();

        while (resultSet.next()) {
            {
                if (specID == resultSet.getInt(SUBJECTID)) {
                    return resultSet.getString(SUBJECT);

                }

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
    }

}
