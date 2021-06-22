package com.qdu.controller;


import com.qdu.pojo.File;
import com.qdu.service.FileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ViewController {

    @Autowired
    private FileServiceImpl fileServiceImpl;

    @GetMapping("/upload")
    public String upload() {

        return "component/upload";

    }

    @GetMapping("/emps")
    public String list() {

        return "component/emp";
    }

    @GetMapping("/myfile")
    public String myfile(HttpSession session, Model model) {

        int uid = (int) session.getAttribute("uid");
        List<File> files = fileServiceImpl.getAllByUid(uid);


        model.addAttribute("files", files);

        return "component/myfile";
    }


    @GetMapping("/search")
    public String search() {

        return "component/search";
    }

    @GetMapping("/opesearch")
    public String opesearch() {

        return "component/opesearch";
    }




}
