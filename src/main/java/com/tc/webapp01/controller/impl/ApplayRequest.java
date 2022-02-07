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
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;

        ServiceFactory serviceFactory = new ServiceFactory();
        AdminService adminService = serviceFactory.getAdminService();
        int applicantID = Integer.parseInt(request.getParameter("applicantID"));
        int specialityID = Integer.parseInt(request.getParameter("specialityID"));
        System.out.println(request.getParameter("midlScore"));
        String requestID = request.getParameter("requestID");
        try {
            Boolean b = adminService.applyRequest(specialityID, applicantID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Boolean deleteRequests = adminService.deleteRequest(applicantID);
        response.sendRedirect("MyController?command=GO_TO_REQUEST_LIST_PAGE&" +
                "loginationInfo=" + "SSS");


    }
}
