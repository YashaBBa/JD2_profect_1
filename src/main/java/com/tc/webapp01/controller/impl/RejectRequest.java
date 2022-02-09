package com.tc.webapp01.controller.impl;

import com.tc.webapp01.controller.Command;
import com.tc.webapp01.service.AdminService;
import com.tc.webapp01.service.ServiceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class RejectRequest implements Command {

    public static final String APPLICANT_ID = "applicantID";
    public static final String SPECIALITY_ID = "specialityID";
    public static final String MY_CONTROLLER_COMMAND_GO_TO_REQUEST_LIST_PAGE = "MyController?command=GO_TO_REQUEST_LIST_PAGE&";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServiceFactory serviceFactory = new ServiceFactory();
        AdminService adminService = serviceFactory.getAdminService();
        int applicantID = Integer.parseInt(request.getParameter(APPLICANT_ID));
        int specialityID = Integer.parseInt(request.getParameter(SPECIALITY_ID));


        try {
            Boolean deleteRequests = adminService.deleteRequest(applicantID, specialityID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect(MY_CONTROLLER_COMMAND_GO_TO_REQUEST_LIST_PAGE);

    }
}
