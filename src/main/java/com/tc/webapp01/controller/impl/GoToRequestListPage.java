package com.tc.webapp01.controller.impl;

import com.tc.webapp01.controller.Command;
import com.tc.webapp01.dao.DAOException;
import com.tc.webapp01.entity.Applicant;
import com.tc.webapp01.entity.Request;
import com.tc.webapp01.service.AdminService;
import com.tc.webapp01.service.FacultyService;
import com.tc.webapp01.service.ServiceException;
import com.tc.webapp01.service.ServiceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GoToRequestListPage implements Command {

    private static final String REQUEST_LIST = "requestList";
    private static final String WEB_INF_JSP_REQUESTS_LIST_PAGE_JSP = "/WEB-INF/jsp/requestsListPage.jsp";
    private static final String MY_CONTROLLER_COMMAND_GO_TO_ERROR_PAGE = "MyController?command=GO_TO_ERROR_PAGE";
    private static final String MY_CONTROLLER_COMMAND_GO_TO_REQUEST_LIST_PAGE = "MyController?command=GO_TO_REQUEST_LIST_PAGE";
    private static final String URL = "url";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServiceFactory serviceFactory = new ServiceFactory();
        AdminService adminService = serviceFactory.getAdminService();

        HttpSession session=request.getSession();
        session.setAttribute(URL, MY_CONTROLLER_COMMAND_GO_TO_REQUEST_LIST_PAGE);

        int finalScore = 0;
        try {
            List<Request> requestList = adminService.showSpecialistsList();

            request.setAttribute(REQUEST_LIST, requestList);


        }  catch (ServiceException e) {
            response.sendRedirect(MY_CONTROLLER_COMMAND_GO_TO_ERROR_PAGE);
        }


        RequestDispatcher dispatcher = request.getRequestDispatcher(WEB_INF_JSP_REQUESTS_LIST_PAGE_JSP);
        dispatcher.forward(request, response);


    }
}
