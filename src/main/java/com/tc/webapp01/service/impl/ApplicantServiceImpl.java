package com.tc.webapp01.service.impl;

import com.tc.webapp01.entity.Applicant;
import com.tc.webapp01.entity.User;
import com.tc.webapp01.service.ApplicantsService;
import com.tc.webapp01.service.SQLFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ApplicantServiceImpl implements ApplicantsService {

    @Override
    public Boolean saveApplicantData(Applicant applicant) throws SQLException {

        Connection connection = SQLFactory.getConnection();
        String SQL = String.format("insert into applicants (name,surname,passport,study_format) VALUES(\"%s\",\"%s\",\"%s\",\"%s\");", applicant.getName(), applicant.getSurname(), applicant.getPassport(), applicant.getStudyFormat());
        CallableStatement statement = connection.prepareCall(SQL);
        statement.execute();

        return false;
    }
}
