package com.tc.webapp01.entity;

import java.io.Serializable;
import java.util.Objects;

public class Subject implements Serializable {
    private String subject;
    private Integer subjectID;
    private Integer score;

    public Subject() {
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Integer getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(Integer subjectID) {
        this.subjectID = subjectID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject1 = (Subject) o;
        return Objects.equals(subject, subject1.subject) && Objects.equals(subjectID, subject1.subjectID) && Objects.equals(score, subject1.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subject, subjectID, score);
    }
}
