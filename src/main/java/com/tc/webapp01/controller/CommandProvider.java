package com.tc.webapp01.controller;

import java.util.HashMap;
import java.util.Map;

import com.tc.webapp01.controller.impl.*;

public final class CommandProvider {
    public static final String LOGINATION = "logination";
    public static final String REGISTRATION = "registration";
    public static final String SAVE_APPLICANT_DATA = "saveApplicantData";
    public static final String SHOW_FACULTY_INFO = "showFacultyInfo";
    public static final String SEND_REQUEST = "sendRequest";
    public static final String EXIT = "exit";
    public static final String SAVE_NEW_SPECIALITY = "saveNewSpeciality";
    public static final String GO_TO_SAVE_NEW_SPECIALITY_PAGE = "GO_TO_SAVE_NEW_SPECIALITY_PAGE";
    public static final String REJECT_REQUEST = "REJECT_REQUEST";
    public static final String APPLAY_REQUEST = "APPLAY_REQUEST";
    public static final String GO_TO_REQUEST_LIST_PAGE = "GO_TO_REQUEST_LIST_PAGE";
    public static final String GO_TO_REQUEST_PAGE = "GO_TO_REQUEST_PAGE";
    public static final String GO_TO_REGISTRATION_PAGE = "GO_TO_REGISTRATION_PAGE";
    public static final String GO_TO_INDEX_PAGE = "GO_TO_INDEX_PAGE";
    public static final String GO_TO_MAIN_PAGE = "GO_TO_MAIN_PAGE";
    private final Map<String, Command> commands = new HashMap<String, Command>();

    public CommandProvider() {
        commands.put(LOGINATION, new LoginationCommand());
        commands.put(REGISTRATION, new RegistraionCommand());
        commands.put(SAVE_APPLICANT_DATA, new SaveApplicantsDataCommand());
        commands.put(SHOW_FACULTY_INFO, new GoToFacultyInfo());
        commands.put(SEND_REQUEST, new RequestCommand());
        commands.put(EXIT, new ExitCommand());
        commands.put(SAVE_NEW_SPECIALITY, new SaveSpeciality());

        commands.put(GO_TO_SAVE_NEW_SPECIALITY_PAGE, new GoToCreatingSpecialityPage());
        commands.put(REJECT_REQUEST, new RejectRequest());
        commands.put(APPLAY_REQUEST, new ApplayRequest());
        commands.put(GO_TO_REQUEST_LIST_PAGE, new GoToRequestListPage());
        commands.put(GO_TO_REQUEST_PAGE, new GoToRequestPage());
        commands.put(GO_TO_REGISTRATION_PAGE, new GoToregistrationPageCommand());
        commands.put(GO_TO_INDEX_PAGE, new GoToIndexPage());
        commands.put(GO_TO_MAIN_PAGE, new GoToMainPage());

        commands.put("GO_TO_APPLICANTSDATA_PAGE", new GoToApplicantsPage());

    }

    public final Command getCommand(String commandName) {
        Command command = commands.get(commandName);
        return command;
    }


}
