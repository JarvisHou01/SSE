package com.qdu.pojo;


import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private int id;
    private String username;
    private String password;

    public User(String username,String password){
        this.username=username;
        this.password=password;
    }



}
