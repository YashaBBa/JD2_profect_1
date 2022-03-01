package com.tc.webapp01.dao.impl;

import com.tc.webapp01.pool.ConnectionPool;
import com.tc.webapp01.pool.ConnectionPoolException;
import com.tc.webapp01.dao.DAOException;
import com.tc.webapp01.dao.FacultyDAO;
import com.tc.webapp01.entity.Faculty;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLFacultyDAO implements FacultyDAO {
    private static final String CONNECTION_POOL_EXCEPTION_MESSAGE = "Database server connection has problem";
    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String SELECT_FROM_APPLICATIONSSYSTEM_FACULTIES = "SELECT * FROM applicationssystem.faculties;";
    private static final String FACULTY = "faculty";
    private static final String IDFACULTIES = "idfaculties";

    @Override
    public List<Faculty> allFaculties() throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String SQL = SELECT_FROM_APPLICATIONSSYSTEM_FACULTIES;
        List<Faculty> faculties = new ArrayList<>();
        try {
            connection = connectionPool.takeConnection();
            statement = connection.prepareCall(SQL);
            statement.execute();
            resultSet = statement.getResultSet();

            while (resultSet.next()) {
                Faculty faculty = new Faculty();
                faculty.setFaculty(resultSet.getString(FACULTY));
                faculty.setId(resultSet.getInt(IDFACULTIES));
                faculties.add(faculty);

            }
        } catch (ConnectionPoolException e) {
            throw new DAOException(CONNECTION_POOL_EXCEPTION_MESSAGE, e);
        } catch (SQLException e) {
            throw new DAOException("SQL command exception", e);
        } finally {
            connectionPool.closeConnection(connection, statement, resultSet);
        }

        return faculties;
    }
}
