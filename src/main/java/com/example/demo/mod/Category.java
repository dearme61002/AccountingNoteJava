package com.example.demo.mod;

import lombok.Data;

@Data
public class Category {
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

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public String getBody() {
        return Body;
    }

    public void setBody(String body) {
        Body = body;
    }

    public String getCountAccountingNote() {
        return CountAccountingNote;
    }

    public void setCountAccountingNote(String countAccountingNote) {
        CountAccountingNote = countAccountingNote;
    }

    private String ID;
    private String UserID;
    private String Title;
    private String CreateTime;
    private String Body;
    private String CountAccountingNote;
}
