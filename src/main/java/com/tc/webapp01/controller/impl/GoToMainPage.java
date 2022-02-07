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
import com.tc.webapp01.entity.Faculty;
import com.tc.webapp01.service.FacultyService;
import com.tc.webapp01.service.ServiceFactory;
import com.tc.webapp01.service.impl.FacultyServiceImpl;

public class GoToMainPage implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServiceFactory serviceFactory = new ServiceFactory();
        FacultyService facultyService = serviceFactory.getFacultyService();
        try {
            List<Faculty> facultyList= facultyService.allFaculties();
            request.setAttribute("facultyList",facultyList);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/mainPage.jsp");
        dispatcher.forward(request, response);

    }

}
