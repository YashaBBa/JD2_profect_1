package com.tc.webapp01.controller.impl;

import com.tc.webapp01.controller.Command;
import com.tc.webapp01.entity.Applicant;
import com.tc.webapp01.service.ServiceException;
import com.tc.webapp01.service.ServiceFactory;
import com.tc.webapp01.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SaveApplicantsDataCommand implements Command {

    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String PASSPORT = "passport";
    private static final String STUDY_FORMAT = "studyFormat";
    private static final String MY_CONTROLLER_COMMAND_GO_TO_MAIN_PAGE_REGISTRATION_INFO = "MyController?command=GO_TO_MAIN_PAGE&registrationInfo=";
    private static final String COMPLETE = "Complete";
    private static final String EXCEPTION_REDIRECT = "MyController?command=GO_TO_ERROR_PAGE";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
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

        UserService userService = factory.getUserService();
        try {
            userService.saveApplicantData(applicant);
        } catch (ServiceException e) {
            response.sendRedirect(EXCEPTION_REDIRECT);
        }

        response.sendRedirect(MY_CONTROLLER_COMMAND_GO_TO_MAIN_PAGE_REGISTRATION_INFO + COMPLETE);


    }
}

