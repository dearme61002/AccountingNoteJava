package com.example.demo.mod;


import lombok.Data;

import javax.annotation.PropertyKey;
import java.util.Date;
@Data
public class UserInfo {

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getAccount() {
        return Account;
    }

    public void setAccount(String account) {
        Account = account;
    }

    public String getPWD() {
        return PWD;
    }

    public void setPWD(String PWD) {
        this.PWD = PWD;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getUserLevel() {
        return UserLevel;
    }

    public void setUserLevel(String userLevel) {
        UserLevel = userLevel;
    }

    public Date getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(Date createDate) {
        CreateDate = createDate;
    }

    public Date getFixdate() {
        return Fixdate;
    }

    public void setFixdate(Date fixdate) {
        Fixdate = fixdate;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "ID='" + ID + '\'' +
                ", Account='" + Account + '\'' +
                ", PWD='" + PWD + '\'' +
                ", Name='" + Name + '\'' +
                ", Email='" + Email + '\'' +
                ", UserLevel='" + UserLevel + '\'' +
                ", CreateDate=" + CreateDate +
                ", Fixdate=" + Fixdate +
                ", onlyID=" + onlyID +
                '}';
    }

    public Integer getOnlyID() {
        return onlyID;
    }

    public void setOnlyID(Integer onlyID) {
        this.onlyID = onlyID;
    }

    private String ID;
    private String Account;
    private String PWD;
    private String Name;
    private String Email;
    private String UserLevel;
    private Date CreateDate;
    private Date Fixdate;
    private Integer onlyID;
}
