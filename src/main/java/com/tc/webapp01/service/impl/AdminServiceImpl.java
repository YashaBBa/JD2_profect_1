package com.tc.webapp01.service.impl;

import com.tc.webapp01.dao.AdminDAO;
import com.tc.webapp01.dao.DAOFactory;
import com.tc.webapp01.entity.Applicant;
import com.tc.webapp01.entity.Properties;
import com.tc.webapp01.entity.Request;
import com.tc.webapp01.entity.Speciality;
import com.tc.webapp01.service.AdminService;

import java.sql.SQLException;
import java.util.List;

public class AdminServiceImpl implements AdminService {
    @Override
    public List<Request> showSpecialistsList() throws SQLException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        AdminDAO adminDAO = daoFactory.getAdminDAO();
        List<Request> requestList;
        requestList = adminDAO.getSpecialistsList();
        return requestList;
    }

    @Override
    public List<Applicant> showApplicantsList() throws SQLException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        AdminDAO adminDAO = daoFactory.getAdminDAO();
        List<Applicant> applicants;
        applicants = adminDAO.getApplicantsList();
        return applicants;
    }

    @Override
    public String getSpecialityName(int specID) throws SQLException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        AdminDAO adminDAO = daoFactory.getAdminDAO();
        String specialityName;
        specialityName = adminDAO.getSpecialityName(specID);
        return specialityName;

    }

    @Override
    public Boolean applyRequest(int specID, int applicantID) throws SQLException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        AdminDAO adminDAO = daoFactory.getAdminDAO();

        Boolean a = adminDAO.applyRequest(specID, applicantID);
        return a;


    }

    @Override
    public Boolean deleteRequest(int applicantID) {
        DAOFactory daoFactory = DAOFactory.getInstance();
        AdminDAO adminDAO = daoFactory.getAdminDAO();

        Boolean a = null;
        try {
            a = adminDAO.deleteRequest(applicantID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return a;
    }

    @Override
    public Boolean deleteRequest(int applicantID, int specialityID) throws SQLException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        AdminDAO adminDAO = daoFactory.getAdminDAO();

        Boolean a = adminDAO.deleteRequest(applicantID,specialityID);
        return a;
    }

    @Override
    public int saveAndGetNewSpecialityID(Speciality speciality) throws SQLException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        AdminDAO adminDAO = daoFactory.getAdminDAO();

        return adminDAO.saveAndGetNewSpecialityID(speciality);

    }

    @Override
    public void savePropetriesForSpeciality(Properties properties, int specialityID) throws SQLException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        AdminDAO adminDAO = daoFactory.getAdminDAO();

       adminDAO.savePropetriesForSpeciality(properties,specialityID);

    }

    @Override
    public void saveSpecialityAndSubjectsConnection(Integer listOfSubjects, int minScore, int specialityID) throws SQLException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        AdminDAO adminDAO = daoFactory.getAdminDAO();

        adminDAO.saveSpecialityAndSubjectsConnection(listOfSubjects,minScore,specialityID);
    }
}
