package com.tc.webapp01.entity;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Map;

public class Request {
    private Integer requestID;
    private Integer score;
    private Integer subjectsID;
    private Integer applicantsID;
    private String speciality;
    private Applicant applicant;
    private Speciality specialityObj;
    private Integer specialityID;
    private String subject;
    private Integer finalScore;
    public Integer getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(Integer finalScore) {
        this.finalScore = finalScore;
    }




    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Speciality getSpecialityObj() {
        return specialityObj;
    }

    public void setSpecialityObj(Speciality specialityObj) {
        this.specialityObj = specialityObj;
    }

    public Integer getSpecialityID() {
        return specialityID;
    }

    public void setSpecialityID(Integer specialityID) {
        this.specialityID = specialityID;
    }

    public Applicant getApplicant() {
        return applicant;
    }

    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
    }


    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public Map<Integer, Integer> getSubIdAndScore() {
        return subIdAndScore;
    }

    public void setSubIdAndScore(Map<Integer, Integer> subIdAndScore) {
        this.subIdAndScore = subIdAndScore;
    }

    private Map<Integer,Integer> subIdAndScore;

    public Integer getRequestID() {
        return requestID;
    }

    public void setRequestID(Integer requestID) {
        this.requestID = requestID;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getSubjectsID() {
        return subjectsID;
    }

    public void setSubjectsID(Integer subjectsID) {
        this.subjectsID = subjectsID;
    }

    public Integer getApplicantsID() {
        return applicantsID;
    }

    public void setApplicantsID(Integer applicantsID) {
        this.applicantsID = applicantsID;
    }

    @Override
    public String toString() {
        return "Request{" +
                "requestID=" + requestID +
                ", score=" + score +
                ", subjectsID=" + subjectsID +
                ", applicantsID=" + applicantsID +
                ", speciality='" + speciality + '\'' +
                ", applicant=" + applicant +
                ", specialityObj=" + specialityObj +
                ", specialityID=" + specialityID +
                ", subIdAndScore=" + subIdAndScore +
                '}';
    }
}
