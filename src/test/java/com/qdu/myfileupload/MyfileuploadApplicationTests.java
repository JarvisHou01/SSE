package com.qdu.myfileupload;

import com.qdu.dao.FileMapper;
import com.qdu.dao.KeywordMapper;
import com.qdu.dao.UserMapper;
import com.qdu.pojo.File;
import com.qdu.pojo.Keyword;
import com.qdu.pojo.User;
import com.qdu.result.Result;
import com.qdu.result.ResultFactory;
import com.qdu.service.KeywordService;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
class MyfileuploadApplicationTests {

    @Autowired
    FileMapper fileMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    KeywordMapper keywordMapper;

    @Autowired
    KeywordService keywordService;




    @Test
    void contextLoads() throws SQLException, IOException {

        HashMap<Object, Object> data = new HashMap<>();
        data.put("id", "用户的id");
        data.put("name","用户的name");
        //生成token返回给客户端
        data.put("token","JWT产生的token");

        Result result = ResultFactory.buildSuccessResult(data, "测试成功");


        System.out.println(result);

    }

}
