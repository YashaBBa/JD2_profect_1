package com.tc.webapp01.service.impl;

import com.tc.webapp01.dao.DAOException;
import com.tc.webapp01.dao.DAOFactory;
import com.tc.webapp01.dao.FacultyDAO;
import com.tc.webapp01.dao.UserDAO;
import com.tc.webapp01.entity.Faculty;
import com.tc.webapp01.service.FacultyService;
import com.tc.webapp01.service.SQLFactory;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FacultyServiceImpl implements FacultyService {
    @Override
    public List<Faculty> allFaculties() throws SQLException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        FacultyDAO facultyDAO = daoFactory.getFacultyDAO();
        List<Faculty> faculties;
        faculties = facultyDAO.allFaculties();
        return faculties;

    }
}
