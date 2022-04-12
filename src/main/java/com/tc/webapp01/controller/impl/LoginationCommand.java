package com.tc.webapp01.controller.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tc.webapp01.controller.Command;
import com.tc.webapp01.service.ServiceException;
import com.tc.webapp01.service.ServiceFactory;
import com.tc.webapp01.service.UserService;

public class LoginationCommand implements Command {


    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String ROLE = "role";
    private static final String USER_ID = "userID";
    private static final String MY_CONTROLLER_COMMAND_GO_TO_MAIN_PAGE = "MyController?command=GO_TO_MAIN_PAGE&";
    private static final String EXCEPTION_REDIRECT = "MyController?command=GO_TO_ERROR_PAGE";
    private static final String MY_CONTROLLER_COMMAND_GO_TO_INDEX_PAGE = "MyController?command=GO_TO_INDEX_PAGE";
    private static final String CHECK_UR_PASSWORD_OR_EMAIL = "Check ur password or email";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



        String login;
        String passwrod;
        login = request.getParameter(LOGIN);
        passwrod = request.getParameter(PASSWORD);


        ServiceFactory factory = ServiceFactory.getInstance();
        UserService userService = factory.getUserService();
        String role;
        int userID;


        try {
            role = userService.authorisation(login, passwrod);
            userID = userService.getUserID(login, passwrod);

            HttpSession session = request.getSession();
            if (null != role) {
                session.setAttribute(ROLE, role);
                session.setAttribute(USER_ID, userID);

                System.out.println(session.getAttribute(ROLE));
                System.out.println(session.getAttribute(USER_ID));
                response.sendRedirect(MY_CONTROLLER_COMMAND_GO_TO_MAIN_PAGE +
                        "loginationInfo=" + "SSS");

            } else {
                System.out.println(CHECK_UR_PASSWORD_OR_EMAIL);
                response.sendRedirect(MY_CONTROLLER_COMMAND_GO_TO_INDEX_PAGE);

            }

        } catch (ServiceException e) {
            response.sendRedirect(EXCEPTION_REDIRECT);
        }


    }

}
