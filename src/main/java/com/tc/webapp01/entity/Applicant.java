package com.tc.webapp01.entity;

import java.io.Serializable;
import java.util.Objects;

public class Applicant implements Serializable {
    private Integer applicantId;
    private String name;
    private String surname;
    private String passport;
    private String studyFormat;
    private Integer specialityPropId;
    private int applicantSpeciality;
    private String privileges;

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    private Integer priority;


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    private int score;

    public String getPrivileges() {
        return privileges;
    }

    public void setPrivileges(String privileges) {
        this.privileges = privileges;
    }


    public Applicant() {
    }

    public Integer getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(Integer applicantId) {
        this.applicantId = applicantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getStudyFormat() {
        return studyFormat;
    }

    public void setStudyFormat(String studyFormat) {
        this.studyFormat = studyFormat;
    }

    public Integer getSpecialityPropId() {
        return specialityPropId;
    }

    public void setSpecialityPropId(Integer specialityPropId) {
        this.specialityPropId = specialityPropId;
    }


    public void setApplicantSpeciality(int applicantSpeciality) {
        this.applicantSpeciality = applicantSpeciality;
    }

    public int getApplicantSpeciality() {
        return applicantSpeciality;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Applicant applicant = (Applicant) o;
        return applicantSpeciality == applicant.applicantSpeciality && Objects.equals(applicantId, applicant.applicantId) && Objects.equals(name, applicant.name) && Objects.equals(surname, applicant.surname) && Objects.equals(passport, applicant.passport) && Objects.equals(studyFormat, applicant.studyFormat) && Objects.equals(specialityPropId, applicant.specialityPropId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(applicantId, name, surname, passport, studyFormat, specialityPropId, applicantSpeciality);
    }
}
