package com.example.demo.mod;

import lombok.Data;

@Data
public class IndexData {

private String firstTime;

    public String getFirstTime() {
        return firstTime;
    }

    public void setFirstTime(String firstTime) {
        this.firstTime = firstTime;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }

    public String getNoteAmount() {
        return noteAmount;
    }

    public void setNoteAmount(String noteAmount) {
        this.noteAmount = noteAmount;
    }

    public String getNumberAmount() {
        return numberAmount;
    }

    public void setNumberAmount(String numberAmount) {
        this.numberAmount = numberAmount;
    }

    private String lastTime;
    private String noteAmount;
    private String numberAmount;

}
