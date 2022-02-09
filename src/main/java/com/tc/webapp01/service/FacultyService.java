package com.tc.webapp01.service;

import com.tc.webapp01.entity.Faculty;

import java.sql.SQLException;
import java.util.List;

public interface FacultyService {
    List<Faculty> allFaculties() throws SQLException;

}
