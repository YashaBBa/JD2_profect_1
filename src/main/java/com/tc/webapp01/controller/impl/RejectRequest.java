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
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServiceFactory serviceFactory = new ServiceFactory();
        AdminService adminService = serviceFactory.getAdminService();
        int applicantID = Integer.parseInt(request.getParameter("applicantID"));
        int specialityID = Integer.parseInt(request.getParameter("specialityID"));
        String requestID = request.getParameter("requestID");

        try {
            Boolean deleteRequests = adminService.deleteRequest(applicantID, specialityID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect("MyController?command=GO_TO_REQUEST_LIST_PAGE&");

    }
}
