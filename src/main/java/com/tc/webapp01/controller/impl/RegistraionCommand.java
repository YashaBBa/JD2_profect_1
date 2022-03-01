package com.tc.webapp01.controller.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tc.webapp01.controller.Command;
import com.tc.webapp01.entity.Applicant;
import com.tc.webapp01.entity.User;
import com.tc.webapp01.service.ServiceException;
import com.tc.webapp01.service.ServiceFactory;
import com.tc.webapp01.service.UserService;

public class RegistraionCommand implements Command {


    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String PASSPORT = "passport";
    private static final String STUDY_FORMAT = "studyFormat";
    private static final String MY_CONTROLLER_COMMAND_GO_TO_INDEX_PAGE_REGISTRATION_INFO = "MyController?command=GO_TO_INDEX_PAGE&registrationInfo=";
    private static final String COMPLETE = "Complete";
    private static final String ERROR_MESSAGE = "errorMessage";
    private static final String SMTH_WRONG = "smth wrong";
    private static final String WEB_INF_JSP_REGISTRATION_JSP = "/WEB-INF/jsp/registration.jsp";
    private static final String PRIVILEGES = "privileges";
    private static final String EXCEPTION_REDIRECT = "MyController?command=GO_TO_ERROR_PAGE";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login;
        String password;
        String name;
        String surname;
        String passport;
        String studyFormat;
        String privileges;


        login = request.getParameter(LOGIN);
        password = request.getParameter(PASSWORD);
        name = request.getParameter(NAME);
        surname = request.getParameter(SURNAME);
        passport = request.getParameter(PASSPORT);
        studyFormat = request.getParameter(STUDY_FORMAT);
        privileges = request.getParameter(PRIVILEGES);
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        Applicant applicant = new Applicant();
        applicant.setName(name);
        applicant.setSurname(surname);
        applicant.setPassport(passport);
        applicant.setStudyFormat(studyFormat);
        applicant.setPrivileges(privileges);


        System.out.println(login + " - " + password);
        ServiceFactory factory = ServiceFactory.getInstance();
        UserService userService = factory.getUserService();
        boolean flag = false;
        try {
            flag = !userService.loginExists(login);
        } catch (ServiceException e) {
            response.sendRedirect(EXCEPTION_REDIRECT);
        }

        if (flag) {

            try {
                userService.registration(user,applicant);
            } catch (ServiceException e) {
                response.sendRedirect(EXCEPTION_REDIRECT);
            }

            response.sendRedirect(MY_CONTROLLER_COMMAND_GO_TO_INDEX_PAGE_REGISTRATION_INFO + COMPLETE);

        } else {
            request.setAttribute(ERROR_MESSAGE, SMTH_WRONG);
            RequestDispatcher dispatcher = request.getRequestDispatcher(WEB_INF_JSP_REGISTRATION_JSP);
            dispatcher.forward(request, response);
        }


    }

}
