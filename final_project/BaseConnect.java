package kz.aitu.oop.final_project;


import java.sql.Connection;

public interface BaseConnect {

    public Connection connect(String url, String user, String password);
    public String execSQL(String sql,String [] params);
}
