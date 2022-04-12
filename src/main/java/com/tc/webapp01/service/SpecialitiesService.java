package com.tc.webapp01.service;

import com.tc.webapp01.dao.DAOException;
import com.tc.webapp01.entity.Applicant;
import com.tc.webapp01.entity.Property;
import com.tc.webapp01.entity.Speciality;
import com.tc.webapp01.entity.Subject;

import java.sql.SQLException;
import java.util.List;

public interface SpecialitiesService {
    List<Speciality> allSpecialities(int facultyID) throws ServiceException;
    Property allProperties(int specialityID) throws  ServiceException;
    List<Subject> getSubjectsList(int specialityID) throws  ServiceException;

    List<Applicant> getApplicantsList(int specialityID) throws ServiceException;

    Boolean checkDeadLineTime() throws ServiceException;

}
