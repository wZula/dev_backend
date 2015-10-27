/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourgoat.users.models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.annotation.Id;

/**
 *
 * @author fitsum
 */
@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @javax.persistence.Id
    private Long id;
    private String userId;
    private String email;
    private String password;
    private String picture;
    private String fullName;
    private String firstName;
    private String lastName;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateOfBirth;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date createdDate;
    private boolean isAccountActive;
    private boolean isEmailVerification; 
    private String facebook;
    private String google;
    private String gender;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getGoogle() {
        return google;
    }

    public void setGoogle(String google) {
        this.google = google;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getPicture() {
        return picture;
    }

    public enum Provider {

        FACEBOOK("facebook"), GOOGLE("google");

        String name;

        Provider(final String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        public String capitalize() {
            return StringUtils.capitalize(this.name);
        }
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


    

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public boolean isIsAccountActive() {
        return isAccountActive;
    }

    public void setIsAccountActive(boolean isAccountActive) {
        this.isAccountActive = isAccountActive;
    }



    public boolean isIsEmailVerification() {
        return isEmailVerification;
    }

    public void setIsEmailVerification(boolean isEmailVerification) {
        this.isEmailVerification = isEmailVerification;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    
    @Override
    public String toString() {
        return "User{" + "id=" + id + ", userId=" + userId + ", email=" + email + ", password=" + password + ", picture=" + picture + ", fullname=" + fullName + ", firstName=" + firstName + ", lastName=" + lastName + ", dateOfBirth=" + dateOfBirth + ", createdDate=" + createdDate + ", isAccountActive=" + isAccountActive + ", isEmailVerification=" + isEmailVerification + ", facebook=" + facebook + ", google=" + google + '}';
    }


    

}
