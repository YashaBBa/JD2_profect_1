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
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServiceFactory serviceFactory = new ServiceFactory();
        AdminService adminService = serviceFactory.getAdminService();
        Speciality speciality = new Speciality();
        speciality.setSpeciality(request.getParameter("specialityName"));
        speciality.setFacultyID(Integer.valueOf(request.getParameter("facultyID")));
        speciality.setScore(Integer.valueOf(request.getParameter("minScore")));
        int specialityID = 0;
        try {
            specialityID = adminService.saveAndGetNewSpecialityID(speciality);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Properties properties = new Properties();
        properties.setCost(Double.valueOf(request.getParameter("cost")));
        properties.setPlaces(Integer.valueOf(request.getParameter("allPlaces")));
        properties.setPriferentPlacec(Integer.valueOf(request.getParameter("specialPlaces")));
        try {
            adminService.savePropetriesForSpeciality(properties, specialityID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<Integer> listOfSubjects = new ArrayList<>();
        listOfSubjects.add(Integer.valueOf(request.getParameter("firstSub")));
        listOfSubjects.add(Integer.valueOf(request.getParameter("secondSub")));
        listOfSubjects.add(Integer.valueOf(request.getParameter("thirdSub")));

        for (Integer listOfSubject : listOfSubjects) {
            try {
                adminService.saveSpecialityAndSubjectsConnection(listOfSubject,speciality.getScore()/listOfSubjects.size(),specialityID);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        RequestDispatcher requestDispatcher;
        requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/mainPage.jsp");
        requestDispatcher.forward(request, response);
    }
}
