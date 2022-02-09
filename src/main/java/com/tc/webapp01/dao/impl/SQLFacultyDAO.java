package com.tc.webapp01.dao.impl;

import com.tc.webapp01.dao.FacultyDAO;
import com.tc.webapp01.entity.Faculty;
import com.tc.webapp01.service.SQLFactory;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLFacultyDAO implements FacultyDAO {

    public static final String SELECT_FROM_APPLICATIONSSYSTEM_FACULTIES = "SELECT * FROM applicationssystem.faculties;";
    public static final String FACULTY = "faculty";
    public static final String IDFACULTIES = "idfaculties";

    @Override
    public List<Faculty> allFaculties() throws SQLException {
        Connection connection = SQLFactory.getConnection();
        String SQL = SELECT_FROM_APPLICATIONSSYSTEM_FACULTIES;

        CallableStatement statement = connection.prepareCall(SQL);
        statement.execute();
        ResultSet resultSet = statement.getResultSet();
        List<Faculty> faculties = new ArrayList<>();
        while (resultSet.next()) {
            Faculty faculty = new Faculty();
            faculty.setFaculty(resultSet.getString(FACULTY));
            faculty.setId(resultSet.getInt(IDFACULTIES));
            faculties.add(faculty);

        }
        try {
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return faculties;
    }
}
