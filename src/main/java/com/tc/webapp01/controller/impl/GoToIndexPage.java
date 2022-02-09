package com.tc.webapp01.controller.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tc.webapp01.controller.Command;

public class GoToIndexPage implements Command {

	public static final String WEB_INF_JSP_HELLO_PAGE_JSP = "/WEB-INF/jsp/helloPage.jsp";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		
		RequestDispatcher dispatcher = request.getRequestDispatcher(WEB_INF_JSP_HELLO_PAGE_JSP);
		dispatcher.forward(request, response);
		
	}

}

