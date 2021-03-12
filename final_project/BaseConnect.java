package kz.aitu.oop.final_project;


import java.sql.Connection;

public interface BaseConnect { // it is for connect to the database

    public Connection connect(String url, String user, String password); // connect with url, user and password
    public String execSQL(String sql,String [] params); 
}
