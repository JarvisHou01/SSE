package com.qdu.controller;


import com.qdu.pojo.File;
import com.qdu.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
public class ViewController {

    @Autowired
    private FileService fileService;

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
        List<File> files = fileService.getAllByUid(uid);


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
