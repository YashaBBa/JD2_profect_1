package com.tc.webapp01.controller.impl;

import com.tc.webapp01.controller.Command;
import com.tc.webapp01.entity.Applicant;
import com.tc.webapp01.service.AdminService;
import com.tc.webapp01.service.ServiceException;
import com.tc.webapp01.service.ServiceFactory;
import com.tc.webapp01.service.UserService;
import com.tc.webapp01.service.impl.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class GoToApplicantsPage implements Command {

    private static final String WEB_INF_JSP_APPLICANTS_DATA_JSP = "/WEB-INF/jsp/applicantsData.jsp";
    private static final String MY_CONTROLLER_COMMAND_GO_TO_APPLICANTSDATA_PAGE = "MyController?command=GO_TO_APPLICANTSDATA_PAGE";
    private static final String EXCEPTION_REDIRECT = "MyController?command=GO_TO_ERROR_PAGE";
    private static final String APPLICANT_DATA = "applicantData";
    private static final String USER_ID = "userID";
    private static final String URL = "url";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute(URL, MY_CONTROLLER_COMMAND_GO_TO_APPLICANTSDATA_PAGE);
        String id = String.valueOf(session.getAttribute(USER_ID));
        ServiceFactory serviceFactory = new ServiceFactory();
        UserService userService = serviceFactory.getUserService();
        try {
            Applicant applicantData = userService.getUserData(id);
            request.setAttribute(APPLICANT_DATA,applicantData);
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
            response.sendRedirect(EXCEPTION_REDIRECT);
        }


        RequestDispatcher requestDispatcher = request.getRequestDispatcher(WEB_INF_JSP_APPLICANTS_DATA_JSP);
        requestDispatcher.forward(request, response);
    }
}
