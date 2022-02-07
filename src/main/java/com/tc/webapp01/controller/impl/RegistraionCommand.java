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


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login;
        String password;
        String name;
        String surname;
        String passport;
        String studyFormat;

        HttpSession session = request.getSession();
        session.setAttribute("url", "request url forming process");// application/Controller?aaa=qqq&ddd=hhh

        // save data into DB

        login = request.getParameter("login");
        password = request.getParameter("password");
        name = request.getParameter("name");
        surname = request.getParameter("surname");
        passport = request.getParameter("passport");
        studyFormat = request.getParameter("studyFormat");
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

            response.sendRedirect("MyController?command=GO_TO_INDEX_PAGE&registrationInfo=" + "Complete");

        } else {
            request.setAttribute("errorMessage", "smth wrong");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/registration.jsp");
            dispatcher.forward(request, response);
        }


    }

}
