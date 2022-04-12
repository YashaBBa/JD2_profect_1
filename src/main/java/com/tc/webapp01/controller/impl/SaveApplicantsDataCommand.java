package com.tc.webapp01.controller.impl;

import com.tc.webapp01.controller.Command;
import com.tc.webapp01.entity.Applicant;
import com.tc.webapp01.service.ServiceException;
import com.tc.webapp01.service.ServiceFactory;
import com.tc.webapp01.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SaveApplicantsDataCommand implements Command {

    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String PASSPORT = "passport";
    private static final String STUDY_FORMAT = "studyFormat";
    private static final String MY_CONTROLLER_COMMAND_GO_TO_MAIN_PAGE_REGISTRATION_INFO = "MyController?command=GO_TO_MAIN_PAGE&registrationInfo=";
    private static final String COMPLETE = "GOOD";
    private static final String EXCEPTION_REDIRECT = "MyController?command=GO_TO_ERROR_PAGE";
    private static final String REGISTRATION_INFO = "registrationInfo";
    private static final String WRONG_PASSWORD = "WRONG PASSWORD";
    private static final String ERROR = "ERROR";
    private static final String PASSWORD = "password";
    private static final String PASSWORD_1 = "password1";
    private static final String CHANGE_PASSWORD = "changePassword";
    private static final String USER_ID = "userID";
    private static final String CHANGE_DATA = "changeData";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        String name;
        String surname;
        String passport;
        String studyFormat;
        HttpSession session = request.getSession();

        int id = (int) session.getAttribute(USER_ID);

        ServiceFactory factory = ServiceFactory.getInstance();
        UserService userService = factory.getUserService();
        String message = null;

        if (request.getParameter(CHANGE_DATA) != null) {


            name = request.getParameter(NAME);
            surname = request.getParameter(SURNAME);
            passport = request.getParameter(PASSPORT);
            studyFormat = request.getParameter(STUDY_FORMAT);
            id = (int) session.getAttribute(USER_ID);

            Applicant applicant = new Applicant();
            applicant.setName(name);
            applicant.setSurname(surname);
            applicant.setPassport(passport);
            applicant.setStudyFormat(studyFormat);
            applicant.setApplicantId(id);


            try {
                userService.saveApplicantData(applicant);
            } catch (ServiceException e) {
                response.sendRedirect(EXCEPTION_REDIRECT);
            }
        }
        if (request.getParameter(CHANGE_PASSWORD) != null) {
            String password = request.getParameter(PASSWORD);
            String password1 = request.getParameter(PASSWORD_1);

            try {
                Boolean change = userService.changePassword(password, password1, id);
                if (!change) {
                    request.setAttribute(REGISTRATION_INFO, WRONG_PASSWORD);
                    message = ERROR;


                } else {
                    request.setAttribute(REGISTRATION_INFO, COMPLETE);
                    message = COMPLETE;
                }
            } catch (ServiceException e) {
                response.sendRedirect(EXCEPTION_REDIRECT);
            }


        }


        response.sendRedirect(MY_CONTROLLER_COMMAND_GO_TO_MAIN_PAGE_REGISTRATION_INFO + message);


    }
}

