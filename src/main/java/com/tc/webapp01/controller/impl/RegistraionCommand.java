package com.tc.webapp01.controller.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tc.webapp01.controller.Command;
import com.tc.webapp01.entity.Applicant;
import com.tc.webapp01.entity.User;
import com.tc.webapp01.service.ServiceException;
import com.tc.webapp01.service.ServiceFactory;
import com.tc.webapp01.service.UserService;

public class RegistraionCommand implements Command {


    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final String NAME = "name";
    public static final String SURNAME = "surname";
    public static final String PASSPORT = "passport";
    public static final String STUDY_FORMAT = "studyFormat";
    public static final String MY_CONTROLLER_COMMAND_GO_TO_INDEX_PAGE_REGISTRATION_INFO = "MyController?command=GO_TO_INDEX_PAGE&registrationInfo=";
    public static final String COMPLETE = "Complete";
    public static final String ERROR_MESSAGE = "errorMessage";
    public static final String SMTH_WRONG = "smth wrong";
    public static final String WEB_INF_JSP_REGISTRATION_JSP = "/WEB-INF/jsp/registration.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login;
        String password;
        String name;
        String surname;
        String passport;
        String studyFormat;


        login = request.getParameter(LOGIN);
        password = request.getParameter(PASSWORD);
        name = request.getParameter(NAME);
        surname = request.getParameter(SURNAME);
        passport = request.getParameter(PASSPORT);
        studyFormat = request.getParameter(STUDY_FORMAT);
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        Applicant applicant = new Applicant();
        applicant.setName(name);
        applicant.setSurname(surname);
        applicant.setPassport(passport);
        applicant.setStudyFormat(studyFormat);


        System.out.println(login + " - " + password);
        ServiceFactory factory = ServiceFactory.getInstance();
        UserService userService = factory.getUserService();
        boolean flag = false;
        try {
            flag = !userService.loginExists(login);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (flag) {

            try {
                userService.registration(user,applicant);
            } catch (ServiceException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            response.sendRedirect(MY_CONTROLLER_COMMAND_GO_TO_INDEX_PAGE_REGISTRATION_INFO + COMPLETE);

        } else {
            request.setAttribute(ERROR_MESSAGE, SMTH_WRONG);
            RequestDispatcher dispatcher = request.getRequestDispatcher(WEB_INF_JSP_REGISTRATION_JSP);
            dispatcher.forward(request, response);
        }


    }

}
