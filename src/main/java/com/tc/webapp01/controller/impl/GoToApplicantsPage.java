package com.tc.webapp01.controller.impl;

import com.tc.webapp01.controller.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class GoToApplicantsPage implements Command {

    private static final String WEB_INF_JSP_APPLICANTS_DATA_JSP = "/WEB-INF/jsp/applicantsData.jsp";
    private static final String MY_CONTROLLER_COMMAND_GO_TO_APPLICANTSDATA_PAGE = "MyController?command=GO_TO_APPLICANTSDATA_PAGE";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        session.setAttribute("url", MY_CONTROLLER_COMMAND_GO_TO_APPLICANTSDATA_PAGE);

        RequestDispatcher requestDispatcher= request.getRequestDispatcher(WEB_INF_JSP_APPLICANTS_DATA_JSP);
        requestDispatcher.forward(request,response);
    }
}
