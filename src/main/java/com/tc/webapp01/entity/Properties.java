package com.tc.webapp01.entity;

import javax.persistence.criteria.CriteriaBuilder;

public class Properties {
    private Integer idTable1;
    private Integer priferentPlacec;
    private Double cost;
    private Integer places;
    private Integer specialty_id;

    public Integer getPlaces() {
        return places;
    }

    public void setPlaces(Integer places) {
        this.places = places;
    }

    public Integer getSpecialty_id() {
        return specialty_id;
    }

    public void setSpecialty_id(Integer specialty_id) {
        this.specialty_id = specialty_id;
    }

    private Integer application_campaign_id;
    private Integer study_formats_study_format_id;

    public Integer getIdTable1() {
        return idTable1;
    }

    public void setIdTable1(Integer idTable1) {
        this.idTable1 = idTable1;
    }

    public Integer getPriferentPlacec() {
        return priferentPlacec;
    }

    public void setPriferentPlacec(Integer priferentPlacec) {
        this.priferentPlacec = priferentPlacec;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Integer getApplication_campaign_id() {
        return application_campaign_id;
    }

    public void setApplication_campaign_id(Integer application_campaign_id) {
        this.application_campaign_id = application_campaign_id;
    }

    public Integer getStudy_formats_study_format_id() {
        return study_formats_study_format_id;
    }

    public void setStudy_formats_study_format_id(Integer study_formats_study_format_id) {
        this.study_formats_study_format_id = study_formats_study_format_id;
    }
}
