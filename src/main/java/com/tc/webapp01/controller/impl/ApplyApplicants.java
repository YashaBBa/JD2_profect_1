package com.tc.webapp01.controller.impl;

import com.tc.webapp01.controller.Command;
import com.tc.webapp01.entity.Applicant;
import com.tc.webapp01.entity.Speciality;
import com.tc.webapp01.service.AdminService;
import com.tc.webapp01.service.ServiceException;
import com.tc.webapp01.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ApplyApplicants implements Command {
    private static final String MY_CONTROLLER_COMMAND_GO_TO_MAIN_PAGE = "MyController?command=GO_TO_MAIN_PAGE&";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ServiceException {
        ServiceFactory serviceFactory = new ServiceFactory();
        AdminService adminService = serviceFactory.getAdminService();
        List<Speciality> listOfSpecialitisWithRequests = adminService.getAllSpecialitisWhithRequests();

        Boolean sortAllApplicants = adminService.sortAllApplitantsRequestsByPriotityAndScore(listOfSpecialitisWithRequests);
        response.sendRedirect(MY_CONTROLLER_COMMAND_GO_TO_MAIN_PAGE);


    }
}
