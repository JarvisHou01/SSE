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
public class FileServiceImpl implements FileService {
    @Autowired
    private FileMapper fileMapper;


    /**
     * 上传文件 返回主键值
     *
     * @param md5
     * @param file
     */
    public int saveFile(String name,
                        String md5,
                        int ope,
                        MultipartFile file,
                        int uid) {

        String path = "D:\\uploadfile\\" + file.getOriginalFilename();
        try {
            file.transferTo(new java.io.File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        File fileToCommit = new File();
        fileToCommit.setName(name);
        fileToCommit.setMd5(md5);
        fileToCommit.setPath(path);
        fileToCommit.setOpe(ope);
        fileToCommit.setUploadTime(new Date());
        fileToCommit.setUid(uid);
        fileMapper.save(fileToCommit);
        return fileToCommit.getId();
    }

    public File download(int fid) {
        return fileMapper.getById(fid);

    }


    public List<File> getAllByUid(int uid) {

        List<File> filesByUid = fileMapper.getFilesByUid(uid);
        return filesByUid;
    }


    public int deleteFileById(int fid) {
        int i = fileMapper.deleteById(fid);
        return i;
    }


    public File getById(int id) {
        return fileMapper.getById(id);
    }

    public List<File> getFileByope(int uid,int start,int end){

        return fileMapper.getFilesByopeRange(uid, start, end);

    }




}
