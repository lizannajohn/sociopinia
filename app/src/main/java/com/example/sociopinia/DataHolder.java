package com.example.sociopinia;

import android.provider.ContactsContract;

public class DataHolder {

    String uname, email, pwd;

    //Add Constructor

    public DataHolder(String uname, String email, String pwd) {
        this.uname = uname;
        this.email = email;
        this.pwd = pwd;
    }

    //Add getter and setter

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String mail)
    {
        email = mail;
    }

    public String getUname()
    {
        return uname;
    }

    public void setUname(String username)
    {
        uname = username;
    }

    public String getPwd()
    {
        return pwd;
    }

    public void setPwd(String password)
    {
        pwd = password;
    }
}
