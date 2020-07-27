package com.example.chat;

import java.util.Date;

public class Message {

    private String msgTxt;
    private String msgUsr;
    private long msgTime;
    static String chatWith = "";


    public Message(String msgTxt, String msgUsr) {
        this.msgTxt = msgTxt;
        this.msgUsr = msgUsr;
        msgTime = new Date().getTime();
    }

    public Message() {
    }

    public String getMsgTxt() {
        return msgTxt;
    }

    public void setMsgTxt(String msgTxt) {
        this.msgTxt = msgTxt;
    }

    public String getMsgUsr() {
        return msgUsr;
    }

    public void setMsgUsr(String msgUsr) {
        this.msgUsr = msgUsr;
    }

    public long getMsgTime() {
        return msgTime;
    }

    public void setMsgTime(long msgTime) {
        this.msgTime = msgTime;
    }
}
