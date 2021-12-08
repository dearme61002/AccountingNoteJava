package com.example.demo.mod;

import lombok.Data;

import java.util.Date;

@Data
public class AccountingNote {


    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getCaption() {
        return Caption;
    }

    public void setCaption(String caption) {
        Caption = caption;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }

    public String getActType() {
        return ActType;
    }

    public void setActType(String actType) {
        ActType = actType;
    }

    public Date getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(Date createDate) {
        CreateDate = createDate;
    }

    public String getBody() {
        return Body;
    }

    public void setBody(String body) {
        Body = body;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    private String ID;
    private String UserID;
    private String Caption;
    private int Amount;
    private String ActType;
    private Date CreateDate;
    private String Body;
    private String Category;


}
