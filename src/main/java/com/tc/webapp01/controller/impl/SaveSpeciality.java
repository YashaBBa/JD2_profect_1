package com.tc.webapp01.controller.impl;

import com.tc.webapp01.controller.Command;
import com.tc.webapp01.entity.Property;
import com.tc.webapp01.entity.Speciality;
import com.tc.webapp01.service.AdminService;
import com.tc.webapp01.service.ServiceException;
import com.tc.webapp01.service.ServiceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SaveSpeciality implements Command {

    private static final String SPECIALITY_NAME = "specialityName";
    private static final String FACULTY_ID = "facultyID";
    private static final String MIN_SCORE = "minScore";
    private static final String COST = "cost";
    private static final String ALL_PLACES = "allPlaces";
    private static final String SPECIAL_PLACES = "specialPlaces";
    private static final String FIRST_SUB = "firstSub";
    private static final String SECOND_SUB = "secondSub";
    private static final String THIRD_SUB = "thirdSub";
    private static final String WEB_INF_JSP_MAIN_PAGE_JSP = "/WEB-INF/jsp/mainPage.jsp";

    private static final String MY_CONTROLLER_COMMAND_GO_TO_ERROR_PAGE = "MyController?command=GO_TO_ERROR_PAGE";
    private static final String MY_CONTROLLER_COMMAND_GO_TO_MAIN_PAGE = "MyController?command=GO_TO_MAIN_PAGE";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServiceFactory serviceFactory = new ServiceFactory();
        AdminService adminService = serviceFactory.getAdminService();
        Speciality speciality = new Speciality();
        speciality.setSpeciality(request.getParameter(SPECIALITY_NAME));
    String specId=request.getParameter(FACULTY_ID);
        speciality.setFacultyID(Integer.valueOf(request.getParameter(FACULTY_ID)));
        speciality.setScore(Integer.valueOf(request.getParameter(MIN_SCORE)));
        int specialityID = 0;
        try {
            specialityID = adminService.saveAndGetNewSpecialityID(speciality);
        }  catch (ServiceException e) {
            response.sendRedirect(MY_CONTROLLER_COMMAND_GO_TO_ERROR_PAGE);
        }

        Property properties = new Property();
        properties.setCost(Double.valueOf(request.getParameter(COST)));
        properties.setPlaces(Integer.valueOf(request.getParameter(ALL_PLACES)));
        properties.setPriferentPlacec(Integer.valueOf(request.getParameter(SPECIAL_PLACES)));
        try {
            adminService.savePropetriesForSpeciality(properties, specialityID);
        }  catch (ServiceException e) {
            response.sendRedirect(MY_CONTROLLER_COMMAND_GO_TO_ERROR_PAGE);
        }
        List<Integer> listOfSubjects = new ArrayList<>();
        listOfSubjects.add(Integer.valueOf(request.getParameter(FIRST_SUB)));
        listOfSubjects.add(Integer.valueOf(request.getParameter(SECOND_SUB)));
        listOfSubjects.add(Integer.valueOf(request.getParameter(THIRD_SUB)));

        for (Integer listOfSubject : listOfSubjects) {
            try {
                adminService.saveSpecialityAndSubjectsConnection(listOfSubject,speciality.getScore()/listOfSubjects.size(),specialityID);
            }  catch (ServiceException e) {
                response.sendRedirect(MY_CONTROLLER_COMMAND_GO_TO_ERROR_PAGE);
            }
        }


        RequestDispatcher dispatcher = request.getRequestDispatcher(MY_CONTROLLER_COMMAND_GO_TO_MAIN_PAGE);
        dispatcher.forward(request, response);
    }
}
