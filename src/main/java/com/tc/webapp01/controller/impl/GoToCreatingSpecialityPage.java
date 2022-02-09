package com.tc.webapp01.controller.impl;

import com.tc.webapp01.controller.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToCreatingSpecialityPage implements Command {

    public static final String WEB_INF_JSP_CREATE_SPECIALITY_PAGE_JSP = "/WEB-INF/jsp/createSpecialityPage.jsp";
    public static final String FACULTY_ID = "facultyID";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        request.setAttribute(FACULTY_ID,request.getParameter(FACULTY_ID));
        requestDispatcher = request.getRequestDispatcher(WEB_INF_JSP_CREATE_SPECIALITY_PAGE_JSP);
        requestDispatcher.forward(request, response);
    }
}
