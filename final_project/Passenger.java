package kz.aitu.oop.final_project;

import java.util.Scanner;

public class Passenger {
    Scanner in = new Scanner(System.in);
    Validator valid = new Validator();
    PostgreSQL database = PostgreSQL.getInstance();
    public Passenger(){};

    public void addpassenger(int id){
        int  pas_id=database.passengermaxid()+1;
        System.out.print("Enter your firstname: ");
        String firstname = in.nextLine();
        System.out.print("Enter your secondname: ");
        String secondname = in.nextLine();
        System.out.print("Enter your gender: ");
        String gender = in.nextLine();
        System.out.print("Enter your dateofbirth: ");
        String dateofbirth = in.nextLine();
        for (int i=0;i<1;){
            if(valid.checkDate(dateofbirth)){
                break;
            }
            System.out.print("Enter your dateofbirth: ");
            dateofbirth = in.nextLine();
        }
        System.out.println("Enter your citizenship: ");
        String citizenship = in.nextLine();
        System.out.println("Enter your Documentno: ");
        String documentno= in.nextLine();
        for (int i=0;i<1;) {
            if(valid.checkDocumentno(documentno)){
                break;
            }
            System.out.println("Enter your Documentno: ");
            documentno = in.nextLine();
        }
        System.out.println("Enter your dateofexpire: ");
        int dateofexpire= Integer.parseInt(in.nextLine());
        /*for (int i=0;i<1;) {
            if(valid.checkDate(dateofexpire)){
                break;
            }
            System.out.println("Enter your dateofexpire: ");
            dateofexpire = in.nextLine();
        }*/
        System.out.println("Enter your IIN: ");
        String IIN= in.nextLine();
        for (int i=0;i<1;) {
            if(valid.checkIIN(IIN)){
                break;
            }
            System.out.println("Enter your IIN: ");
            IIN = in.nextLine();
        }
        String sql="insert into Passengers values ("+pas_id+','+id+",'"+firstname+"','"+secondname+"','"+gender+"','"
            +dateofbirth+"','"+citizenship+"','"+documentno+"','"+dateofexpire+"','"+IIN+"')";

        database.execSQL(sql,null);
    }

}
