package com.tc.webapp01.controller.impl;

import com.tc.webapp01.controller.Command;
import com.tc.webapp01.entity.Applicant;
import com.tc.webapp01.entity.User;
import com.tc.webapp01.service.ApplicantsService;
import com.tc.webapp01.service.ServiceException;
import com.tc.webapp01.service.ServiceFactory;
import com.tc.webapp01.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class SaveApplicantsDataCommand implements Command {

    public static final String NAME = "name";
    public static final String SURNAME = "surname";
    public static final String PASSPORT = "passport";
    public static final String STUDY_FORMAT = "studyFormat";
    public static final String MY_CONTROLLER_COMMAND_GO_TO_MAIN_PAGE_REGISTRATION_INFO = "MyController?command=GO_TO_MAIN_PAGE&registrationInfo=";
    public static final String COMPLETE = "Complete";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name;
        String surname;
        String passport;
        String studyFormat;


        name = request.getParameter(NAME);
        surname = request.getParameter(SURNAME);
        passport = request.getParameter(PASSPORT);
        studyFormat = request.getParameter(STUDY_FORMAT);
        Applicant applicant = new Applicant();
        applicant.setName(name);
        applicant.setSurname(surname);
        applicant.setPassport(passport);
        applicant.setStudyFormat(studyFormat);


        ServiceFactory factory = ServiceFactory.getInstance();
        ApplicantsService applicantsService = factory.getApplicantsService();

         try {
                applicantsService.saveApplicantData(applicant);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            response.sendRedirect(MY_CONTROLLER_COMMAND_GO_TO_MAIN_PAGE_REGISTRATION_INFO + COMPLETE);


    }
}

