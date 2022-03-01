package com.tc.webapp01.dao.impl;

import com.tc.webapp01.pool.ConnectionPool;
import com.tc.webapp01.pool.ConnectionPoolException;
import com.tc.webapp01.dao.DAOException;
import com.tc.webapp01.dao.SpecialytiesDAO;
import com.tc.webapp01.entity.Applicant;
import com.tc.webapp01.entity.Property;
import com.tc.webapp01.entity.Speciality;
import com.tc.webapp01.entity.Subject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLSpecialityDAO implements SpecialytiesDAO {
    public static final String RETING = "reting";
    public static final String PRIVILEGES = "privileges";
    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String SELECT_FROM_APPLICATIONSSYSTEM_SPECIALTY = "SELECT * FROM applicationssystem.specialty;";
    private static final String FACULTIES_IDFACULTIES = "faculties_idfaculties";
    private static final String IDSPECIALTY = "idspecialty";
    private static final String FACULTIES_IDFACULTIES1 = "faculties_idfaculties";
    private static final String SPECIALTY = "specialty";
    private static final String MINIMUM_SCORE = "minimum_score";
    private static final String SELECT_FROM_APPLICATIONSSYSTEM_SCPECIALTY_PROPERTIES = "SELECT * FROM applicationssystem.scpecialty_properties;";
    private static final String SPECIALTY_ID = "specialty_id";
    private static final String PLACES = "places";
    private static final String COST = "cost";
    private static final String PREFERENTIAL_PLACES = "preferential_places";
    private static final String APPLICATION_CAMPAIGN_ID = "application_campaign_id";
    private static final String STUDY_FORMATS_STUDY_FORMAT_ID = "study_formats_study_format_id";
    private static final String SELECT_FROM_APPLICATIONSSYSTEM_SCPECIALTY_PROPERTIES_HAS_SUBJECTS = "SELECT * FROM applicationssystem.scpecialty_properties_has_subjects;";
    private static final String SCPECIALTY_PROPERTIES_IDTABLE_1 = "scpecialty_properties_idtable1";
    private static final String SUBJECTS_SUBJECTID = "subjects_subjectid";
    private static final String SELECT_FROM_APPLICATIONSSYSTEM_SUBJECTS = "SELECT * FROM applicationssystem.subjects;";
    private static final String SUBJECTID = "subjectid";
    private static final String SUBJECT = "subject";
    private static final String SELECT_FROM_APPLICATIONSSYSTEM_APPLICANTS = "SELECT * FROM applicationssystem.applicants ORDER BY reting DESC;";
    private static final String SPECIALITY_ID = "speciality_id";
    private static final String PASSPORT = "passport";
    private static final String NAME = "name";
    private static final String SURNAME = "surname";


    @Override
    public List<Speciality> specialityList(int facultyID) throws  DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Speciality> specialityList = new ArrayList<>();
        String SQL = SELECT_FROM_APPLICATIONSSYSTEM_SPECIALTY;

        try {
            connection = connectionPool.takeConnection();
            statement = connection.prepareCall(SQL);
            statement.execute();
            resultSet = statement.getResultSet();

            while (resultSet.next()) {

                if (facultyID == resultSet.getInt(FACULTIES_IDFACULTIES)) {
                    Property properties = propertiesList(resultSet.getInt(IDSPECIALTY));
                    Speciality speciality = new Speciality();
                    speciality.setFacultyID(resultSet.getInt(FACULTIES_IDFACULTIES1));
                    speciality.setSpeciality(resultSet.getString(SPECIALTY));
                    speciality.setScore(resultSet.getInt(MINIMUM_SCORE));
                    speciality.setId(resultSet.getInt(IDSPECIALTY));

                    speciality.setId(resultSet.getInt(IDSPECIALTY));
                    speciality.setProperties(properties);


                    specialityList.add(speciality);
                }

            }
        } catch (ConnectionPoolException e) {
            throw new DAOException("Database server connection has problem", e);
        } catch (SQLException e) {
            throw new DAOException("SQL command exception", e);
        } finally {
            connectionPool.closeConnection(connection, statement, resultSet);
        }
        return specialityList;
    }

    @Override
    public Property propertiesList(int specialityID) throws  DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String SQL = SELECT_FROM_APPLICATIONSSYSTEM_SCPECIALTY_PROPERTIES;
        try {
            connection = connectionPool.takeConnection();
            statement = connection.prepareCall(SQL);
            statement.execute();
            resultSet = statement.getResultSet();


            while (resultSet.next()) {

                if (specialityID == resultSet.getInt(SPECIALTY_ID)) {
                    Property properties = new Property();
                    properties.setPlaces(resultSet.getInt(PLACES));
                    properties.setCost(resultSet.getDouble(COST));

                    properties.setSpecialty_id(resultSet.getInt(SPECIALTY_ID));
                    properties.setPriferentPlacec(resultSet.getInt(PREFERENTIAL_PLACES));
                    properties.setApplication_campaign_id(resultSet.getInt(APPLICATION_CAMPAIGN_ID));
                    properties.setStudy_formats_study_format_id(resultSet.getInt(STUDY_FORMATS_STUDY_FORMAT_ID));

                    return properties;
                }


            }
        } catch (ConnectionPoolException e) {
            throw new DAOException("Database server connection has problem", e);
        } catch (SQLException e) {
            throw new DAOException("SQL command exception", e);
        } finally {
            connectionPool.closeConnection(connection, statement, resultSet);
        }
        return null;
    }

    @Override
    public List<Subject> getSubjectList(int specialityID) throws  DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String SQL = SELECT_FROM_APPLICATIONSSYSTEM_SCPECIALTY_PROPERTIES_HAS_SUBJECTS;
        List<Subject> subjectsList = new ArrayList<>();

        try {
            connection = connectionPool.takeConnection();
            statement = connection.prepareCall(SQL);
            statement.execute();
            resultSet = statement.getResultSet();
            List<Integer> listOFSpecialitysID = new ArrayList<>();
            while (resultSet.next()) {
                if (specialityID == resultSet.getInt(SCPECIALTY_PROPERTIES_IDTABLE_1)) {
                    listOFSpecialitysID.add(resultSet.getInt(SUBJECTS_SUBJECTID));
                }
            }

            SQL = SELECT_FROM_APPLICATIONSSYSTEM_SUBJECTS;
            statement = connection.prepareCall(SQL);
            statement.execute();
            resultSet = statement.getResultSet();

            while (resultSet.next()) {

                for (Integer subID : listOFSpecialitysID) {
                    if (subID == resultSet.getInt(SUBJECTID)) {
                        Subject subject = new Subject();
                        subject.setSubject(resultSet.getString(SUBJECT));
                        subject.setSubjectID(resultSet.getInt(SUBJECTID));
                        subjectsList.add(subject);
                    }
                }
            }
        } catch (ConnectionPoolException e) {
            throw new DAOException("Database server connection has problem", e);
        } catch (SQLException e) {
            throw new DAOException("SQL command exception", e);
        } finally {
            connectionPool.closeConnection(connection, statement, resultSet);
        }

        return subjectsList;


    }

    @Override
    public List<Applicant> getApplicantList(int specialityID) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String SQL = SELECT_FROM_APPLICATIONSSYSTEM_APPLICANTS;
        List<Applicant> applicantList = new ArrayList<>();
        try {
            connection = connectionPool.takeConnection();
            statement = connection.prepareCall(SQL);
            statement.execute();
            resultSet = statement.getResultSet();

            while (resultSet.next()) {
                if (specialityID == resultSet.getInt(SPECIALITY_ID)) {
                    Applicant applicant = new Applicant();
                    applicant.setPassport(resultSet.getString(PASSPORT));
                    applicant.setName(resultSet.getString(NAME));
                    applicant.setSurname(resultSet.getString(SURNAME));
                    applicant.setScore(resultSet.getInt(RETING));
                    applicant.setPrivileges(resultSet.getString(PRIVILEGES));
                    applicantList.add(applicant);
                }
            }
        } catch (ConnectionPoolException e) {
            throw new DAOException("Database server connection has problem", e);
        } catch (SQLException e) {
            throw new DAOException("SQL command exception", e);
        } finally {
            connectionPool.closeConnection(connection, statement, resultSet);
        }
        return applicantList;
    }

}
