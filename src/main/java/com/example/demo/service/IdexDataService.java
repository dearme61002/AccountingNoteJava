package com.example.demo.service;

import com.example.demo.mapper.IndexDataMapper;
import com.example.demo.mod.IndexData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IdexDataService {
    @Autowired
    IndexDataMapper indexDataMapper;

    public IndexData getIndexData() {
        IndexData indexData=new IndexData();
        indexData.setFirstTime(indexDataMapper.getMaxTime());
        indexData.setLastTime(indexDataMapper.getMinTime());
        indexData.setNoteAmount(indexDataMapper.getCountNote());
        indexData.setNumberAmount(indexDataMapper.getCountuser());
        return  indexData;
    }
}
