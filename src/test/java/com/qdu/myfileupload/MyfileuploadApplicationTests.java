package com.qdu.myfileupload;

import com.qdu.dao.FileMapper;
import com.qdu.dao.KeywordMapper;
import com.qdu.dao.UserMapper;
import com.qdu.pojo.File;
import com.qdu.pojo.Keyword;
import com.qdu.pojo.User;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@SpringBootTest
class MyfileuploadApplicationTests {

    @Autowired
    FileMapper fileMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    KeywordMapper keywordMapper;




    @Test
    void contextLoads() throws SQLException, IOException {
        File file1 = new File();
        file1.setId(9);

        File file = fileMapper.getByFile(file1);

        System.out.println(file);
    }

}
