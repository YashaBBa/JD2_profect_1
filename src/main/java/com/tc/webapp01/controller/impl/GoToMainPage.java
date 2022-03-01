package com.tc.webapp01.controller.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tc.webapp01.controller.Command;
import com.tc.webapp01.dao.DAOException;
import com.tc.webapp01.entity.Faculty;
import com.tc.webapp01.service.FacultyService;
import com.tc.webapp01.service.ServiceException;
import com.tc.webapp01.service.ServiceFactory;
import com.tc.webapp01.service.impl.FacultyServiceImpl;

public class GoToMainPage implements Command {

    private static final String FACULTY_LIST = "facultyList";
    private static final String WEB_INF_JSP_MAIN_PAGE_JSP = "/WEB-INF/jsp/mainPage.jsp";
    private static final String MY_CONTROLLER_COMMAND_GO_TO_ERROR_PAGE = "MyController?command=GO_TO_ERROR_PAGE";
    private static final String MY_CONTROLLER_COMMAND_GO_TO_MAIN_PAGE = "MyController?command=GO_TO_MAIN_PAGE";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServiceFactory serviceFactory = new ServiceFactory();
        FacultyService facultyService = serviceFactory.getFacultyService();

        HttpSession session=request.getSession();
        session.setAttribute("url", MY_CONTROLLER_COMMAND_GO_TO_MAIN_PAGE);
        try {
            List<Faculty> facultyList= facultyService.allFaculties();
            request.setAttribute(FACULTY_LIST,facultyList);
        } catch (ServiceException e) {
            response.sendRedirect(MY_CONTROLLER_COMMAND_GO_TO_ERROR_PAGE);
        }


        RequestDispatcher dispatcher = request.getRequestDispatcher(WEB_INF_JSP_MAIN_PAGE_JSP);
        dispatcher.forward(request, response);

    }

}
