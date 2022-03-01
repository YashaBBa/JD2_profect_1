package com.tc.webapp01.controller.impl;


import com.tc.webapp01.controller.Command;
import com.tc.webapp01.controller.locale.LocaleManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class ChangeLocaleCommand implements Command {
    private static final String COMMAND = "command";
    private static final String LOCALE = "locale";
    private static final String LOCALE_RU = "ru";
    private static final String LOCALE_EN = "en";
    private static final String MY_CONTROLLER_COMMAND_GO_TO_INDEX_PAGE = "MyController?command=GO_TO_INDEX_PAGE&";


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String locale = request.getParameter(COMMAND);
        LocaleManager localeManager = LocaleManager.getInstance();

        if (locale.equals(LOCALE_RU)) {
            ResourceBundle resourceBundleRU = localeManager.getResourceBundleRU();
            Locale localeRU = resourceBundleRU.getLocale();
            request.getSession().setAttribute(LOCALE, localeRU);
        }
        if (locale.equals(LOCALE_EN)) {
            ResourceBundle resourceBundleEN = localeManager.getResourceBundleEN();
            Locale localeEN = resourceBundleEN.getLocale();
            request.getSession().setAttribute(LOCALE, localeEN);
        }
        String url= (String) request.getSession().getAttribute("url");


       // RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        //dispatcher.forward(request, response);
        response.sendRedirect(url);
    }
}
