package com.tc.webapp01.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Speciality implements Serializable {
    private Integer id;
    private String speciality;
    private Integer score;
    private Integer facultyID;
    private List<Subject> subjectList;
    private Property properties;
    private List<String> subjects;

    public Speciality() {
    }

    public List<Subject> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(List<Subject> subjectList) {
        this.subjectList = subjectList;
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }

    public Property getProperties() {
        return properties;
    }

    public void setProperties(Property properties) {
        this.properties = properties;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getFacultyID() {
        return facultyID;
    }

    public void setFacultyID(Integer facultyID) {
        this.facultyID = facultyID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Speciality that = (Speciality) o;
        return Objects.equals(id, that.id) && Objects.equals(speciality, that.speciality) && Objects.equals(score, that.score) && Objects.equals(facultyID, that.facultyID) && Objects.equals(subjectList, that.subjectList) && Objects.equals(properties, that.properties) && Objects.equals(subjects, that.subjects);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, speciality, score, facultyID, subjectList, properties, subjects);
    }
}
