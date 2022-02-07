package com.tc.webapp01.dao;

import com.tc.webapp01.entity.Applicant;
import com.tc.webapp01.entity.Properties;
import com.tc.webapp01.entity.Speciality;
import com.tc.webapp01.entity.Subject;


import java.sql.SQLException;
import java.util.List;

public interface SpecialytiesDAO {
    List<Speciality> specialityList(int facultyID) throws SQLException;
    Properties propertiesList(int specialityID) throws SQLException;
    List<Subject> getSubjectList(int specialityID) throws SQLException;

    List<Applicant> getApplicantList(int specialityID) throws SQLException;
}
