package com.media.video_meeting.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Webcon implements Serializable {
    @TableId(type = IdType.INPUT)
    private String account;
    private String password;
    private String nickname;
    private Integer priority;
    private String proxy;
    private String clients;
    private Integer solutionid;


    public void setProxy(String proxy){
        this.proxy = proxy;
    }

    public void setClients(String clients) {
        this.clients = clients;
    }

    public void setProxys(String[] proxys) {
        String strs = "";
        for(String str : proxys){
            if(strs != ""){
                strs += "|";
            }
            strs += str;
        }
        this.proxy = strs;
    }

    public void setClientss(String[] clientss) {
        String strs = "";
        for(String str : clientss){
            if(strs != ""){
                strs += "|";
            }
            strs += str;
        }
        this.clients = strs;
    }
}