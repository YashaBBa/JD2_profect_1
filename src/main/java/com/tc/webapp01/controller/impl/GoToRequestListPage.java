package com.tc.webapp01.controller.impl;

import com.tc.webapp01.controller.Command;
import com.tc.webapp01.entity.Applicant;
import com.tc.webapp01.entity.Request;
import com.tc.webapp01.service.AdminService;
import com.tc.webapp01.service.FacultyService;
import com.tc.webapp01.service.ServiceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GoToRequestListPage implements Command {

    public static final String REQUEST_LIST = "requestList";
    public static final String WEB_INF_JSP_REQUESTS_LIST_PAGE_JSP = "/WEB-INF/jsp/requestsListPage.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServiceFactory serviceFactory = new ServiceFactory();
        AdminService adminService = serviceFactory.getAdminService();
        int finalScore = 0;
        try {
            List<Request> requestList = adminService.showSpecialistsList();

            request.setAttribute(REQUEST_LIST, requestList);


        } catch (SQLException e) {
            e.printStackTrace();
        }


        RequestDispatcher dispatcher = request.getRequestDispatcher(WEB_INF_JSP_REQUESTS_LIST_PAGE_JSP);
        dispatcher.forward(request, response);


    }
}
