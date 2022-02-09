package com.tc.webapp01.service.impl;

import com.tc.webapp01.dao.DAOFactory;
import com.tc.webapp01.dao.SpecialytiesDAO;
import com.tc.webapp01.entity.Applicant;
import com.tc.webapp01.entity.Properties;
import com.tc.webapp01.entity.Speciality;

import com.tc.webapp01.entity.Subject;
import com.tc.webapp01.service.SpecialitiesService;

import java.sql.SQLException;
import java.util.List;

public class SpecialitiesServiceImpl implements SpecialitiesService {
    @Override
    public List<Speciality> allSpecialities(int facultyID) throws SQLException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        SpecialytiesDAO specialytiesDAO = daoFactory.getSpecialytiesDAO();
        List<Speciality> specialityList = specialytiesDAO.specialityList(facultyID);

        return specialityList;
    }

    @Override
    public Properties allProperties(int specialityID) throws SQLException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        SpecialytiesDAO specialytiesDAO = daoFactory.getSpecialytiesDAO();
        Properties properties = specialytiesDAO.propertiesList(specialityID);

        return properties;
    }

    @Override
    public List<Subject> getSubjectsList(int specilityID) throws SQLException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        SpecialytiesDAO specialytiesDAO = daoFactory.getSpecialytiesDAO();

        List<Subject> getSubjectList=specialytiesDAO.getSubjectList(specilityID);

        return getSubjectList;

    }

    @Override
    public List<Applicant> getApplicantsList(int specialityID) throws SQLException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        SpecialytiesDAO specialytiesDAO = daoFactory.getSpecialytiesDAO();

        List<Applicant> applicantList=specialytiesDAO.getApplicantList(specialityID);

        return applicantList;

    }
}
