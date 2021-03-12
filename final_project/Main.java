package kz.aitu.oop.final_project;

import kz.aitu.oop.final_project.AdapterDesignPattern.RegInterface;
import kz.aitu.oop.final_project.AdapterDesignPattern.Registration;
import kz.aitu.oop.final_project.AdapterDesignPattern.User;

import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        String url="jdbc:postgresql://localhost:5432/Avia";
        String usersql="postgres";
        String passwordsql="123";
        PostgreSQL database1= PostgreSQL.getInstance();
        database1.connect(url,usersql,passwordsql);

        Validator valid =new Validator();
        Scanner in= new Scanner(System.in);
        String exit="exit";
        int id;
        for(int ex=0;ex<1;){
        System.out.println("Enter 1 - if you already have account\n" +
                "Enter 2 - for registration\n" +
                "Enter 3 - to exit app");
        String ans=in.nextLine();
         if (ans.equals("1")){
             User user= new User();
             id= user.auth();
             if (id!=0){
                 MainPage mainpage=new MainPage(id);
                 mainpage.view();
             }


         } else if (ans.equals("2")){
             RegInterface reg1=new Registration();
             reg1.register();

         }
         else if(ans.equals("3")){
             break;
         }

        }
        database1.close();
    }

    }


