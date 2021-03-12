package kz.aitu.oop.final_project.AdapterDesignPattern;

import kz.aitu.oop.final_project.PostgreSQL;
import kz.aitu.oop.final_project.Validator;

import java.util.Scanner;

public class User {
    Scanner in = new Scanner(System.in);
    Validator valid = new Validator();
    PostgreSQL database = PostgreSQL.getInstance();

    public User() {



        //private String
        // public User(String firstname,String lastname, String gender, String dateofbirth)
    }
    public int auth(){

        for (int i = 0; i < 1; ) {
            System.out.println("Enter your phone number: ");
            String phonenumber = in.nextLine();
            if (phonenumber.equalsIgnoreCase("exit")) {
                break;
            }
            System.out.println("Enter your password: ");
            if (phonenumber.equalsIgnoreCase("exit")) {
                break;
            }
            String password = in.nextLine();
        if(database.checkauthentication( phonenumber,password)){
            System.out.println("Authentification complete");
            return database.getid(phonenumber);
        }
            System.out.println("Invalid password or login");
    }
        return 1;
}
}