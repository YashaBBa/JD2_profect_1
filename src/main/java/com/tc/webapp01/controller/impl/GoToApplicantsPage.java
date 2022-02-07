package com.tc.webapp01.controller.impl;

import com.tc.webapp01.controller.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToApplicantsPage implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher requestDispatcher= request.getRequestDispatcher("/WEB-INF/jsp/applicantsData.jsp");
        requestDispatcher.forward(request,response);
    }
}
