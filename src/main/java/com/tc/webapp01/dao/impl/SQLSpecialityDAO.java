package com.tc.webapp01.dao.impl;

import com.tc.webapp01.dao.SpecialytiesDAO;
import com.tc.webapp01.entity.Applicant;
import com.tc.webapp01.entity.Properties;
import com.tc.webapp01.entity.Speciality;
import com.tc.webapp01.entity.Subject;
import com.tc.webapp01.service.SQLFactory;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLSpecialityDAO implements SpecialytiesDAO {

    public static final String SELECT_FROM_APPLICATIONSSYSTEM_SPECIALTY = "SELECT * FROM applicationssystem.specialty;";
    public static final String FACULTIES_IDFACULTIES = "faculties_idfaculties";
    public static final String IDSPECIALTY = "idspecialty";
    public static final String FACULTIES_IDFACULTIES1 = "faculties_idfaculties";
    public static final String SPECIALTY = "specialty";
    public static final String MINIMUM_SCORE = "minimum_score";
    public static final String SELECT_FROM_APPLICATIONSSYSTEM_SCPECIALTY_PROPERTIES = "SELECT * FROM applicationssystem.scpecialty_properties;";
    public static final String SPECIALTY_ID = "specialty_id";
    public static final String PLACES = "places";
    public static final String COST = "cost";
    public static final String PREFERENTIAL_PLACES = "preferential_places";
    public static final String APPLICATION_CAMPAIGN_ID = "application_campaign_id";
    public static final String STUDY_FORMATS_STUDY_FORMAT_ID = "study_formats_study_format_id";
    public static final String SELECT_FROM_APPLICATIONSSYSTEM_SCPECIALTY_PROPERTIES_HAS_SUBJECTS = "SELECT * FROM applicationssystem.scpecialty_properties_has_subjects;";
    public static final String SCPECIALTY_PROPERTIES_IDTABLE_1 = "scpecialty_properties_idtable1";
    public static final String SUBJECTS_SUBJECTID = "subjects_subjectid";
    public static final String SELECT_FROM_APPLICATIONSSYSTEM_SUBJECTS = "SELECT * FROM applicationssystem.subjects;";
    public static final String SUBJECTID = "subjectid";
    public static final String SUBJECT = "subject";
    public static final String SELECT_FROM_APPLICATIONSSYSTEM_APPLICANTS = "SELECT * FROM applicationssystem.applicants;";
    public static final String SPECIALITY_ID = "speciality_id";
    public static final String PASSPORT = "passport";
    public static final String NAME = "name";
    public static final String SURNAME = "surname";


    @Override
    public List<Speciality> specialityList(int facultyID) throws SQLException {
        Connection connection = SQLFactory.getConnection();
        java.lang.String SQL = SELECT_FROM_APPLICATIONSSYSTEM_SPECIALTY;
        CallableStatement statement = connection.prepareCall(SQL);
        statement.execute();
        ResultSet resultSet = statement.getResultSet();
        List<Speciality> specialityList = new ArrayList<>();


        while (resultSet.next()) {

            if (facultyID == resultSet.getInt(FACULTIES_IDFACULTIES)) {
                Properties properties = propertiesList(resultSet.getInt(IDSPECIALTY));
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
        try {
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return specialityList;
    }

    @Override
    public Properties propertiesList(int specialityID) throws SQLException {
        Connection connection = SQLFactory.getConnection();
        String SQL = SELECT_FROM_APPLICATIONSSYSTEM_SCPECIALTY_PROPERTIES;
        CallableStatement statement = connection.prepareCall(SQL);
        statement.execute();
        ResultSet resultSet = statement.getResultSet();


        while (resultSet.next()) {

            if (specialityID == resultSet.getInt(SPECIALTY_ID)) {
                Properties properties = new Properties();
                properties.setPlaces(resultSet.getInt(PLACES));
                properties.setCost(resultSet.getDouble(COST));

                properties.setSpecialty_id(resultSet.getInt(SPECIALTY_ID));
                properties.setPriferentPlacec(resultSet.getInt(PREFERENTIAL_PLACES));
                properties.setApplication_campaign_id(resultSet.getInt(APPLICATION_CAMPAIGN_ID));
                properties.setStudy_formats_study_format_id(resultSet.getInt(STUDY_FORMATS_STUDY_FORMAT_ID));
                try {
                    resultSet.close();
                    statement.close();
                    connection.close();
                } catch (SQLException e){
                    System.out.println(e.getMessage());
                }

                return properties;
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
    public List<Subject> getSubjectList(int specialityID) throws SQLException {
        Connection connection = SQLFactory.getConnection();
        String SQL = SELECT_FROM_APPLICATIONSSYSTEM_SCPECIALTY_PROPERTIES_HAS_SUBJECTS;
        CallableStatement statement = connection.prepareCall(SQL);
        statement.execute();
        ResultSet resultSet = statement.getResultSet();
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
        List<Subject> subjectsList = new ArrayList<>();
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
        try {
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }


        return subjectsList;


    }

    @Override
    public List<Applicant> getApplicantList(int specialityID) throws SQLException {
        Connection connection = SQLFactory.getConnection();
        String SQL = SELECT_FROM_APPLICATIONSSYSTEM_APPLICANTS;
        CallableStatement statement = connection.prepareCall(SQL);
        statement.execute();
        ResultSet resultSet = statement.getResultSet();
        List<Applicant> applicantList = new ArrayList<>();
        while (resultSet.next()) {
            if (specialityID == resultSet.getInt(SPECIALITY_ID)) {
                Applicant applicant=new Applicant();
                applicant.setPassport(resultSet.getString(PASSPORT));
                applicant.setName(resultSet.getString(NAME));
                applicant.setSurname(resultSet.getString(SURNAME));
                applicantList.add(applicant);
            }
        }
        try {
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return applicantList;
    }

}
