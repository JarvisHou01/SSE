package com.qdu.pojo;


import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Keyword {
    private int id;
    private String keyword;
    private int fid;
    private String position;

    public Keyword(String keyword, int fid,String position) {
        this.keyword = keyword;
        this.fid = fid;
        this.position=position;
    }

    public Keyword(String keyword, int fid) {
        this.keyword = keyword;
        this.fid = fid;
    }

}
