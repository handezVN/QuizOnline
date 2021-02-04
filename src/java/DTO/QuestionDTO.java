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
public class QuestionDTO {
    private String id ;
    private String question ;
    private String answer ;
    private String correct ;
    private String subjectid ;
    private int point ;
    private boolean status;
    private boolean isdelete;

    public boolean isIsdelete() {
        return isdelete;
    }

    public void setIsdelete(boolean isdelete) {
        this.isdelete = isdelete;
    }

    public QuestionDTO(String id, String question, String answer, String correct, String subjectid, int point, boolean status, boolean isdelete) {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.correct = correct;
        this.subjectid = subjectid;
        this.point = point;
        this.status = status;
        this.isdelete = isdelete;
    }
    public QuestionDTO() {
    }

   

    public QuestionDTO(String id, String question, String answer, String correct, String subjectid, int point, boolean status) {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.correct = correct;
        this.subjectid = subjectid;
        this.point = point;
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getCorrect() {
        return correct;
    }

    public void setCorrect(String correct) {
        this.correct = correct;
    }

    public String getSubjectid() {
        return subjectid;
    }

    public void setSubjectid(String subjectid) {
        this.subjectid = subjectid;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
    
}
