package com.tc.webapp01.service;

import com.tc.webapp01.entity.Applicant;
import com.tc.webapp01.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.http.HttpRequest;
import java.sql.SQLException;

public interface ApplicantsService {
    public Boolean saveApplicantData(Applicant applicant) throws SQLException;
}
