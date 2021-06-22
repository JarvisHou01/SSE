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
public class File implements Serializable {

    private int id;
    private String name;
    private String md5;
    private String path;
    private int ope;
    private Date uploadTime;
    private int uid;



}
