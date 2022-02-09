package com.tc.webapp01.controller.impl;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tc.webapp01.controller.Command;
import com.tc.webapp01.service.ServiceException;
import com.tc.webapp01.service.ServiceFactory;
import com.tc.webapp01.service.UserService;
import com.tc.webapp01.service.impl.UserServiceImpl;

public class LoginationCommand implements Command {


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("logination");


        String login;
        String passwrod;
        login = request.getParameter("login");
        passwrod = request.getParameter("password");
        String logInfo = "Hi.\n It is main page";

        ServiceFactory factory = ServiceFactory.getInstance();
        UserService userService = factory.getUserService();
        String role;
        int userID;


        try {
            role = userService.authorisation(login, passwrod);
            userID = userService.getUserID(login, passwrod);

            HttpSession session = request.getSession();
            if (null != role) {
                session.setAttribute("role", role);
                session.setAttribute("userID", userID);

                System.out.println(session.getAttribute("role"));
                System.out.println(session.getAttribute("userID"));
                response.sendRedirect("MyController?command=GO_TO_MAIN_PAGE&" +
                        "loginationInfo=" + "SSS");

            } else {
                System.out.println("Check ur password or email");
                response.sendRedirect("MyController?command=GO_TO_INDEX_PAGE&");

            }

        } catch (ServiceException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

}
