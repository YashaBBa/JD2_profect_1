package com.tc.webapp01.controller.impl;

import com.tc.webapp01.controller.Command;
import com.tc.webapp01.service.AdminService;
import com.tc.webapp01.service.ServiceException;
import com.tc.webapp01.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;

public class ChangeDate implements Command {
    private static final String MY_CONTROLLER_COMMAND_GO_TO_ERROR_PAGE = "MyController?command=GO_TO_ERROR_PAGE";
    private static final String MY_CONTROLLER_COMMAND_GO_TO_MAIN_PAGE = "MyController?command=GO_TO_MAIN_PAGE&";
    private static final String MY_CONTROLLER_COMMAND_GO_TO_MAIN_PAGE_MESSAGE = "MyController?command=GO_TO_MAIN_PAGE&message=error";
    private static final String DATE = "date";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ServiceException {

        ServiceFactory serviceFactory = new ServiceFactory();
        AdminService adminService = serviceFactory.getAdminService();
        String date = request.getParameter(DATE);
        Boolean exception = true;
        try {
            Date date1 = Date.valueOf(date);
        } catch (IllegalArgumentException e) {

            exception = false;
        }

        try {

            if (exception) {
                Boolean deleteRequests = adminService.changeDate(date);
                response.sendRedirect(MY_CONTROLLER_COMMAND_GO_TO_MAIN_PAGE);

            } else {

                response.sendRedirect(MY_CONTROLLER_COMMAND_GO_TO_MAIN_PAGE_MESSAGE);

            }
        } catch (ServiceException e) {
            response.sendRedirect(MY_CONTROLLER_COMMAND_GO_TO_ERROR_PAGE);
        }



    }
}
