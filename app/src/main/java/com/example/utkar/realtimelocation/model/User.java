package com.example.utkar.realtimelocation.model;

public class User {
    private int id;
    private String username;
    public String status;
    private String password;
    private String mob;
    public int getId(){
        return id;

    }
    public void setId(int id){
        this.id=id;
    }
    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username=username;
    }
    public String getPassword()
    {
        return password;
    }
    public void setPassword(String password)
    {
        this.password=password;
    }
    public String getMob(){
        return mob;
    }
    public void setMob(String mob)
    {
        this.mob=mob;
    }



}
