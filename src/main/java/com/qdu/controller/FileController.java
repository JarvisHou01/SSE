package com.qdu.controller;

import com.qdu.pojo.File;
import com.qdu.service.FileService;
import com.qdu.service.KeywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@Controller
//@CrossOrigin
public class FileController {


    @Autowired
    private FileService fileService;

    @Autowired
    private KeywordService keywordService;


    @PostMapping("/upload")
    public String upload(// String name,
                         // String md5,
                         @RequestParam("file") MultipartFile file,
                         @RequestParam("index") MultipartFile index,
                         HttpSession session) throws IOException {

        String md5 = DigestUtils.md5DigestAsHex(file.getInputStream());
        String filename = file.getOriginalFilename();


        int uid = (int) session.getAttribute("uid");

        System.out.println("在Filecontroller中的upload方法----------------------------------");
        System.out.println("文件md5值:" + md5);
        System.out.println("文件名:" + filename);

        int fid = fileService.upload(filename, md5, file, uid);

        keywordService.addIndex(fid, index);

        return "redirect:/main.html";

    }


    @RequestMapping("/main.html")
    public String dashBoard(HttpSession session, Model model) {

        int uid = (int) session.getAttribute("uid");

        List<File> files = fileService.getAllByUid(uid);


        model.addAttribute("files", files);


        return "dashboard";
    }


    @RequestMapping("/delete")
    public String deleteFile(@RequestParam("fid") int fid) {

        fileService.deleteFileById(fid);


        keywordService.deleteByFid(fid);


        return "redirect:/main.html";

    }

    @RequestMapping("/search")
    public String search(@RequestParam("trapdoor") String trapdoor, Model model){

        System.out.println("进入了search");
        if (null==trapdoor||"".equals(trapdoor)){
            return "redirect:/main.html";
        }

        List<File> files = new ArrayList<>();

        String[] trapdoors = trapdoor.split(" ");

        // 单关键词
        if (trapdoors.length == 1){
            int[] search = keywordService.search(trapdoor);
            for (int i : search) {
                files.add(fileService.getById(i));
            }
        }else {// 多关键词

            int[] search1=keywordService.search(trapdoors[0]);
            int[] search2=keywordService.search(trapdoors[1]);

            int[] same = findSameElementIn2Arrays(search1, search2);

            for (int i = 2; i < trapdoors.length; i++) {
                int[] search = keywordService.search(trapdoors[i]);
                same = findSameElementIn2Arrays(same,search);
            }

            for (int i : same) {
                files.add(fileService.getById(i));
            }


        }



        model.addAttribute("searchfiles", files);
        return "forward:/main.html";



    }


    public static int[] findSameElementIn2Arrays(int[] array1,int[] array2) {

        Set<Integer> sameElementSet = new HashSet<Integer>();//用来存放两个数组中相同的元素
        Set<Integer> tempSet = new HashSet<Integer>();//用来存放数组1中的元素

        for(int i=0;i<array1.length;i++) {
            tempSet.add(array1[i]);//把数组1中的元素放到Set中，可以去除重复的元素
        }

        for(int j=0;j<array2.length;j++) {
            //把数组2中的元素添加到tempSet中
            //如果tempSet中已存在相同的元素，则tempSet.add(array2[j])返回false
            if(!tempSet.add(array2[j])) {
                //返回false,说明当前元素是两个数组中相同的元
                sameElementSet.add(array2[j]);
            }
        }

        Integer[] integers = sameElementSet.toArray(new Integer[sameElementSet.size()]);
        int[] same = Arrays.stream(integers).mapToInt(Integer::valueOf).toArray();

        return same;
    }



}
