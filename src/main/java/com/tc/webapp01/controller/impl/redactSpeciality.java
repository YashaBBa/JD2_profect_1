package com.tc.webapp01.controller.impl;

import com.tc.webapp01.controller.Command;
import com.tc.webapp01.service.AdminService;
import com.tc.webapp01.service.ServiceException;
import com.tc.webapp01.service.ServiceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class redactSpeciality implements Command {

    private static final String MY_CONTROLLER_COMMAND_GO_TO_MAIN_PAGE = "MyController?command=GO_TO_MAIN_PAGE";
    private static final String SP = "sp";
    private static final String DELETE = "delete";
    private static final String COST = "cost";
    private static final String PREF_PLACES = "prefPlaces";
    private static final String PLACES = "places";
    private static final String SCORE = "score";
    private static final String SPEC_ID = "specID";
    private static final String UPDATE = "update";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ServiceException {
        RequestDispatcher requestDispatcher;

        ServiceFactory serviceFactory = new ServiceFactory();
        AdminService adminService = serviceFactory.getAdminService();
        if (request.getParameter(UPDATE) != null) {

            String specID = request.getParameter(SPEC_ID);
            String score = request.getParameter(SCORE);
            String places = request.getParameter(PLACES);
            String prefPlaces = request.getParameter(PREF_PLACES);
            String cost = request.getParameter(COST);

            adminService.updateSpecialityParam(specID, score, places, prefPlaces, cost);
        }
        if (request.getParameter(DELETE) != null) {
            String param = request.getParameter(SP);
            adminService.redactSpeciality(param);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(MY_CONTROLLER_COMMAND_GO_TO_MAIN_PAGE);
        dispatcher.forward(request, response);
    }
}
