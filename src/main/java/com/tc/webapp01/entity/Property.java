package com.tc.webapp01.entity;

import java.io.Serializable;
import java.util.Objects;

public class Property implements Serializable {
    private Integer idTable;
    private Integer priferentPlacec;
    private Double cost;
    private Integer places;
    private Integer specialtyId;

    public Property() {
    }

    public Integer getPlaces() {
        return places;
    }

    public void setPlaces(Integer places) {
        this.places = places;
    }

    public Integer getSpecialty_id() {
        return specialtyId;
    }

    public void setSpecialty_id(Integer specialty_id) {
        this.specialtyId = specialty_id;
    }

    private Integer application_campaign_id;
    private Integer study_formats_study_format_id;

    public Integer getIdTable() {
        return idTable;
    }

    public void setIdTable(Integer idTable) {
        this.idTable = idTable;
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



    public void setApplication_campaign_id(Integer application_campaign_id) {
        this.application_campaign_id = application_campaign_id;
    }



    public void setStudy_formats_study_format_id(Integer study_formats_study_format_id) {
        this.study_formats_study_format_id = study_formats_study_format_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Property that = (Property) o;
        return Objects.equals(idTable, that.idTable) && Objects.equals(priferentPlacec, that.priferentPlacec) && Objects.equals(cost, that.cost) && Objects.equals(places, that.places) && Objects.equals(specialtyId, that.specialtyId) && Objects.equals(application_campaign_id, that.application_campaign_id) && Objects.equals(study_formats_study_format_id, that.study_formats_study_format_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTable, priferentPlacec, cost, places, specialtyId, application_campaign_id, study_formats_study_format_id);
    }
}
