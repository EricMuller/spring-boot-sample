package com.emu.apps.qcm.model;

import javax.persistence.*;
import java.util.Date;


@Table(name = "profile")
@Entity
public class Profile  extends BasicEntity {
    @Column(name = "FULLNAME")
    private String fullName;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "PRINCIPAL_ID")
    private String principalId;
    @Column(name = "PHOTO")
    private String photo;
    @Column(name = "CREATED")
    private Date created;
    @Column(name = "LAST_LOGIN")
    private Date lastLogin;
    @Column(name = "LOGIN_TYPE")
    //@Enumerated(EnumType.STRING)
    private String loginType;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPrincipalId() {
        return principalId;
    }

    public void setPrincipalId(String principalId) {
        this.principalId = principalId;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }
}