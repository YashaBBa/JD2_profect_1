package com.tc.webapp01.dao.impl;

import com.tc.webapp01.dao.AdminDAO;
import com.tc.webapp01.pool.ConnectionPool;
import com.tc.webapp01.pool.ConnectionPoolException;
import com.tc.webapp01.dao.DAOException;
import com.tc.webapp01.entity.Applicant;
import com.tc.webapp01.entity.Property;
import com.tc.webapp01.entity.Request;
import com.tc.webapp01.entity.Speciality;
import com.tc.webapp01.service.ServiceException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLAdminDAO implements AdminDAO {
    private static final String UPDATE_APPLICATIONSSYSTEM_USERS_SET_ROLES_ID_3_WHERE_USER_ID = "UPDATE applicationssystem.users SET roles_id=3 WHERE user_id=";
    private static final String UPDATE_APPLICATIONSSYSTEM_APPLICANTS_SET_SPECIALITY_ID_WHERE_APPLICANT_ID = "UPDATE applicationssystem.applicants SET speciality_id=? WHERE applicant_id=?";
    private static final String DELETE_REQUEST_COMMAND = "DELETE FROM applicationssystem.request WHERE applicants_applicant_id=";
    private static final String INSERT_INTO_APPLICATIONSSYSTEM_SCPECIALTY_PROPERTIES_HAS_SUBJECTS_SCPECIALTY_PROPERTIES_IDTABLE_1_SUBJECTS_SUBJECTID_MINIMUM_POSSITIVE_SCORE_VALUES = "INSERT INTO applicationssystem.scpecialty_properties_has_subjects(scpecialty_properties_idtable1,subjects_subjectid,minimum_possitive_score) VALUES(?,?,?)";
    private static final String INSERT_INTO_APPLICATIONSSYSTEM_SCPECIALTY_PROPERTIES_PREFERENTIAL_PLACES_PLACES_COST_SPECIALTY_ID_VALUES = "INSERT INTO applicationssystem.scpecialty_properties(preferential_places,places,cost,specialty_id) VALUES(?,?,?,?)";
    private static final String INSERT_INTO_APPLICATIONSSYSTEM_SPECIALTY_SPECIALTY_MINIMUM_SCORE_FACULTIES_IDFACULTIES_VALUES = "INSERT INTO applicationssystem.specialty(specialty,minimum_score,faculties_idfaculties) VALUES(?,?,?)";
    private static final String DELETE_FROM_APPLICATIONSSYSTEM_REQUEST_WHERE_APPLICANTS_APPLICANT_ID_AND_SPECIALITY_ID = "DELETE FROM applicationssystem.request WHERE applicants_applicant_id=? AND speciality_id=?";
    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String SELECT_FROM_APPLICATIONSSYSTEM_REQUEST = "SELECT * FROM applicationssystem.request;";
    private static final String SELECT_FROM_APPLICATIONSSYSTEM_APPLICANTS = "SELECT * FROM applicationssystem.applicants;";
    private static final String SELECT_FROM_APPLICATIONSSYSTEM_SPECIALTY = "SELECT * FROM applicationssystem.specialty;";
    private static final String SELECT_FROM_APPLICATIONSSYSTEM_SPECIALTY1 = "SELECT * FROM applicationssystem.specialty;";
    private static final String SPECIALTY = "specialty";
    private static final String IDSPECIALTY = "idspecialty";
    private static final String SELECT_FROM_APPLICATIONSSYSTEM_SUBJECTS = "SELECT * FROM applicationssystem.subjects;";
    private static final String APPLICANTS_APPLICANT_ID = "applicants_applicant_id";
    private static final String SPECIALITY_ID = "speciality_id";
    private static final String SUBJECTS_SUBJECTID = "subjects_subjectid";
    private static final String REQUEST_ID = "request_id";
    private static final String SCORE = "score";
    private static final String APPLICANT_ID = "applicant_id";
    private static final String SPECIALITY_ID1 = "speciality_id";
    private static final String APPLICANT_ID1 = "applicant_id";
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String PASSPORT = "passport";
    private static final String IDSPECIALTY1 = "idspecialty";
    private static final String SPECIALTY1 = "specialty";
    private static final String SUBJECTID = "subjectid";
    private static final String SUBJECT = "subject";


    @Override
    public List<Request> getSpecialistsList() throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String SQL = SELECT_FROM_APPLICATIONSSYSTEM_REQUEST;
        List<Request> requestList = new ArrayList<>();

        try {
            connection = connectionPool.takeConnection();
            statement = connection.prepareCall(SQL);
            statement.execute();
            resultSet = statement.getResultSet();

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
        } catch (ConnectionPoolException e) {
            throw new DAOException("Database server connection has problem", e);

        } catch (SQLException e) {
            throw new DAOException("SQL Exception", e);
        } finally {
            connectionPool.closeConnection(connection, statement, resultSet);
        }

        return requestList;
    }

    @Override
    public List<Applicant> getApplicantsList() throws ServiceException {
        return null;
    }

    public Applicant getApplicantData(int applicantID) throws  DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String SQL = SELECT_FROM_APPLICATIONSSYSTEM_APPLICANTS;
        try {
            connection = connectionPool.takeConnection();
            statement = connection.prepareCall(SQL);
            statement.execute();
            resultSet = statement.getResultSet();

            while (resultSet.next()) {
                if (applicantID == resultSet.getInt(APPLICANT_ID)) {
                    Applicant applicant = new Applicant();
                    applicant.setApplicantSpeciality(resultSet.getInt(SPECIALITY_ID1));
                    applicant.setApplicantId(resultSet.getInt(APPLICANT_ID1));
                    applicant.setName(resultSet.getString(NAME));
                    applicant.setSurname(resultSet.getString(SURNAME));
                    applicant.setPassport(resultSet.getString(PASSPORT));
                    return applicant;
                }
            }
        } catch (ConnectionPoolException e) {
            throw new DAOException("Database server connection has problem", e);
        } catch (SQLException e) {
            throw new DAOException("SQL Exception", e);
        } finally {
            connectionPool.closeConnection(connection, statement, resultSet);
        }


        return null;

    }


    @Override
    public String getSpecialityName(int specID) throws  DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String SQL = SELECT_FROM_APPLICATIONSSYSTEM_SPECIALTY;

        try {
            connection = connectionPool.takeConnection();
            statement = connection.prepareCall(SQL);
            statement.execute();
            resultSet = statement.getResultSet();

            while (resultSet.next()) {
                {
                    if (specID == resultSet.getInt(IDSPECIALTY1)) {
                        return resultSet.getString(SPECIALTY1);
                    }
                }
            }
        } catch (ConnectionPoolException e) {
            throw new DAOException("Database server connection has problem", e);
        } catch (SQLException e) {
            throw new DAOException("SQL Exception", e);
        } finally {
            connectionPool.closeConnection(connection, statement, resultSet);
        }

        return null;

    }


    @Override
    public Boolean applyRequest(int specID, int applicantID) throws DAOException{
        Connection connection = null;
        PreparedStatement statement = null;

        String SQL = UPDATE_APPLICATIONSSYSTEM_USERS_SET_ROLES_ID_3_WHERE_USER_ID + applicantID;

        try {
            connection = connectionPool.takeConnection();
            statement = connection.prepareCall(SQL);
            statement.execute();
            System.out.println(specID);

            //SQL = "UPDATE applicationssystem.applicants SET speciality_id=" + specID + " WHERE applicant_id=" + applicantID + ";";
            SQL = UPDATE_APPLICATIONSSYSTEM_APPLICANTS_SET_SPECIALITY_ID_WHERE_APPLICANT_ID;

            statement = connection.prepareStatement(SQL);
            statement.setInt(1, specID);
            statement.setInt(2, applicantID);
            statement.execute();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Database server connection has problem", e);
        } catch (SQLException e) {
            throw new DAOException("SQL Exception", e);
        } finally {
            connectionPool.closeConnection(connection, statement);
        }

        return null;
    }


    @Override
    public Boolean deleteRequest(int applicantID) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        String SQL = DELETE_REQUEST_COMMAND + applicantID;
        try {
            connection = connectionPool.takeConnection();
            statement = connection.prepareCall(SQL);
            statement.execute();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Database server connection has problem", e);
        } catch (SQLException e) {
            throw new DAOException("SQL Exception", e);
        } finally {
            connectionPool.closeConnection(connection, statement);
        }


        return null;
    }


    @Override
    public Boolean deleteRequest(int applicantID, int specialityID) throws  DAOException {
        Connection connection = null;
        PreparedStatement statement = null;


        try {
            connection = connectionPool.takeConnection();
            //String SQL = "DELETE FROM applicationssystem.request WHERE applicants_applicant_id=" + applicantID + " AND speciality_id=" + specialityID + ";";
            String SQL = DELETE_FROM_APPLICATIONSSYSTEM_REQUEST_WHERE_APPLICANTS_APPLICANT_ID_AND_SPECIALITY_ID;

            statement = connection.prepareStatement(SQL);
            statement.setInt(1, applicantID);
            statement.setInt(2, specialityID);
            statement.execute();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Database server connection has problem", e);
        } catch (SQLException e) {
            throw new DAOException("SQL Exception", e);
        } finally {
            connectionPool.closeConnection(connection, statement);
        }


        return null;
    }


    @Override
    public int saveAndGetNewSpecialityID(Speciality speciality) throws DAOException{
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        String SQL = INSERT_INTO_APPLICATIONSSYSTEM_SPECIALTY_SPECIALTY_MINIMUM_SCORE_FACULTIES_IDFACULTIES_VALUES;


        try {
            connection = connectionPool.takeConnection();
            statement = connection.prepareStatement(SQL);
            statement.setString(1, speciality.getSpeciality());
            statement.setInt(2, speciality.getScore());
            statement.setInt(3, speciality.getFacultyID());
            statement.execute();
            SQL = SELECT_FROM_APPLICATIONSSYSTEM_SPECIALTY1;
            statement = connection.prepareCall(SQL);
            statement.execute();
            resultSet = statement.getResultSet();

            while (resultSet.next()) {
                {
                    if (speciality.getSpeciality().equals(resultSet.getString(SPECIALTY))) {
                        return resultSet.getInt(IDSPECIALTY);
                    }
                }
            }
        } catch (ConnectionPoolException e) {
            throw new DAOException("Database server connection has problem", e);
        } catch (SQLException e) {
            throw new DAOException("SQL Exception", e);
        } finally {
            connectionPool.closeConnection(connection, statement, resultSet);
        }

        return 0;
    }


    @Override
    public void savePropetriesForSpeciality(Property properties, int specialityID) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;

        String SQL = INSERT_INTO_APPLICATIONSSYSTEM_SCPECIALTY_PROPERTIES_PREFERENTIAL_PLACES_PLACES_COST_SPECIALTY_ID_VALUES;


        try {
            connection = connectionPool.takeConnection();
            statement = connection.prepareStatement(SQL);
            statement.setInt(1, properties.getPriferentPlacec());
            statement.setInt(2, properties.getPlaces());
            statement.setDouble(3, properties.getCost());
            statement.setInt(4, specialityID);
            statement.execute();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Database server connection has problem", e);
        } catch (SQLException e) {
            throw new DAOException("SQL Exception", e);
        } finally {
            connectionPool.closeConnection(connection, statement);
        }

    }


    @Override
    public void saveSpecialityAndSubjectsConnection(Integer listOfSubjects, int minScore, int specialityID) throws  DAOException {
        Connection connection = null;
        PreparedStatement statement = null;


        String SQL = INSERT_INTO_APPLICATIONSSYSTEM_SCPECIALTY_PROPERTIES_HAS_SUBJECTS_SCPECIALTY_PROPERTIES_IDTABLE_1_SUBJECTS_SUBJECTID_MINIMUM_POSSITIVE_SCORE_VALUES;

        try {
            connection = connectionPool.takeConnection();
            statement = connection.prepareStatement(SQL);
            statement.setInt(1,specialityID);
            statement.setInt(2,listOfSubjects);
            statement.setInt(3,minScore);
            statement.execute();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Database server connection has problem", e);
        } catch (SQLException e) {
            throw new DAOException("SQL Exception", e);
        } finally {
            connectionPool.closeConnection(connection, statement);
        }

    }

    public String getSubjectName(int specID) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String SQL = SELECT_FROM_APPLICATIONSSYSTEM_SUBJECTS;


        try {
            connection = connectionPool.takeConnection();
            statement = connection.prepareCall(SQL);
            statement.execute();
            resultSet = statement.getResultSet();

            while (resultSet.next()) {
                {
                    if (specID == resultSet.getInt(SUBJECTID)) {
                        return resultSet.getString(SUBJECT);
                    }
                }
            }
        } catch (ConnectionPoolException e) {
            throw new DAOException("Database server connection has problem", e);
        } catch (SQLException e) {
            throw new DAOException("SQL Exception", e);
        } finally {
            connectionPool.closeConnection(connection, statement, resultSet);
        }
        return null;
    }

}
