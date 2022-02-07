package com.tc.webapp01.controller.impl;

import com.tc.webapp01.controller.Command;
import com.tc.webapp01.entity.Applicant;
import com.tc.webapp01.entity.User;
import com.tc.webapp01.service.ApplicantsService;
import com.tc.webapp01.service.ServiceException;
import com.tc.webapp01.service.ServiceFactory;
import com.tc.webapp01.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class SaveApplicantsDataCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name;
        String surname;
        String passport;
        String studyFormat;


        name = request.getParameter("name");
        surname = request.getParameter("surname");
        passport = request.getParameter("passport");
        studyFormat = request.getParameter("studyFormat");
        Applicant applicant = new Applicant();
        applicant.setName(name);
        applicant.setSurname(surname);
        applicant.setPassport(passport);
        applicant.setStudyFormat(studyFormat);

        System.out.println(1 + " - " + 2);
        ServiceFactory factory = ServiceFactory.getInstance();
        ApplicantsService applicantsService=factory.getApplicantsService();

        boolean flag = true;

        if (flag) {


            try {
                applicantsService.saveApplicantData(applicant);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            response.sendRedirect("MyController?command=GO_TO_MAIN_PAGE&registrationInfo=" + "Complete");
            //RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/mainPage.jsp");
            //dispatcher.forward(request, response);
        } else {
            request.setAttribute("errorMessage", "smth wrong");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/registration.jsp");
            dispatcher.forward(request, response);
        }
    }
}
