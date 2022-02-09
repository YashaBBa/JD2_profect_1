package com.tc.webapp01.service;

import com.tc.webapp01.entity.Applicant;
import com.tc.webapp01.entity.Properties;
import com.tc.webapp01.entity.Request;
import com.tc.webapp01.entity.Speciality;

import java.sql.SQLException;
import java.util.List;

public interface AdminService {
    List<Request> showSpecialistsList() throws SQLException;
    List<Applicant> showApplicantsList() throws SQLException;
    String getSpecialityName(int specID) throws SQLException;
    Boolean applyRequest(int specID, int applicantID) throws SQLException;

    Boolean deleteRequest(int applicantID);

    Boolean deleteRequest(int applicantID, int specialityID) throws SQLException;

    int saveAndGetNewSpecialityID(Speciality speciality) throws SQLException;

    void savePropetriesForSpeciality(Properties properties, int specialityID) throws SQLException;

    void saveSpecialityAndSubjectsConnection(Integer listOfSubjects, int i, int specialityID) throws SQLException;
}
