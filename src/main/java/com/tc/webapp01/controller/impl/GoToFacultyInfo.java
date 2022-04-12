package com.tc.webapp01.controller.impl;

import com.tc.webapp01.controller.Command;
import com.tc.webapp01.dao.DAOException;
import com.tc.webapp01.entity.Speciality;
import com.tc.webapp01.service.ServiceException;
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
    public static final String URL = "url";
    private static final String FACULTY_ID = "facultyId";
    private static final String SPECIALTIES = "specialties";
    private static final String WEB_INF_JSP_FACULTIES_JSP_FKP_JSP = "/WEB-INF/jsp/FacultiesJSP/fkp.jsp";
    private static final String EXCEPTION_REDIRECT = "MyController?command=GO_TO_ERROR_PAGE";
    private static final String MY_CONTROLLER_COMMAND_SHOW_FACULTY_INFO_FACULTY_ID = "MyController?command=showFacultyInfo&facultyId=";
    private static final String DEADLINE_TIME = "deadlineTime";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        System.out.println(request.getParameter(FACULTY_ID));


        int facultyID = Integer.parseInt(request.getParameter(FACULTY_ID));
        ServiceFactory serviceFactory = new ServiceFactory();
        SpecialitiesService specialitiesService = serviceFactory.getSpecialitiesService();
        try {
            List<Speciality> specialityList = specialitiesService.allSpecialities(facultyID);
            Boolean requestTime = specialitiesService.checkDeadLineTime();
            request.setAttribute(DEADLINE_TIME,requestTime);
            request.setAttribute(SPECIALTIES, specialityList);
            request.setAttribute(FACULTY_ID, facultyID);

        } catch (ServiceException e) {

            response.sendRedirect(EXCEPTION_REDIRECT);
        }
        HttpSession session = request.getSession();
        session.setAttribute(URL, MY_CONTROLLER_COMMAND_SHOW_FACULTY_INFO_FACULTY_ID + facultyID);

        requestDispatcher = request.getRequestDispatcher(WEB_INF_JSP_FACULTIES_JSP_FKP_JSP);
        requestDispatcher.forward(request, response);


    }

}


