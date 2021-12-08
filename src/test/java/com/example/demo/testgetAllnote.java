package com.example.demo;

import com.example.demo.mapper.AccountingNoteMapper;
import com.example.demo.mod.AccountingNote;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class testgetAllnote {
    @Autowired
    private AccountingNoteMapper accountingNoteMapper;
    @Test
    void contextLoads() {
        List<AccountingNote> accountingNote = accountingNoteMapper.getAllAccountingNote();
        System.out.println(accountingNote);

    }
}
