package com.tc.webapp01.controller.impl;

import com.tc.webapp01.controller.Command;
import com.tc.webapp01.service.AdminService;
import com.tc.webapp01.service.ServiceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class ApplayRequest implements Command {

    public static final String APPLICANT_ID = "applicantID";
    public static final String SPECIALITY_ID = "specialityID";
    public static final String MIDL_SCORE = "midlScore";
    public static final String REQUEST_ID = "requestID";
    public static final String MY_CONTROLLER_COMMAND_GO_TO_REQUEST_LIST_PAGE = "MyController?command=GO_TO_REQUEST_LIST_PAGE";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;

        ServiceFactory serviceFactory = new ServiceFactory();
        AdminService adminService = serviceFactory.getAdminService();
        int applicantID = Integer.parseInt(request.getParameter(APPLICANT_ID));
        int specialityID = Integer.parseInt(request.getParameter(SPECIALITY_ID));
        System.out.println(request.getParameter(MIDL_SCORE));
        String requestID = request.getParameter(REQUEST_ID);
        try {
            Boolean b = adminService.applyRequest(specialityID, applicantID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Boolean deleteRequests = adminService.deleteRequest(applicantID);
        response.sendRedirect(MY_CONTROLLER_COMMAND_GO_TO_REQUEST_LIST_PAGE);


    }
}
