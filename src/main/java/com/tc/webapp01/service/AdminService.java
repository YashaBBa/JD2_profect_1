package com.tc.webapp01.service;

import com.tc.webapp01.dao.DAOException;
import com.tc.webapp01.entity.Applicant;
import com.tc.webapp01.entity.Property;
import com.tc.webapp01.entity.Request;
import com.tc.webapp01.entity.Speciality;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface AdminService {
    List<Request> showSpecialistsList() throws ServiceException;
    List<Applicant> showApplicantsList() throws ServiceException;
    String getSpecialityName(int specID) throws ServiceException;
    Boolean applyRequest(int specID, int applicantID) throws ServiceException;

    Boolean deleteRequest(int applicantID) throws ServiceException;

    Boolean deleteRequest(int applicantID, int specialityID) throws ServiceException;

    int saveAndGetNewSpecialityID(Speciality speciality) throws ServiceException;

    void savePropetriesForSpeciality(Property properties, int specialityID) throws ServiceException;

    void saveSpecialityAndSubjectsConnection(Integer listOfSubjects, int i, int specialityID) throws ServiceException;

    Boolean changeDate(String date) throws ServiceException;

    List<Speciality> getAllSpecialitisWhithRequests() throws ServiceException;



    Boolean sortAllApplitantsRequestsByPriotityAndScore(List<Speciality> specialityList) throws ServiceException;

    void redactSpeciality(String param) throws ServiceException;

    void updateSpecialityParam(String specID, String score, String places, String prefPlaces, String cost) throws ServiceException;

}
