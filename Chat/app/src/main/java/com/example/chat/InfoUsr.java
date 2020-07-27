package com.example.chat;

public class InfoUsr {
    public String usrname;
    public String usremail;
    public String usrpass;
    public String uid;

    public InfoUsr(String usrname, String usremail, String usrpass,String uid) {
        this.usrname = usrname;
        this.usremail = usremail;
        this.usrpass = usrpass;
        this.uid=uid;
    }


    public InfoUsr ()
    {

    }

    public String getUsrname() {
        return usrname;
    }

    public void setUsrname(String usrname) {
        this.usrname = usrname;
    }

    public String getUsremail() {
        return usremail;
    }

    public void setUsremail(String usremail) {
        this.usremail = usremail;
    }

    public String getUsrpass() {
        return usrpass;
    }

    public void setUsrpass(String usrpass) {
        this.usrpass = usrpass;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

}
