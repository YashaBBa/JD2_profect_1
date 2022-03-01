package com.tc.webapp01.controller.impl;

import com.tc.webapp01.controller.Command;
import com.tc.webapp01.entity.Applicant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SortBestStudents implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Applicant> applicantList = new ArrayList<>();
        String applicants = request.getParameter("applicants");
        System.out.println(applicants);
        applicantList = (List<Applicant>) request.getAttribute("applicants");
        for (Applicant applicant : applicantList) {
            System.out.println(applicant.getName());
        }
    }
}
