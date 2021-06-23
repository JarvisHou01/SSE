package com.qdu.utils;


import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * 文件操作工具类
 */
public class FileUtils {

    /**
     * 写入文件
     * @param target
     * @param src
     * @throws IOException
     */
    public static void write(String target, InputStream src) throws IOException {
        OutputStream os = new FileOutputStream(target);
        byte[] buf = new byte[1024];
        int len;
        while (-1 != (len = src.read(buf))) {
            os.write(buf,0,len);
        }
        os.flush();
        os.close();
    }


    /**
     * 生成随机文件名
     * @return
     */
    public static String generateFileName() {
        return UUID.randomUUID().toString();
    }

    /**
     * 找交集
     * @param array1
     * @param array2
     * @return
     */
    public static int[] findSameElementIn2Arrays(int[] array1, int[] array2) {

        Set<Integer> sameElementSet = new HashSet<Integer>();//用来存放两个数组中相同的元素
        Set<Integer> tempSet = new HashSet<Integer>();//用来存放数组1中的元素

        for (int i = 0; i < array1.length; i++) {
            tempSet.add(array1[i]);//把数组1中的元素放到Set中，可以去除重复的元素
        }

        for (int j = 0; j < array2.length; j++) {
            //把数组2中的元素添加到tempSet中
            //如果tempSet中已存在相同的元素，则tempSet.add(array2[j])返回false
            if (!tempSet.add(array2[j])) {
                //返回false,说明当前元素是两个数组中相同的元
                sameElementSet.add(array2[j]);
            }
        }

        Integer[] integers = sameElementSet.toArray(new Integer[sameElementSet.size()]);
        int[] same = Arrays.stream(integers).mapToInt(Integer::valueOf).toArray();

        return same;
    }


}
