package com.tc.webapp01.controller.impl;

import com.tc.webapp01.controller.Command;
import com.tc.webapp01.entity.Properties;
import com.tc.webapp01.entity.Speciality;
import com.tc.webapp01.service.AdminService;
import com.tc.webapp01.service.ServiceFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SaveSpeciality implements Command {

    public static final String SPECIALITY_NAME = "specialityName";
    public static final String FACULTY_ID = "facultyID";
    public static final String MIN_SCORE = "minScore";
    public static final String COST = "cost";
    public static final String ALL_PLACES = "allPlaces";
    public static final String SPECIAL_PLACES = "specialPlaces";
    public static final String FIRST_SUB = "firstSub";
    public static final String SECOND_SUB = "secondSub";
    public static final String THIRD_SUB = "thirdSub";
    public static final String WEB_INF_JSP_MAIN_PAGE_JSP = "/WEB-INF/jsp/mainPage.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServiceFactory serviceFactory = new ServiceFactory();
        AdminService adminService = serviceFactory.getAdminService();
        Speciality speciality = new Speciality();
        speciality.setSpeciality(request.getParameter(SPECIALITY_NAME));
        speciality.setFacultyID(Integer.valueOf(request.getParameter(FACULTY_ID)));
        speciality.setScore(Integer.valueOf(request.getParameter(MIN_SCORE)));
        int specialityID = 0;
        try {
            specialityID = adminService.saveAndGetNewSpecialityID(speciality);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Properties properties = new Properties();
        properties.setCost(Double.valueOf(request.getParameter(COST)));
        properties.setPlaces(Integer.valueOf(request.getParameter(ALL_PLACES)));
        properties.setPriferentPlacec(Integer.valueOf(request.getParameter(SPECIAL_PLACES)));
        try {
            adminService.savePropetriesForSpeciality(properties, specialityID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<Integer> listOfSubjects = new ArrayList<>();
        listOfSubjects.add(Integer.valueOf(request.getParameter(FIRST_SUB)));
        listOfSubjects.add(Integer.valueOf(request.getParameter(SECOND_SUB)));
        listOfSubjects.add(Integer.valueOf(request.getParameter(THIRD_SUB)));

        for (Integer listOfSubject : listOfSubjects) {
            try {
                adminService.saveSpecialityAndSubjectsConnection(listOfSubject,speciality.getScore()/listOfSubjects.size(),specialityID);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        RequestDispatcher requestDispatcher;
        requestDispatcher = request.getRequestDispatcher(WEB_INF_JSP_MAIN_PAGE_JSP);
        requestDispatcher.forward(request, response);
    }
}
