package com.tc.webapp01.controller.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tc.webapp01.controller.Command;
import com.tc.webapp01.entity.Greeting;

public class GoToregistrationPageCommand implements Command {

	public static final String WEB_INF_JSP_REGISTRATION_JSP = "/WEB-INF/jsp/registration.jsp";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



		RequestDispatcher dispatcher = request.getRequestDispatcher(WEB_INF_JSP_REGISTRATION_JSP);
		dispatcher.forward(request, response);
	}

}
