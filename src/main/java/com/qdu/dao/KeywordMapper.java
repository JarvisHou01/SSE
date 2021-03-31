package com.qdu.dao;

import com.qdu.pojo.Keyword;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface KeywordMapper {

    int add(Keyword keyword);

    int deleteByFid(int fid);

    int[] search(String trapdoor);

}
