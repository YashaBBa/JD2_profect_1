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
    public static final String SELECT_APPLICANTS_APPLICANT_ID_SPECIALITY_ID_SCORE_PRIORITY_FROM_APPLICATIONSSYSTEM_REQUEST_WHERE_SUBJECTS_SUBJECTID_AND_LOOK_AND_SPECIALITY_ID_ORDER_BY_SCORE = "SELECT applicants_applicant_id,speciality_id,score,priority FROM applicationssystem.request WHERE subjects_subjectid=? AND look=? AND speciality_id=? ORDER BY score;";
    private static final String UPDATE_APPLICATIONSSYSTEM_USERS_SET_ROLES_ID_3_WHERE_USER_ID = "UPDATE applicationssystem.request SET look=2 WHERE applicants_applicant_id=? AND speciality_id=?";
    private static final String UPDATE_APPLICATIONSSYSTEM_APPLICANTS_SET_SPECIALITY_ID_WHERE_APPLICANT_ID = "UPDATE applicationssystem.applicants SET speciality_id=? WHERE applicant_id=?";
    private static final String DELETE_REQUEST_COMMAND = "DELETE FROM applicationssystem.request WHERE applicants_applicant_id=";
    private static final String INSERT_INTO_APPLICATIONSSYSTEM_SCPECIALTY_PROPERTIES_HAS_SUBJECTS_SCPECIALTY_PROPERTIES_IDTABLE_1_SUBJECTS_SUBJECTID_MINIMUM_POSSITIVE_SCORE_VALUES = "INSERT INTO applicationssystem.scpecialty_properties_has_subjects(scpecialty_properties_idtable1,subjects_subjectid,minimum_possitive_score) VALUES(?,?,?)";
    private static final String INSERT_INTO_APPLICATIONSSYSTEM_SCPECIALTY_PROPERTIES_PREFERENTIAL_PLACES_PLACES_COST_SPECIALTY_ID_VALUES = "INSERT INTO applicationssystem.scpecialty_properties(preferential_places,places,cost,specialty_id) VALUES(?,?,?,?)";
    private static final String INSERT_INTO_APPLICATIONSSYSTEM_SPECIALTY_SPECIALTY_MINIMUM_SCORE_FACULTIES_IDFACULTIES_VALUES = "INSERT INTO applicationssystem.specialty(specialty,minimum_score,faculties_idfaculties) VALUES(?,?,?)";
    private static final String DELETE_FROM_APPLICATIONSSYSTEM_REQUEST_WHERE_APPLICANTS_APPLICANT_ID_AND_SPECIALITY_ID = "DELETE FROM applicationssystem.request WHERE applicants_applicant_id=? AND speciality_id=?";
    private static final int COUNT_OF_REQUESTS = 5;
    private static final int COUNT_OF_STEPS = 2;
    private static final String DATABASE_SERVER_CONNECTION_HAS_PROBLEM = "Database server connection has problem";
    private static final String SQL_EXCEPTION = "SQL Exception";
    private static final String UPDATE_APPLICATIONSSYSTEM_SPECIALTY_SET_MINIMUM_SCORE_WHERE_IDSPECIALTY = "UPDATE applicationssystem.specialty SET minimum_score=? WHERE idspecialty=?";
    private static final String UPDATE_APPLICATIONSSYSTEM_SCPECIALTY_PROPERTIES_SET_PREFERENTIAL_PLACES_COST_PLACES_WHERE_SPECIALTY_ID = "UPDATE applicationssystem.scpecialty_properties SET preferential_places=?, cost=?,places=?  WHERE specialty_id=?";
    private static final String DELETE_FROM_APPLICATIONSSYSTEM_SPECIALTY_WHERE_IDSPECIALTY = "DELETE FROM applicationssystem.specialty WHERE idspecialty=? ";
    private static final String PRIORITY = "priority";
    private static final String SPECIALTY_ID = "specialty_id";
    private static final String PLACES = "places";
    private static final String UPDATE_APPLICATIONSSYSTEM_APPLICATION_CAMPAIGN_SET_DEADLINE_DATE_WHERE_APPLICATION_CAMPAIGN_ID = "UPDATE applicationssystem.`application campaign` SET deadline_date=? WHERE application_campaign_id=?";
    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String SELECT_FROM_APPLICATIONSSYSTEM_REQUEST = "SELECT * FROM applicationssystem.request WHERE look=1;";
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
            throw new DAOException(DATABASE_SERVER_CONNECTION_HAS_PROBLEM, e);
        } catch (SQLException e) {
            throw new DAOException(SQL_EXCEPTION, e);
        } finally {
            connectionPool.closeConnection(connection, statement, resultSet);
        }

        return requestList;
    }

    @Override
    public List<Applicant> getApplicantsList() throws ServiceException {
        return null;
    }

    public Applicant getApplicantData(int applicantID) throws DAOException {
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
            throw new DAOException(DATABASE_SERVER_CONNECTION_HAS_PROBLEM, e);
        } catch (SQLException e) {
            throw new DAOException(SQL_EXCEPTION, e);
        } finally {
            connectionPool.closeConnection(connection, statement, resultSet);
        }


        return null;

    }


    @Override
    public String getSpecialityName(int specID) throws DAOException {
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
            throw new DAOException(DATABASE_SERVER_CONNECTION_HAS_PROBLEM, e);
        } catch (SQLException e) {
            throw new DAOException(SQL_EXCEPTION, e);
        } finally {
            connectionPool.closeConnection(connection, statement, resultSet);
        }

        return null;

    }


    @Override
    public Boolean applyRequest(int specID, int applicantID) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;

        String SQL = UPDATE_APPLICATIONSSYSTEM_USERS_SET_ROLES_ID_3_WHERE_USER_ID;

        try {
            connection = connectionPool.takeConnection();
            statement = connection.prepareCall(SQL);
            statement.setInt(1, applicantID);
            statement.setInt(2, specID);
            statement.execute();
            System.out.println(specID);


        } catch (ConnectionPoolException e) {
            throw new DAOException(DATABASE_SERVER_CONNECTION_HAS_PROBLEM, e);
        } catch (SQLException e) {
            throw new DAOException(SQL_EXCEPTION, e);
        } finally {
            connectionPool.closeConnection(connection, statement);
        }

        return null;
    }

    public Boolean addApplicnatInGreenList(int specID, int applicantID) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;


        try {
            connection = connectionPool.takeConnection();


            String SQL = UPDATE_APPLICATIONSSYSTEM_APPLICANTS_SET_SPECIALITY_ID_WHERE_APPLICANT_ID;

            statement = connection.prepareStatement(SQL);
            statement.setInt(1, specID);
            statement.setInt(2, applicantID);
            statement.execute();
        } catch (ConnectionPoolException e) {
            throw new DAOException(DATABASE_SERVER_CONNECTION_HAS_PROBLEM, e);
        } catch (SQLException e) {
            throw new DAOException(SQL_EXCEPTION, e);
        } finally {
            connectionPool.closeConnection(connection, statement);
        }

        return true;
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
            throw new DAOException(DATABASE_SERVER_CONNECTION_HAS_PROBLEM, e);
        } catch (SQLException e) {
            throw new DAOException(SQL_EXCEPTION, e);
        } finally {
            connectionPool.closeConnection(connection, statement);
        }


        return null;
    }


    @Override
    public Boolean deleteRequest(int applicantID, int specialityID) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;


        try {
            connection = connectionPool.takeConnection();

            String SQL = DELETE_FROM_APPLICATIONSSYSTEM_REQUEST_WHERE_APPLICANTS_APPLICANT_ID_AND_SPECIALITY_ID;

            statement = connection.prepareStatement(SQL);
            statement.setInt(1, applicantID);
            statement.setInt(2, specialityID);
            statement.execute();
        } catch (ConnectionPoolException e) {
            throw new DAOException(DATABASE_SERVER_CONNECTION_HAS_PROBLEM, e);
        } catch (SQLException e) {
            throw new DAOException(SQL_EXCEPTION, e);
        } finally {
            connectionPool.closeConnection(connection, statement);
        }


        return null;
    }


    @Override
    public int saveAndGetNewSpecialityID(Speciality speciality) throws DAOException {
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
            throw new DAOException(DATABASE_SERVER_CONNECTION_HAS_PROBLEM, e);
        } catch (SQLException e) {
            throw new DAOException(SQL_EXCEPTION, e);
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
            throw new DAOException(DATABASE_SERVER_CONNECTION_HAS_PROBLEM, e);
        } catch (SQLException e) {
            throw new DAOException(SQL_EXCEPTION, e);
        } finally {
            connectionPool.closeConnection(connection, statement);
        }

    }


    @Override
    public void saveSpecialityAndSubjectsConnection(Integer listOfSubjects, int minScore, int specialityID) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;


        String SQL = INSERT_INTO_APPLICATIONSSYSTEM_SCPECIALTY_PROPERTIES_HAS_SUBJECTS_SCPECIALTY_PROPERTIES_IDTABLE_1_SUBJECTS_SUBJECTID_MINIMUM_POSSITIVE_SCORE_VALUES;

        try {
            connection = connectionPool.takeConnection();
            statement = connection.prepareStatement(SQL);
            statement.setInt(1, specialityID);
            statement.setInt(2, listOfSubjects);
            statement.setInt(3, minScore);
            statement.execute();
        } catch (ConnectionPoolException e) {
            throw new DAOException(DATABASE_SERVER_CONNECTION_HAS_PROBLEM, e);
        } catch (SQLException e) {
            throw new DAOException(SQL_EXCEPTION, e);
        } finally {
            connectionPool.closeConnection(connection, statement);
        }

    }

    @Override
    public Boolean changeDate(String date) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;

        String SQL = UPDATE_APPLICATIONSSYSTEM_APPLICATION_CAMPAIGN_SET_DEADLINE_DATE_WHERE_APPLICATION_CAMPAIGN_ID;
        try {
            connection = connectionPool.takeConnection();
            statement = connection.prepareStatement(SQL);
            statement.setDate(1, Date.valueOf(date));
            statement.setInt(2, 1);


            return statement.execute();

        } catch (ConnectionPoolException e) {
            throw new DAOException(DATABASE_SERVER_CONNECTION_HAS_PROBLEM, e);
        } catch (SQLException e) {
            throw new DAOException(SQL_EXCEPTION, e);
        } finally {
            connectionPool.closeConnection(connection, statement);
        }

    }

    @Override
    public List<Speciality> getAllSpecilitisWhithRequests() throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Speciality> specialityList = new ArrayList<>();

        String SQL = "SELECT specialty_id,places FROM applicationssystem.scpecialty_properties;";
        try {
            connection = connectionPool.takeConnection();
            statement = connection.prepareCall(SQL);
            statement.execute();
            resultSet = statement.getResultSet();
            while (resultSet.next()) {
                {
                    Speciality speciality = new Speciality();
                    speciality.setId(resultSet.getInt(SPECIALTY_ID));
                    speciality.setPlaces(resultSet.getInt(PLACES));
                    specialityList.add(speciality);
                }
            }
            SQL = SELECT_APPLICANTS_APPLICANT_ID_SPECIALITY_ID_SCORE_PRIORITY_FROM_APPLICATIONSSYSTEM_REQUEST_WHERE_SUBJECTS_SUBJECTID_AND_LOOK_AND_SPECIALITY_ID_ORDER_BY_SCORE;
            statement = connection.prepareStatement(SQL);

            for (Speciality speciality : specialityList) {


                statement.setInt(1, 6);
                statement.setInt(2, 2);
                statement.setInt(3, speciality.getId());
                statement.execute();
                resultSet = statement.getResultSet();
                List<Applicant> listOfApplicatns = new ArrayList<>();
                while (resultSet.next()) {
                    {
                        Applicant applicant = new Applicant();
                        applicant.setScore(resultSet.getInt(SCORE));
                        applicant.setApplicantId(resultSet.getInt(APPLICANTS_APPLICANT_ID));
                        applicant.setPriority(resultSet.getInt(PRIORITY));
                        listOfApplicatns.add(applicant);
                    }
                }
                speciality.setListOfApplicants(listOfApplicatns);

            }
        } catch (ConnectionPoolException e) {
            throw new DAOException(DATABASE_SERVER_CONNECTION_HAS_PROBLEM, e);
        } catch (SQLException e) {
            throw new DAOException(SQL_EXCEPTION, e);
        } finally {
            connectionPool.closeConnection(connection, statement, resultSet);
        }
        return specialityList;
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
            throw new DAOException(DATABASE_SERVER_CONNECTION_HAS_PROBLEM, e);
        } catch (SQLException e) {
            throw new DAOException(SQL_EXCEPTION, e);
        } finally {
            connectionPool.closeConnection(connection, statement, resultSet);
        }
        return null;
    }

    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
    @Override
    public Boolean sortAllApplitantsRequestsByPriotityAndScore(List<Speciality> specialityList) throws DAOException {

        List<Applicant> applicantList = new ArrayList<>();
        Boolean apply = false;


        for (int i = 0; i < COUNT_OF_REQUESTS; i++) {
            for (int j = 0; j < COUNT_OF_STEPS; j++) {
                for (Speciality speciality : specialityList) {

                    for (Applicant listOfApplicant : speciality.getListOfApplicants()) {
                        int applicantRatingPlace = speciality.getListOfApplicants().indexOf(listOfApplicant);

                        if (applicantRatingPlace < speciality.getPlaces() && listOfApplicant.getPriority() == i) {
                            apply = addApplicnatInGreenList(speciality.getId(), listOfApplicant.getApplicantId());
                            speciality.setPlaces(speciality.getPlaces() - 1);
                            System.out.println(listOfApplicant.getApplicantId() + " " + listOfApplicant.getScore() + " Apply this dude");
                            applicantList.add(listOfApplicant);


                        }
                    }
                    if (apply) {
                        for (Applicant applicant : applicantList) {
                            speciality.getListOfApplicants().remove(applicant);
                        }
                        apply = false;
                    }

                }


            }
        }

        return true;
    }

    @Override
    public void redactSpeciality(String param) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;

        String SQL = DELETE_FROM_APPLICATIONSSYSTEM_SPECIALTY_WHERE_IDSPECIALTY;


        try {
            connection = connectionPool.takeConnection();
            statement = connection.prepareCall(SQL);
            statement.setInt(1, Integer.parseInt(param));
            statement.execute();


        } catch (ConnectionPoolException e) {
            throw new DAOException(DATABASE_SERVER_CONNECTION_HAS_PROBLEM, e);
        } catch (SQLException e) {
            throw new DAOException(SQL_EXCEPTION, e);
        } finally {
            connectionPool.closeConnection(connection, statement);
        }

    }

    @Override
    public void updateSpecialityParam(String specID, String score, String places, String prefPlaces, String cost) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;

        String SQL = UPDATE_APPLICATIONSSYSTEM_SCPECIALTY_PROPERTIES_SET_PREFERENTIAL_PLACES_COST_PLACES_WHERE_SPECIALTY_ID;


        try {
            connection = connectionPool.takeConnection();
            statement = connection.prepareCall(SQL);

            statement.setInt(1, Integer.parseInt(places));
            statement.setDouble(2, Double.parseDouble(cost));
            statement.setInt(3, Integer.parseInt(places));
            statement.setInt(4, Integer.parseInt(specID));
            statement.execute();
            SQL = UPDATE_APPLICATIONSSYSTEM_SPECIALTY_SET_MINIMUM_SCORE_WHERE_IDSPECIALTY;
            statement = connection.prepareStatement(SQL);
            statement.setInt(1, Integer.parseInt(score));
            statement.setInt(2, Integer.parseInt(specID));
            statement.execute();


        } catch (ConnectionPoolException e) {
            throw new DAOException(DATABASE_SERVER_CONNECTION_HAS_PROBLEM, e);
        } catch (SQLException e) {
            throw new DAOException(SQL_EXCEPTION, e);
        } finally {
            connectionPool.closeConnection(connection, statement);
        }

    }

}
