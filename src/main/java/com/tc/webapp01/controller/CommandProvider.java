package com.tc.webapp01.controller;

import java.util.HashMap;
import java.util.Map;

import com.tc.webapp01.controller.impl.*;

public final class CommandProvider {
    private final Map<String, Command> commands = new HashMap<String, Command>();

    public CommandProvider() {
        commands.put("logination", new LoginationCommand());
        commands.put("registration", new RegistraionCommand());
        commands.put("saveApplicantData", new SaveApplicantsDataCommand());
        commands.put("showFacultyInfo", new GoToFacultyInfo());
        commands.put("sendRequest", new RequestCommand());
        commands.put("exit", new ExitCommand());
        commands.put("saveNewSpeciality", new SaveSpeciality());

        commands.put("GO_TO_SAVE_NEW_SPECIALITY_PAGE", new GoToCreatingSpecialityPage());
        commands.put("REJECT_REQUEST", new RejectRequest());
        commands.put("APPLAY_REQUEST", new ApplayRequest());
        commands.put("GO_TO_REQUEST_LIST_PAGE", new GoToRequestListPage());
        commands.put("GO_TO_REQUEST_PAGE", new GoToRequestPage());
        commands.put("GO_TO_REGISTRATION_PAGE", new GoToregistrationPageCommand());
        commands.put("GO_TO_INDEX_PAGE", new GoToIndexPage());
        commands.put("GO_TO_MAIN_PAGE", new GoToMainPage());

        commands.put("GO_TO_APPLICANTSDATA_PAGE", new GoToApplicantsPage());

    }

    public final Command getCommand(String commandName) {
        Command command = commands.get(commandName);
        return command;
    }


}
