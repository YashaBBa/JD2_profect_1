package com.tc.webapp01.controller.impl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ExitCommand implements com.tc.webapp01.controller.Command {
    public static final String ROLE = "role";
    public static final String USER_ID = "userID";
    public static final String MY_CONTROLLER_COMMAND_GO_TO_INDEX_PAGE = "MyController?command=GO_TO_INDEX_PAGE&";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute(ROLE, null);

        session.setAttribute(USER_ID, null);

        response.sendRedirect(MY_CONTROLLER_COMMAND_GO_TO_INDEX_PAGE);

    }
}
