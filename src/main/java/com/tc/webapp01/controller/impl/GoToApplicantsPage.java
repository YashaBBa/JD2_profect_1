package com.tc.webapp01.controller.impl;

import com.tc.webapp01.controller.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToApplicantsPage implements Command {

    public static final String WEB_INF_JSP_APPLICANTS_DATA_JSP = "/WEB-INF/jsp/applicantsData.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher requestDispatcher= request.getRequestDispatcher(WEB_INF_JSP_APPLICANTS_DATA_JSP);
        requestDispatcher.forward(request,response);
    }
}
