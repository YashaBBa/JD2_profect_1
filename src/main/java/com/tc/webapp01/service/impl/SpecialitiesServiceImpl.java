package com.tc.webapp01.service.impl;

import com.tc.webapp01.dao.DAOException;
import com.tc.webapp01.dao.DAOFactory;
import com.tc.webapp01.dao.SpecialytiesDAO;
import com.tc.webapp01.entity.Applicant;
import com.tc.webapp01.entity.Property;
import com.tc.webapp01.entity.Speciality;

import com.tc.webapp01.entity.Subject;
import com.tc.webapp01.service.ServiceException;
import com.tc.webapp01.service.SpecialitiesService;

import java.sql.SQLException;
import java.util.List;

public class SpecialitiesServiceImpl implements SpecialitiesService {
    @Override
    public List<Speciality> allSpecialities(int facultyID) throws ServiceException{
        DAOFactory daoFactory = DAOFactory.getInstance();
        SpecialytiesDAO specialytiesDAO = daoFactory.getSpecialytiesDAO();
        List<Speciality> specialityList = null;
        try {
            specialityList = specialytiesDAO.specialityList(facultyID);
        } catch (DAOException e) {
            throw new ServiceException("Database server connection has problem", e);
        }

        return specialityList;
    }

    @Override
    public Property allProperties(int specialityID) throws ServiceException{
        DAOFactory daoFactory = DAOFactory.getInstance();
        SpecialytiesDAO specialytiesDAO = daoFactory.getSpecialytiesDAO();
        Property properties = null;
        try {
            properties = specialytiesDAO.propertiesList(specialityID);
        } catch (DAOException e) {
            throw new ServiceException("Database server connection has problem", e);
        }

        return properties;
    }

    @Override
    public List<Subject> getSubjectsList(int specilityID) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        SpecialytiesDAO specialytiesDAO = daoFactory.getSpecialytiesDAO();

        List<Subject> getSubjectList= null;
        try {
            getSubjectList = specialytiesDAO.getSubjectList(specilityID);
        } catch (DAOException e) {
            throw new ServiceException("Database server connection has problem", e);
        }

        return getSubjectList;

    }

    @Override
    public List<Applicant> getApplicantsList(int specialityID) throws ServiceException{
        DAOFactory daoFactory = DAOFactory.getInstance();
        SpecialytiesDAO specialytiesDAO = daoFactory.getSpecialytiesDAO();

        List<Applicant> applicantList= null;
        try {
            applicantList = specialytiesDAO.getApplicantList(specialityID);
        } catch (DAOException e) {
            throw new ServiceException("Database server connection has problem", e);
        }

        return applicantList;

    }
}
