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
public class DetailSubmitDTO {
    private String submitid;
    private String answer_selected;
    private String answer_correct;
    private String question_content;
    private String answer_content;
    private float point_get;
    private float point;

    public DetailSubmitDTO(String submitid, String answer_selected, String answer_correct, String question_content, String answer_content, float point_get, float point) {
        this.submitid = submitid;
        this.answer_selected = answer_selected;
        this.answer_correct = answer_correct;
        this.question_content = question_content;
        this.answer_content = answer_content;
        this.point_get = point_get;
        this.point = point;
    }

    public String getSubmitid() {
        return submitid;
    }

    public void setSubmitid(String submitid) {
        this.submitid = submitid;
    }

    public String getAnswer_selected() {
        return answer_selected;
    }

    public void setAnswer_selected(String answer_selected) {
        this.answer_selected = answer_selected;
    }

    public String getAnswer_correct() {
        return answer_correct;
    }

    public void setAnswer_correct(String answer_correct) {
        this.answer_correct = answer_correct;
    }

    public String getQuestion_content() {
        return question_content;
    }

    public void setQuestion_content(String question_content) {
        this.question_content = question_content;
    }

    public String getAnswer_content() {
        return answer_content;
    }

    public void setAnswer_content(String answer_content) {
        this.answer_content = answer_content;
    }

    public float getPoint_get() {
        return point_get;
    }

    public void setPoint_get(float point_get) {
        this.point_get = point_get;
    }

    public float getPoint() {
        return point;
    }

    public void setPoint(float point) {
        this.point = point;
    }
    
    
}
