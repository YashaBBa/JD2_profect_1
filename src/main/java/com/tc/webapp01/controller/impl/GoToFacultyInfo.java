package com.tc.webapp01.controller.impl;

import com.tc.webapp01.controller.Command;
import com.tc.webapp01.dao.SpecialytiesDAO;
import com.tc.webapp01.dao.impl.SQLSpecialityDAO;
import com.tc.webapp01.entity.Faculty;
import com.tc.webapp01.entity.Properties;
import com.tc.webapp01.entity.Speciality;
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

public class GoToFacultyInfo implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        System.out.println(request.getParameter("facultyId"));

        int facultyID = Integer.parseInt(request.getParameter("facultyId"));
        ServiceFactory serviceFactory = new ServiceFactory();
        SpecialitiesService specialitiesService = serviceFactory.getSpecialitiesService();

        HttpSession session = request.getSession();



        try {

            List<Speciality> specialityList = specialitiesService.allSpecialities(facultyID);


            request.setAttribute("specialties", specialityList);
            request.setAttribute("facultyID",facultyID);

        } catch (SQLException e) {
            e.printStackTrace();
        }


        requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/FacultiesJSP/fkp.jsp");
        requestDispatcher.forward(request, response);


    }

}


