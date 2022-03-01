package com.tc.webapp01.controller.impl;

import com.tc.webapp01.controller.Command;
import com.tc.webapp01.entity.Request;
import com.tc.webapp01.service.ServiceException;
import com.tc.webapp01.service.ServiceFactory;
import com.tc.webapp01.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RequestCommand implements Command {

    private static final String USER_ID = "userID";
    private static final String SPECIALITY_ID = "specialityID";
    private static final String MY_CONTROLLER_COMMAND_GO_TO_MAIN_PAGE_REQUEST_INFO = "MyController?command=GO_TO_MAIN_PAGE&requestInfo=";
    private static final String EXCEPTION_REDIRECT = "MyController?command=GO_TO_ERROR_PAGE";
    private static final String MY_CONTROLLER_COMMAND_GO_TO_ERROR_PAGE = "MyController?command=GO_TO_ERROR_PAGE";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int adpplicantId = (int) request.getSession().getAttribute(USER_ID);
        int specialityID= (int) request.getSession().getAttribute(SPECIALITY_ID);
        System.out.println(specialityID);
        List<Request> list = new ArrayList<>();
        int finalScore=0;

        for (int i = 1; i < 6; i++) {
            if (request.getParameter(String.valueOf(i)) == null) {
                continue;
            }
            Request request1 = new Request();
            request1.setApplicantsID(adpplicantId);
            request1.setSubjectsID(i);
            request1.setScore(Integer.valueOf(request.getParameter(String.valueOf(i))));
            request1.setSpecialityID(specialityID);
            list.add(request1);
            finalScore+= request1.getScore();

        }
        Request request1 = new Request();
        request1.setApplicantsID(adpplicantId);
        request1.setSubjectsID(6);
        request1.setScore(finalScore);
        request1.setSpecialityID(specialityID);
        list.add(request1);

        ServiceFactory factory = ServiceFactory.getInstance();
        UserService userService = factory.getUserService();

        boolean correctSave = false;
        for (Request request2 : list) {
            try {
                correctSave = userService.sendApplicantUrequest(request2);
            } catch (ServiceException e) {
                response.sendRedirect(MY_CONTROLLER_COMMAND_GO_TO_ERROR_PAGE);
            }
        }

        response.sendRedirect(MY_CONTROLLER_COMMAND_GO_TO_MAIN_PAGE_REQUEST_INFO + correctSave);
    }
}
