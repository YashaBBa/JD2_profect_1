package com.tc.webapp01.service;

import com.tc.webapp01.service.impl.*;

public final class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    private final UserService userService = new UserServiceImpl();

    private final FacultyService facultyService = new FacultyServiceImpl();



    private final SpecialitiesService specialitiesService = new SpecialitiesServiceImpl();

    private final AdminService adminService = new AdminServiceImpl();


    public ServiceFactory() {
    }

    public UserService getUserService() {
        return userService;
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public FacultyService getFacultyService() {
        return facultyService;
    }



    public SpecialitiesService getSpecialitiesService() {
        return specialitiesService;
    }

    public AdminService getAdminService() {
        return adminService;
    }
}
