package com.tc.webapp01.dao;

import com.tc.webapp01.entity.Applicant;
import com.tc.webapp01.entity.Property;
import com.tc.webapp01.entity.Request;
import com.tc.webapp01.entity.Speciality;
import com.tc.webapp01.service.ServiceException;

import java.util.List;

public interface AdminDAO {
    List<Request> getSpecialistsList() throws  DAOException;

    List<Applicant> getApplicantsList() throws DAOException, ServiceException;

    String getSpecialityName(int specID) throws DAOException;

    Boolean applyRequest(int specID,int applicantID) throws  DAOException;

    Boolean deleteRequest(int applicantID) throws  DAOException;

    Boolean deleteRequest(int applicantID, int specialityID) throws  DAOException;

    int saveAndGetNewSpecialityID(Speciality speciality) throws  DAOException;

    void savePropetriesForSpeciality(Property properties, int specialityID) throws  DAOException;

    void saveSpecialityAndSubjectsConnection(Integer listOfSubjects, int minScore, int specialityID) throws  DAOException;

    Boolean changeDate(String date) throws DAOException;

    List<Speciality> getAllSpecilitisWhithRequests() throws DAOException;

    Boolean sortAllApplitantsRequestsByPriotityAndScore(List<Speciality> specialityList) throws DAOException;

    void redactSpeciality(String param) throws DAOException;

    void updateSpecialityParam(String specID, String score, String places, String prefPlaces, String cost) throws DAOException;

}
