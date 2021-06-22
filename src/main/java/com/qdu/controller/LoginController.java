package com.qdu.controller;

import com.qdu.pojo.User;
import com.qdu.result.Result;
import com.qdu.result.ResultFactory;
import com.qdu.service.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
public class LoginController {


    @Resource
    private UserServiceImpl userService;



    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        RedirectAttributes attributes,
                        HttpSession session){


        User user = new User(username, DigestUtils.md5DigestAsHex(password.getBytes()));

        System.out.println("提交来的用户"+user);

        User login = userService.login(user);
        System.out.println("查询到的用户"+login);

        if (login!= null){

            session.setAttribute("loginuser",username);
            session.setAttribute("uid",login.getId());

            return "redirect:/main.html";
//            return "redirect:/";
//            return "dashboard2k";
//            return "redirect:/dashboard";
        }else {
            attributes.addFlashAttribute("errorMsg","用户名或者密码错误");

            return "redirect:/index";
        }

    }






    @RequestMapping("/user/register")
    public String register(@RequestParam("userName") String username,
                           @RequestParam("password") String password,
                           HttpSession session){

        User newuser = new User(username, DigestUtils.md5DigestAsHex(password.getBytes()));
        int add = userService.add(newuser);

        if (add==1){
            System.out.println("建立了新的用户" + newuser);
        }
        return "redirect:/index";

    }






    @RequestMapping("/user/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/index";
    }
}
