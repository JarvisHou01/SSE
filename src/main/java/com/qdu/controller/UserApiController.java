package com.qdu.controller;

import com.qdu.pojo.User;
import com.qdu.result.Result;
import com.qdu.result.ResultFactory;
import com.qdu.service.UserServiceImpl;
import com.qdu.utils.JWTUtil;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;

@RestController
@RequestMapping("/api")
public class UserApiController {

    @Resource
    UserServiceImpl userService;

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public Result login(@RequestBody User user) {

        System.out.println(user.getUsername());
        User user_query = new User(0, user.getUsername(), DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        User login = userService.login(user_query);
        if (login!=null){
            HashMap<Object, Object> data = new HashMap<>();
            data.put("uid",login.getId());
            data.put("username",login.getUsername());
            data.put("token", JWTUtil.sign(user));

            return ResultFactory.buildSuccessResult(data,"登录成功");
        }else {
            return ResultFactory.buildFailResult("登录失败");
        }
    }
}
