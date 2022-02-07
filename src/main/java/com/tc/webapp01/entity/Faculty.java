package com.tc.webapp01.entity;

import java.util.List;

public class Faculty {
    private Integer id;
    private String faculty;
    private List<Speciality> specialitiesList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public List<Speciality> getSpecialitiesList() {
        return specialitiesList;
    }

    public void setSpecialitiesList(List<Speciality> specialitiesList) {
        this.specialitiesList = specialitiesList;
    }
}
