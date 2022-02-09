package com.tc.webapp01.dao;

import com.tc.webapp01.dao.impl.SQLAdminDAO;
import com.tc.webapp01.dao.impl.SQLFacultyDAO;
import com.tc.webapp01.dao.impl.SQLSpecialityDAO;
import com.tc.webapp01.dao.impl.SQLUserDAO;
import com.tc.webapp01.service.SQLFactory;

import javax.servlet.ServletContext;
import java.io.File;
import java.util.ResourceBundle;

public final class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();

    private final FacultyDAO facultyDAO = new SQLFacultyDAO();

    private final UserDAO userDAO = new SQLUserDAO();

    private final SpecialytiesDAO specialytiesDAO = new SQLSpecialityDAO();


    private final AdminDAO adminDAO = new SQLAdminDAO();

    public SpecialytiesDAO getSpecialytiesDAO() {
        return specialytiesDAO;
    }

    private DAOFactory() {
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public FacultyDAO getFacultyDAO() {
        return facultyDAO;
    }

    public static DAOFactory getInstance() {
        return instance;
    }

    public AdminDAO getAdminDAO() {
        return adminDAO;
    }

}
