package com.tc.webapp01.controller;

import com.tc.webapp01.service.ServiceException;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String COMMAND = "command";

	private final CommandProvider provider = new CommandProvider();
       
    public Controller() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			process(request, response);
		} catch (SQLException e) {
			try {
				throw new SQLException();
			} catch (SQLException ex) {
				System.out.println("SQLEXCEPTION");
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			process(request, response);
		} catch (SQLException e) {
			try {
				throw new SQLException();
			} catch (SQLException ex) {
				System.out.println("SQLEXCEPTION");
			}
		}
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
		
		String commandName = request.getParameter(COMMAND);
		
		Command command = provider.getCommand(commandName);
		try {
			command.execute(request, response);
		} catch (ServiceException e) {
			System.out.println("SERVICEEXCEPTION");
		}

	}

}
