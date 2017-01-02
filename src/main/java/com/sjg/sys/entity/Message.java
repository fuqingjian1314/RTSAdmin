package com.sjg.sys.entity;

/**
 * Created by fuqingjian on 2016/12/1.
 */
public class Message {
    private Integer type;//消息类型，1用户信息，2发生消息
    private String Messageinfo;//信息内容

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getMessageinfo() {
        return Messageinfo;
    }

    public void setMessageinfo(String messageinfo) {
        Messageinfo = messageinfo;
    }
}
