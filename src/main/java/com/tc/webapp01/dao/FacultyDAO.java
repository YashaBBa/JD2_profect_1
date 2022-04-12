package com.tc.webapp01.dao;

import com.tc.webapp01.entity.Faculty;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface FacultyDAO {
    List<Faculty> allFaculties() throws DAOException;

    Date getData() throws DAOException;

}
