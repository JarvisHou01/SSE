package com.qdu.controller;

import com.qdu.pojo.File;
import com.qdu.service.FileService;
import com.qdu.service.KeywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;

import static com.qdu.utils.FileUtils.findSameElementIn2Arrays;

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
                         @RequestParam("ope") int ope,
                         HttpSession session) throws IOException {

        String md5 = DigestUtils.md5DigestAsHex(file.getInputStream());
        String filename = file.getOriginalFilename();


        int uid = (int) session.getAttribute("uid");

        System.out.println("在Filecontroller中的upload方法----------------------------------");
        System.out.println("文件md5值:" + md5);
        System.out.println("文件名:" + filename);

        int fid = fileService.saveFile(filename, md5, ope, file, uid);

        keywordService.addIndex(fid, index);

//        return "redirect:/main.html";
        return "component/upload";

    }


//    @RequestMapping(value = {"/main.html", "/"})
//    public String dashBoard(HttpSession session, Model model) {
//
//        int uid = (int) session.getAttribute("uid");
//
//        List<File> files = fileService.getAllByUid(uid);
//
//
//        model.addAttribute("files", files);
//
//
//        return "dashboard";
//    }


    @RequestMapping("/delete")
    public String deleteFile(@RequestParam("fid") int fid,@RequestParam("url") String url ) {

        String[] split = url.split("/");
        String comingPath = split[split.length - 1];

        fileService.deleteFileById(fid);

        keywordService.deleteByFid(fid);

        return "component/"+comingPath;

//        return "redirect:/main.html";

    }

    @RequestMapping("/download")
    @ResponseBody
    public String downloadFile(@RequestParam("fid") int fid, HttpServletResponse response) throws UnsupportedEncodingException {

        File download = fileService.download(fid);

        java.io.File file = new java.io.File(download.getPath());

        if (file.exists()) {
            // 配置文件下载
            response.setHeader("content-type", "application/octet-stream");
            response.setContentType("application/octet-stream");
            // 下载文件能正常显示中文
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(download.getName(), "UTF-8"));
            // 实现文件下载
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            OutputStream os = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
                return "successfully";
            } catch (Exception e) {
                return "failed";

            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (os != null) {
                    try {
                        os.flush();
                        os.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }

        }

        return "";

    }

    @PostMapping("/search")
    public String search(@RequestParam("trapdoor") String trapdoor, @RequestParam(value = "phraseCheckBox", required = false) String checkbox, Model model,HttpSession session) {

        int uid = (int) session.getAttribute("uid");

        if (null == trapdoor || "".equals(trapdoor)) {
            return "redirect:/main.html";
        }

        List<File> files = new ArrayList<>();

        String[] trapdoors = trapdoor.split(" ");

        if (checkbox == null) {
            // 单关键词
            if (trapdoors.length == 1) {
                int[] search = keywordService.search(trapdoor);
                for (int i : search) {
                    File file = fileService.getById(i);
                    if (file.getUid() == uid) {
                        files.add(file);
                    }
                }
            } else {
                // 多关键词
                int[] search1 = keywordService.search(trapdoors[0]);
                int[] search2 = keywordService.search(trapdoors[1]);

                int[] same = findSameElementIn2Arrays(search1, search2);

                for (int i = 2; i < trapdoors.length; i++) {
                    int[] search = keywordService.search(trapdoors[i]);
                    same = findSameElementIn2Arrays(same, search);
                }

                for (int i : same) {
                    File file = fileService.getById(i);
                    if (file.getUid() == uid) {
                        files.add(file);
                    }
                }

            }
        }else {
            // 词组搜索

            int[] phraseSearch = keywordService.phraseSearch(trapdoors);

            for (int i : phraseSearch) {
                File file = fileService.getById(i);
                if (file.getUid() == uid) {
                    files.add(file);
                }

            }
            
        }


        HashSet<File> files1 = new HashSet<>(files);


        model.addAttribute("searchfiles", files1);


//        return "forward:/main.html";
        return "component/search";


    }


    @PostMapping("/opesearch")
    public String opesearch(@RequestParam("startpoint") int startpoint, @RequestParam("endpoint") int endpoint, Model model, HttpSession session) {

        System.out.println("opesearch");

        int uid = (int) session.getAttribute("uid");

        List<File> files = fileService.getFileByope(uid, startpoint, endpoint);


        model.addAttribute("opesearchfiles", files);

//        return "forward:/main.html";
        return "component/opesearch";


    }




}
