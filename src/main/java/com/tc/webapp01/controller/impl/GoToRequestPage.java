package com.tc.webapp01.controller.impl;

import com.tc.webapp01.controller.Command;
import com.tc.webapp01.dao.DAOException;
import com.tc.webapp01.entity.Applicant;
import com.tc.webapp01.entity.Faculty;
import com.tc.webapp01.entity.Subject;
import com.tc.webapp01.service.FacultyService;
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
import java.util.stream.Collectors;

public class GoToRequestPage implements Command {

    private static final String SPECIALITY_ID = "specialityID";
    private static final String LIST_OF_SUBJECTS = "listOfSubjects";
    private static final String APPLICANTS_LIST = "applicantsList";
    private static final String WEB_INF_JSP_REPUEST_PAGE_JSP = "/WEB-INF/jsp/repuestPage.jsp";
    private static final String EXCEPTION_REDIRECT = "MyController?command=GO_TO_ERROR_PAGE";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        int specialityID = Integer.parseInt(request.getParameter(SPECIALITY_ID));
        System.out.println(specialityID);




        ServiceFactory serviceFactory = new ServiceFactory();
        SpecialitiesService specialitiesService = serviceFactory.getSpecialitiesService();
        try {
            List<Subject> listOfSubjects = specialitiesService.getSubjectsList(specialityID);
            List<Applicant> applicantsList = specialitiesService.getApplicantsList(specialityID);
            request.setAttribute(LIST_OF_SUBJECTS, listOfSubjects);
            request.setAttribute(APPLICANTS_LIST, applicantsList);

        } catch (ServiceException e) {
            response.sendRedirect(EXCEPTION_REDIRECT);
        }


        HttpSession session = request.getSession();
        session.setAttribute(SPECIALITY_ID, specialityID);

        session.setAttribute("url","MyController?command=GO_TO_REQUEST_PAGE&specialityID="+specialityID);

        RequestDispatcher dispatcher = request.getRequestDispatcher(WEB_INF_JSP_REPUEST_PAGE_JSP);
        dispatcher.forward(request, response);
    }
}
