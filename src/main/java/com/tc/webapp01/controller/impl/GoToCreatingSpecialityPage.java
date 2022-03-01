package com.tc.webapp01.controller.impl;

import com.tc.webapp01.controller.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class GoToCreatingSpecialityPage implements Command {

    private static final String WEB_INF_JSP_CREATE_SPECIALITY_PAGE_JSP = "/WEB-INF/jsp/createSpecialityPage.jsp";
    private static final String FACULTY_ID = "facultyID";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        request.setAttribute(FACULTY_ID,request.getParameter(FACULTY_ID));

        HttpSession session=request.getSession();
        session.setAttribute("url","MyController?command=GO_TO_SAVE_NEW_SPECIALITY_PAGE");

        requestDispatcher = request.getRequestDispatcher(WEB_INF_JSP_CREATE_SPECIALITY_PAGE_JSP);
        requestDispatcher.forward(request, response);
    }
}
