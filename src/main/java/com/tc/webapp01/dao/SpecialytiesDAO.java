package com.tc.webapp01.dao;

import com.tc.webapp01.entity.Applicant;
import com.tc.webapp01.entity.Property;
import com.tc.webapp01.entity.Speciality;
import com.tc.webapp01.entity.Subject;


import java.sql.SQLException;
import java.util.List;

public interface SpecialytiesDAO {
    List<Speciality> specialityList(int facultyID) throws  DAOException;
    Property propertiesList(int specialityID) throws  DAOException;
    List<Subject> getSubjectList(int specialityID) throws  DAOException;

    List<Applicant> getApplicantList(int specialityID) throws  DAOException;
}
