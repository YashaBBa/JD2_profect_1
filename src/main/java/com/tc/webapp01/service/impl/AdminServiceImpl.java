package com.tc.webapp01.service.impl;

import com.tc.webapp01.dao.AdminDAO;
import com.tc.webapp01.dao.DAOException;
import com.tc.webapp01.dao.DAOFactory;
import com.tc.webapp01.entity.Applicant;
import com.tc.webapp01.entity.Property;
import com.tc.webapp01.entity.Request;
import com.tc.webapp01.entity.Speciality;
import com.tc.webapp01.service.AdminService;
import com.tc.webapp01.service.ServiceException;

import java.sql.SQLException;
import java.util.List;

public class AdminServiceImpl implements AdminService {
    @Override
    public List<Request> showSpecialistsList() throws ServiceException{
        DAOFactory daoFactory = DAOFactory.getInstance();
        AdminDAO adminDAO = daoFactory.getAdminDAO();
        List<Request> requestList;
        try {
            requestList = adminDAO.getSpecialistsList();
        } catch (DAOException e) {
            throw new ServiceException("Database server connection has problem", e);
        }
        return requestList;
    }

    @Override
    public List<Applicant> showApplicantsList() throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        AdminDAO adminDAO = daoFactory.getAdminDAO();
        List<Applicant> applicants;
        try {
            applicants = adminDAO.getApplicantsList();
        } catch (DAOException e) {
            throw new ServiceException("Database server connection has problem", e);
        }
        return applicants;
    }

    @Override
    public String getSpecialityName(int specID) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        AdminDAO adminDAO = daoFactory.getAdminDAO();
        String specialityName;
        try {
            specialityName = adminDAO.getSpecialityName(specID);
        } catch (DAOException e) {
            throw new ServiceException("Database server connection has problem", e);
        }
        return specialityName;

    }

    @Override
    public Boolean applyRequest(int specID, int applicantID) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        AdminDAO adminDAO = daoFactory.getAdminDAO();

        Boolean a = null;
        try {
            a = adminDAO.applyRequest(specID, applicantID);
        } catch (DAOException e) {
            throw new ServiceException("Database server connection has problem", e);
        }
        return a;


    }

    @Override
    public Boolean deleteRequest(int applicantID) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        AdminDAO adminDAO = daoFactory.getAdminDAO();

        Boolean a = null;
        try {
            a = adminDAO.deleteRequest(applicantID);
        } catch (DAOException e) {
            throw new ServiceException("Database server connection has problem", e);
        }
        return a;
    }

    @Override
    public Boolean deleteRequest(int applicantID, int specialityID) throws ServiceException{
        DAOFactory daoFactory = DAOFactory.getInstance();
        AdminDAO adminDAO = daoFactory.getAdminDAO();

        Boolean a = null;
        try {
            a = adminDAO.deleteRequest(applicantID,specialityID);
        } catch (DAOException e) {
            throw new ServiceException("Database server connection has problem", e);
        }
        return a;
    }

    @Override
    public int saveAndGetNewSpecialityID(Speciality speciality) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        AdminDAO adminDAO = daoFactory.getAdminDAO();

        try {
            return adminDAO.saveAndGetNewSpecialityID(speciality);
        } catch (DAOException e) {
            throw new ServiceException("Database server connection has problem", e);
        }

    }

    @Override
    public void savePropetriesForSpeciality(Property properties, int specialityID) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        AdminDAO adminDAO = daoFactory.getAdminDAO();

        try {
            adminDAO.savePropetriesForSpeciality(properties,specialityID);
        } catch (DAOException e) {
            throw new ServiceException("Database server connection has problem", e);
        }

    }

    @Override
    public void saveSpecialityAndSubjectsConnection(Integer listOfSubjects, int minScore, int specialityID) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        AdminDAO adminDAO = daoFactory.getAdminDAO();

        try {
            adminDAO.saveSpecialityAndSubjectsConnection(listOfSubjects,minScore,specialityID);
        } catch (DAOException e) {
            throw new ServiceException("Database server connection has problem", e);
        }
    }
}
