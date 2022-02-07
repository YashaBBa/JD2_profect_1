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
    @Override
    public List<Speciality> specialityList(int facultyID) throws SQLException {
        Connection connection = SQLFactory.getConnection();
        java.lang.String SQL = "SELECT * FROM applicationssystem.specialty;";
        CallableStatement statement = connection.prepareCall(SQL);
        statement.execute();
        ResultSet resultSet = statement.getResultSet();
        List<Speciality> specialityList = new ArrayList<>();


        while (resultSet.next()) {

            if (facultyID == resultSet.getInt("faculties_idfaculties")) {
                Properties properties = propertiesList(resultSet.getInt("idspecialty"));
                Speciality speciality = new Speciality();
                speciality.setFacultyID(resultSet.getInt("faculties_idfaculties"));
                speciality.setSpeciality(resultSet.getString("specialty"));
                speciality.setScore(resultSet.getInt("minimum_score"));
                speciality.setId(resultSet.getInt("idspecialty"));

                speciality.setId(resultSet.getInt("idspecialty"));
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
        String SQL = "SELECT * FROM applicationssystem.scpecialty_properties;";
        CallableStatement statement = connection.prepareCall(SQL);
        statement.execute();
        ResultSet resultSet = statement.getResultSet();


        while (resultSet.next()) {

            if (specialityID == resultSet.getInt("specialty_id")) {
                Properties properties = new Properties();
                properties.setPlaces(resultSet.getInt("places"));
                properties.setCost(resultSet.getDouble("cost"));

                properties.setSpecialty_id(resultSet.getInt("specialty_id"));
                properties.setPriferentPlacec(resultSet.getInt("preferential_places"));
                properties.setApplication_campaign_id(resultSet.getInt("application_campaign_id"));
                properties.setStudy_formats_study_format_id(resultSet.getInt("study_formats_study_format_id"));
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
        String SQL = "SELECT * FROM applicationssystem.scpecialty_properties_has_subjects;";
        CallableStatement statement = connection.prepareCall(SQL);
        statement.execute();
        ResultSet resultSet = statement.getResultSet();
        List<Integer> listOFSpecialitysID = new ArrayList<>();
        while (resultSet.next()) {
            if (specialityID == resultSet.getInt("scpecialty_properties_idtable1")) {
                listOFSpecialitysID.add(resultSet.getInt("subjects_subjectid"));
            }
        }

        SQL = "SELECT * FROM applicationssystem.subjects;";
        statement = connection.prepareCall(SQL);
        statement.execute();
        resultSet = statement.getResultSet();
        List<Subject> subjectsList = new ArrayList<>();
        while (resultSet.next()) {

            for (Integer subID : listOFSpecialitysID) {
                if (subID == resultSet.getInt("subjectid")) {
                    Subject subject = new Subject();
                    subject.setSubject(resultSet.getString("subject"));
                    subject.setSubjectID(resultSet.getInt("subjectid"));
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
        String SQL = "SELECT * FROM applicationssystem.applicants;";
        CallableStatement statement = connection.prepareCall(SQL);
        statement.execute();
        ResultSet resultSet = statement.getResultSet();
        List<Applicant> applicantList = new ArrayList<>();
        while (resultSet.next()) {
            if (specialityID == resultSet.getInt("speciality_id")) {
                Applicant applicant=new Applicant();
                applicant.setPassport(resultSet.getString("passport"));
                applicant.setName(resultSet.getString("name"));
                applicant.setSurname(resultSet.getString("surname"));
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
