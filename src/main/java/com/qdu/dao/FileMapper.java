package com.qdu.dao;


import com.qdu.pojo.File;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
//@Component 万能
@Repository
public interface FileMapper {
    /**
     * 通过主键获取一行数据
     * @return
     */
    File getById(int id);

    /**
     * 插入一行数据
     * @param file
     * @return
     */
    int save(File file);

    /**
     * 更新一行数据
     * @param file
     * @return
     */
    int update(File file);

    /**
     * 删除一行数据
     * @param id
     * @return
     */
    int deleteById(int id);

    /**
     * 根据一个或多个属性获取File
     * @param file
     * @return
     */
    File getByFile(File file);

    /**
     * 根据用户id返回属于该用户的所有文件
     * @param uid
     * @return
     */
    List<File> getFilesByUid(int uid);
}
