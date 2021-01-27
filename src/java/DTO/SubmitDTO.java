/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author handez
 */
public class SubmitDTO {
    private String submitid;
    private String subjectid;
    private String time_begin;
    private String time_end;
    private String email;
    private float point;
    private float point_total;
    private float point_current;

    public SubmitDTO() {
    }

    public SubmitDTO(String submitid, String subjectid, String time_begin, String time_end, String email, float point, float point_total, float point_current) {
        this.submitid = submitid;
        this.subjectid = subjectid;
        this.time_begin = time_begin;
        this.time_end = time_end;
        this.email = email;
        this.point = point;
        this.point_total = point_total;
        this.point_current = point_current;
    }

    public String getSubmitid() {
        return submitid;
    }

    public void setSubmitid(String submitid) {
        this.submitid = submitid;
    }

    public String getSubjectid() {
        return subjectid;
    }

    public void setSubjectid(String subjectid) {
        this.subjectid = subjectid;
    }

    public String getTime_begin() {
        return time_begin;
    }

    public void setTime_begin(String time_begin) {
        this.time_begin = time_begin;
    }

    public String getTime_end() {
        return time_end;
    }

    public void setTime_end(String time_end) {
        this.time_end = time_end;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public float getPoint() {
        return point;
    }

    public void setPoint(float point) {
        this.point = point;
    }

    public float getPoint_total() {
        return point_total;
    }

    public void setPoint_total(float point_total) {
        this.point_total = point_total;
    }

    public float getPoint_current() {
        return point_current;
    }

    public void setPoint_current(float point_current) {
        this.point_current = point_current;
    }
    
    
    
}
