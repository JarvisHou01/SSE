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
import org.springframework.util.DigestUtils;

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

        List<File> filesByopeRange = fileMapper.getFilesByopeRange(1, 1000, 3000);

        for (File file : filesByopeRange) {
            System.out.println(file);
        }
    }

}
