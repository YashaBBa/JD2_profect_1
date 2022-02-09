package com.tc.webapp01.controller.impl;

import com.tc.webapp01.controller.Command;
import com.tc.webapp01.entity.Applicant;
import com.tc.webapp01.entity.Faculty;
import com.tc.webapp01.entity.Subject;
import com.tc.webapp01.service.FacultyService;
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

public class GoToRequestPage implements Command {

    public static final String SPECIALITY_ID = "specialityID";
    public static final String LIST_OF_SUBJECTS = "listOfSubjects";
    public static final String APPLICANTS_LIST = "applicantsList";
    public static final String WEB_INF_JSP_REPUEST_PAGE_JSP = "/WEB-INF/jsp/repuestPage.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        int specialityID = Integer.parseInt(request.getParameter(SPECIALITY_ID));
        System.out.println(specialityID);


        ServiceFactory serviceFactory = new ServiceFactory();
        SpecialitiesService specialitiesService = serviceFactory.getSpecialitiesService();
        try {
            List<Subject> listOfSubjects = specialitiesService.getSubjectsList(specialityID);
            List<Applicant> applicantsList= specialitiesService.getApplicantsList(specialityID);

            request.setAttribute(LIST_OF_SUBJECTS, listOfSubjects);
            request.setAttribute(APPLICANTS_LIST, applicantsList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        HttpSession session = request.getSession();
        session.setAttribute(SPECIALITY_ID,specialityID);

        RequestDispatcher dispatcher = request.getRequestDispatcher(WEB_INF_JSP_REPUEST_PAGE_JSP);
        dispatcher.forward(request, response);
    }
}
