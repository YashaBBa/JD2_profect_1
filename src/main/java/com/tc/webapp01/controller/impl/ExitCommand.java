package com.tc.webapp01.controller.impl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ExitCommand implements com.tc.webapp01.controller.Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("role", null);
        session.setAttribute("userID", null);

        response.sendRedirect("MyController?command=GO_TO_INDEX_PAGE&");

    }
}
