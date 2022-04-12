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

import java.util.ArrayList;
import java.util.List;

public class AdminServiceImpl implements AdminService {

    public static final String DATABASE_CONNECTION_EXCEPTION_TXT = "Database server connection has problem";

    @Override
    public List<Request> showSpecialistsList() throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        AdminDAO adminDAO = daoFactory.getAdminDAO();
        List<Request> requestList;
        try {
            requestList = adminDAO.getSpecialistsList();
        } catch (DAOException e) {
            throw new ServiceException(DATABASE_CONNECTION_EXCEPTION_TXT, e);
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
            throw new ServiceException(DATABASE_CONNECTION_EXCEPTION_TXT, e);
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
            throw new ServiceException(DATABASE_CONNECTION_EXCEPTION_TXT, e);
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
            throw new ServiceException(DATABASE_CONNECTION_EXCEPTION_TXT, e);
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
            throw new ServiceException(DATABASE_CONNECTION_EXCEPTION_TXT, e);
        }
        return a;
    }

    @Override
    public Boolean deleteRequest(int applicantID, int specialityID) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        AdminDAO adminDAO = daoFactory.getAdminDAO();

        Boolean a = null;
        try {
            a = adminDAO.deleteRequest(applicantID, specialityID);
        } catch (DAOException e) {
            throw new ServiceException(DATABASE_CONNECTION_EXCEPTION_TXT, e);
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
            throw new ServiceException(DATABASE_CONNECTION_EXCEPTION_TXT, e);
        }

    }

    @Override
    public void savePropetriesForSpeciality(Property properties, int specialityID) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        AdminDAO adminDAO = daoFactory.getAdminDAO();

        try {
            adminDAO.savePropetriesForSpeciality(properties, specialityID);
        } catch (DAOException e) {
            throw new ServiceException(DATABASE_CONNECTION_EXCEPTION_TXT, e);
        }

    }

    @Override
    public void saveSpecialityAndSubjectsConnection(Integer listOfSubjects, int minScore, int specialityID) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        AdminDAO adminDAO = daoFactory.getAdminDAO();

        try {
            adminDAO.saveSpecialityAndSubjectsConnection(listOfSubjects, minScore, specialityID);
        } catch (DAOException e) {
            throw new ServiceException(DATABASE_CONNECTION_EXCEPTION_TXT, e);
        }
    }

    @Override
    public Boolean changeDate(String date) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        AdminDAO adminDAO = daoFactory.getAdminDAO();

        try {
            adminDAO.changeDate(date);
        } catch (DAOException e) {
            throw new ServiceException(DATABASE_CONNECTION_EXCEPTION_TXT, e);
        }
        return true;
    }

    @Override
    public List<Speciality> getAllSpecialitisWhithRequests() throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        AdminDAO adminDAO = daoFactory.getAdminDAO();
        List<Speciality> specialityList = new ArrayList<>();
        try {
            specialityList = adminDAO.getAllSpecilitisWhithRequests();
        } catch (DAOException e) {
            throw new ServiceException(DATABASE_CONNECTION_EXCEPTION_TXT, e);
        }
        return specialityList;
    }

    @Override
    public Boolean sortAllApplitantsRequestsByPriotityAndScore(List<Speciality> specialityList) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        AdminDAO adminDAO = daoFactory.getAdminDAO();
        Boolean bool=null;
        try {
            bool = adminDAO.sortAllApplitantsRequestsByPriotityAndScore(specialityList);
        } catch (DAOException e) {
            throw new ServiceException(DATABASE_CONNECTION_EXCEPTION_TXT, e);
        }
        return true;
    }

    @Override
    public void redactSpeciality(String param) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        AdminDAO adminDAO = daoFactory.getAdminDAO();

        try {
             adminDAO.redactSpeciality(param);
        } catch (DAOException e) {
            throw new ServiceException(DATABASE_CONNECTION_EXCEPTION_TXT, e);
        }

    }

    @Override
    public void updateSpecialityParam(String specID, String score, String places, String prefPlaces, String cost) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        AdminDAO adminDAO = daoFactory.getAdminDAO();

        try {
            adminDAO.updateSpecialityParam(specID,score,places,prefPlaces,cost);
        } catch (DAOException e) {
            throw new ServiceException(DATABASE_CONNECTION_EXCEPTION_TXT, e);
        }
    }

}
