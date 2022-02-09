package com.tc.webapp01.service;

import com.tc.webapp01.entity.Applicant;
import com.tc.webapp01.entity.Properties;
import com.tc.webapp01.entity.Speciality;
import com.tc.webapp01.entity.Subject;

import java.sql.SQLException;
import java.util.List;

public interface SpecialitiesService {
    List<Speciality> allSpecialities(int facultyID) throws SQLException;
    Properties allProperties(int specialityID) throws SQLException;
    List<Subject> getSubjectsList(int specialityID) throws SQLException;

    List<Applicant> getApplicantsList(int specialityID) throws SQLException;
}
