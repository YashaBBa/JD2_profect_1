package com.tc.webapp01.controller.impl;

import com.tc.webapp01.controller.Command;
import com.tc.webapp01.dao.SpecialytiesDAO;
import com.tc.webapp01.dao.impl.SQLSpecialityDAO;
import com.tc.webapp01.entity.Faculty;
import com.tc.webapp01.entity.Properties;
import com.tc.webapp01.entity.Speciality;
import com.tc.webapp01.service.ServiceFactory;
import com.tc.webapp01.service.SpecialitiesService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class GoToFacultyInfo implements Command {
    public static final String FACULTY_ID = "facultyId";
    public static final String SPECIALTIES = "specialties";
    public static final String WEB_INF_JSP_FACULTIES_JSP_FKP_JSP = "/WEB-INF/jsp/FacultiesJSP/fkp.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        System.out.println(request.getParameter(FACULTY_ID));

        int facultyID = Integer.parseInt(request.getParameter(FACULTY_ID));
        ServiceFactory serviceFactory = new ServiceFactory();
        SpecialitiesService specialitiesService = serviceFactory.getSpecialitiesService();





        try {

            List<Speciality> specialityList = specialitiesService.allSpecialities(facultyID);


            request.setAttribute(SPECIALTIES, specialityList);
            request.setAttribute(FACULTY_ID,facultyID);

        } catch (SQLException e) {
            e.printStackTrace();
        }


        requestDispatcher = request.getRequestDispatcher(WEB_INF_JSP_FACULTIES_JSP_FKP_JSP);
        requestDispatcher.forward(request, response);


    }

}


