package com.tc.webapp01.entity;

public class Applicant {
    private Integer applicant_id;
    private String name;
    private String surname;
    private String passport;
    private String studyFormat;
    private Integer specialityPropId;
    private int applicantSpeciality;

    public Integer getApplicant_id() {
        return applicant_id;
    }

    public void setApplicant_id(Integer applicant_id) {
        this.applicant_id = applicant_id;
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
}
