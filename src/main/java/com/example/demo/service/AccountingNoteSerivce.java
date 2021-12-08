package com.example.demo.service;

import com.example.demo.mapper.AccountingNoteMapper;
import com.example.demo.mod.AccountingNote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountingNoteSerivce {
    @Autowired
    AccountingNoteMapper accountingNoteMapper;

    public List<AccountingNote> getALLAccountingNote(){
        List<AccountingNote> allAccountingNote = accountingNoteMapper.getAllAccountingNote();
        return allAccountingNote;
    }

    public int insertAccountingNote(String UserID,String Caption,int Amount,String ActType,String Body,String Category){
        int i = accountingNoteMapper.insertAccountingNote(UserID, Caption, Amount, ActType, Body, Category);
        return i;
    }

    public List<AccountingNote> getOneAccountingNoteByUserID(String UserID){
        List<AccountingNote> oneAccountingNoteByUserID = accountingNoteMapper.getOneAccountingNoteByUserID(UserID);
        return oneAccountingNoteByUserID;
    }

    public Integer getSumINAmount(String UserID){
        Integer sumAmount = accountingNoteMapper.getSumAmount(UserID, "0");
        return sumAmount;

    }
    public Integer getSumOutAmount(String UserID){
        Integer sumAmount = accountingNoteMapper.getSumAmount(UserID, "1");
        return sumAmount;

    }

    public Integer updatemany(String UserID,String oldCategory,String newCategory){
        Integer updatemany = accountingNoteMapper.updatemany(UserID, oldCategory,newCategory);
        return updatemany;
    }

    public Integer deleteAccountingNote(String UserID,String Category){
        Integer integer = accountingNoteMapper.deleteAccountingNote(UserID, Category);
        return integer;
    }

    public AccountingNote getOneAccountingNoteByID(String ID){
        AccountingNote oneAccountingNoteByID = accountingNoteMapper.getOneAccountingNoteByID(ID);
        return  oneAccountingNoteByID;
    }
    public Integer getOneMaxAccountingNoteID(){
        Integer oneMaxAccountingNoteID = accountingNoteMapper.getOneMaxAccountingNoteID();
        return oneMaxAccountingNoteID;
    }

    public Integer updateOneAccountingNoteID(String Caption,Integer Amount,String ActType,String Body,String Category,String ID){
        Integer integer = accountingNoteMapper.updateOneAccountingNoteID(Caption, Amount, ActType, Body, Category, ID);
        return integer;
    }

    public Integer deleteAccountingNoteByID(String ID){
        Integer integer = accountingNoteMapper.deleteAccountingNoteByID(ID);
        return integer;
    }

    public Integer deleteAccountingNoteByUserID(String UserID){
        Integer integer = accountingNoteMapper.deleteAccountingNoteByUserID(UserID);
        return integer;
    }
}
