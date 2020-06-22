package com.example.myapp;

public class carditem2 {
    private String roleid;
    private String appid;
    public carditem2()
    {

    }
    public carditem2(String roleid,String appid)
    {
        this.roleid=roleid;
        this.appid=appid;
    }
    public String getRoleid()
    {
        return roleid;
    }
    public String getAppid()
    {
        return appid;
    }
}