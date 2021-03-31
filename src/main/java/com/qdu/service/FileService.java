package com.qdu.service;


import com.qdu.dao.FileMapper;
import com.qdu.pojo.File;
import com.qdu.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import static com.qdu.utils.FileUtils.generateFileName;


/**
 * 文件上传服务
 */

@Service
public class FileService {
    @Autowired
    private FileMapper fileMapper;


    /**
     * 上传文件 返回主键值
     *
     * @param md5
     * @param file
     */
    public int upload(String name,
                      String md5,
                      MultipartFile file,
                      int uid) throws IOException {

        System.out.println("在FileService中的upload方法-----------------------------------");
        String path = "D:\\uploadfile\\" + file.getOriginalFilename();
        System.out.println("文件存储的路径" + path);


        FileUtils.write(path, file.getInputStream());

        File fileToCommit = new File(name, md5, path, new Date(), uid);

        fileMapper.save(fileToCommit);

        return fileToCommit.getId();

    }


    public List<File> getAllByUid(int uid) {

        List<File> filesByUid = fileMapper.getFilesByUid(uid);
        return filesByUid;
    }


    public int deleteFileById(int fid) {
        int i = fileMapper.deleteById(fid);
        return i;
    }


    public File getById(int id){
        return fileMapper.getById(id);
    }


}