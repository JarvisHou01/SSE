package com.qdu.pojo;


import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class User {

    private int id;
    private String name;
    private String password;

    public User(String name,String password){
        this.name=name;
        this.password=password;
    }



}
