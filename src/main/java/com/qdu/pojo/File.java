package com.qdu.pojo;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * File表存储上传的文件信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class File implements Serializable {

    private int id;
    private String name;
    private String md5;
    private String path;
    private Date uploadTime;
    private int uid;



    public File(String name, String md5, String path, Date uploadTime,int uid) {
        this.name = name;
        this.md5 = md5;
        this.path = path;
        this.uploadTime = uploadTime;
        this.uid=uid;
    }
}
