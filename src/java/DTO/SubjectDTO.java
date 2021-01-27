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
public class SubjectDTO {
    private String id ; 
    private String name;
    private String email;
    private String password;
    private String date;
    private int time;
    private int attempts;

    public SubjectDTO(String id, String name, String email, String password, String date, int time, int attempts) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.date = date;
        this.time = time;
        this.attempts = attempts;
    }

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public SubjectDTO(String id, String name, String email, String password, String date, int time) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.date = date;
        this.time = time;
    }

    public SubjectDTO() {
    }
}
