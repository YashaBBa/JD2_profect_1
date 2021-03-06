package com.tc.webapp01.controller;

import com.tc.webapp01.service.ServiceException;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ServiceException;

}
