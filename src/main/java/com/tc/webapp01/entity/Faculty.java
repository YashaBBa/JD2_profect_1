package com.tc.webapp01.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Faculty implements Serializable {
    private Integer id;
    private String faculty;
    private List<Speciality> specialitiesList;

    public Faculty() {
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Faculty faculty1 = (Faculty) o;
        return Objects.equals(id, faculty1.id) && Objects.equals(faculty, faculty1.faculty) && Objects.equals(specialitiesList, faculty1.specialitiesList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, faculty, specialitiesList);
    }
}
