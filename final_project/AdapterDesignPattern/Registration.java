package kz.aitu.oop.final_project.AdapterDesignPattern;
import kz.aitu.oop.final_project.PostgreSQL;
import kz.aitu.oop.final_project.Validator;

import java.util.Scanner;

public class Registration extends User implements RegInterface  {
    private int id_gen=database.maxid()+1;
    //PostgreSQL database= PostgreSQL.getInstance();
    public Registration(){};
    //Scanner in= new Scanner(System.in);
    //Validator valid =new Validator();

    @Override
    public void register(){
        System.out.println(id_gen);
        System.out.println("Enter your phone number: ");
        String phonenumber = in.nextLine();
        for (int i=0;i<1;) {
            if (phonenumber.isEmpty()||!valid.checkPhone_number(phonenumber)){
                System.out.println("Enter your phone number: ");
                phonenumber = in.nextLine();
            } else {
                break;
            }
        }
        System.out.println("Enter your email: ");
        String email = in.nextLine();
        for (int i=0;i<1;) {
            if (email.isEmpty()){
                System.out.println("Enter your email: ");
                email = in.nextLine();
            } else{
                break;
            }
        }
        System.out.println("Enter password: (Password must to contain at least 1 uppercase letter," +
                " 1 lowercase letter, 1 digit, 1 special symbol(@,$,!,^) and length more or equal to 8.) ");
        String password = in.nextLine();
        for (int i=0;i<1;) {

            if(valid.checkPassword(password)){
                break;
            }else {
                System.out.println("Incorrect syntax. Enter your password again: ");
                password = in.nextLine();
            }
        }
        String sql = "insert into Users values ("+id_gen +",'" + phonenumber +"','" + email +"','"+ password +"')";
        database.execSQL(sql,null);
        System.out.println("The account number " + id_gen +" with phone number: "+ phonenumber +" and email: " + email +
                " has benn successfully created");
        id_gen++;
    }




}
