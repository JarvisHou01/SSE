package com.qdu.service;

import com.qdu.dao.KeywordMapper;
import com.qdu.pojo.Keyword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class KeywordService {
    @Autowired
    KeywordMapper keywordMapper;


    public void addIndex(int fid, MultipartFile indexFile) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(indexFile.getInputStream()));

        while (reader.ready()){
            keywordMapper.add(new Keyword(reader.readLine(),fid));
        }

    }


    public int deleteByFid(int fid){

        return keywordMapper.deleteByFid(fid);

    }


    public int[] search(String trapdoor){
        return keywordMapper.search(trapdoor);
    }
}
