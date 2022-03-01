package com.tc.webapp01.service.impl;

import com.tc.webapp01.dao.DAOException;
import com.tc.webapp01.dao.DAOFactory;
import com.tc.webapp01.dao.FacultyDAO;
import com.tc.webapp01.entity.Faculty;
import com.tc.webapp01.service.FacultyService;
import com.tc.webapp01.service.ServiceException;

import java.sql.SQLException;
import java.util.List;

public class FacultyServiceImpl implements FacultyService {
    @Override
    public List<Faculty> allFaculties() throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        FacultyDAO facultyDAO = daoFactory.getFacultyDAO();
        List<Faculty> faculties;
        try {
            faculties = facultyDAO.allFaculties();
        } catch (DAOException e) {
            throw new ServiceException("Database server connection has problem", e);
        }
        return faculties;

    }
}
