package com.tc.webapp01.service;

import com.tc.webapp01.dao.DAOException;
import com.tc.webapp01.entity.Faculty;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface FacultyService {
    List<Faculty> allFaculties() throws ServiceException;

    Date getDate() throws ServiceException;

}
