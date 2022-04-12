package com.tc.webapp01.controller.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tc.webapp01.controller.Command;

public class GoToIndexPage implements Command {

	private static final String WEB_INF_JSP_HELLO_PAGE_JSP = "/WEB-INF/jsp/helloPage.jsp";
	private static final String MY_CONTROLLER_COMMAND_GO_TO_INDEX_PAGE = "MyController?command=GO_TO_INDEX_PAGE";
	private static final String URL = "url";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session=request.getSession();
		session.setAttribute(URL, MY_CONTROLLER_COMMAND_GO_TO_INDEX_PAGE);

		
		RequestDispatcher dispatcher = request.getRequestDispatcher(WEB_INF_JSP_HELLO_PAGE_JSP);
		dispatcher.forward(request, response);
		
	}

}

