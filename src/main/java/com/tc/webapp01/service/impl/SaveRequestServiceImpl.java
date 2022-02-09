package com.tc.webapp01.service.impl;

import com.tc.webapp01.dao.DAOFactory;
import com.tc.webapp01.dao.FacultyDAO;
import com.tc.webapp01.entity.Faculty;
import com.tc.webapp01.service.SaveRequestService;

import java.util.List;

public class SaveRequestServiceImpl implements SaveRequestService {
    @Override
    public void saveRequest() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        FacultyDAO facultyDAO = daoFactory.getFacultyDAO();


    }
}
