package kz.aitu.oop.final_project;

import java.util.Scanner;

public class Passenger { // Create a passenger class
    Scanner in = new Scanner(System.in);
    Validator valid = new Validator(); // Add a new validator.
    PostgreSQL database = PostgreSQL.getInstance(); 
    public Passenger(){};

    public void addpassenger(int id){
        int  pas_id=database.passengermaxid()+1;
        System.out.print("Enter your firstname: "); // Display "Enter your first name".
        String firstname = in.nextLine();
        System.out.print("Enter your secondname: "); // Display "Enter your secondname".
        String secondname = in.nextLine();
        System.out.print("Enter your gender: "); // Display "Enter your gender".
        String gender = in.nextLine();
        System.out.print("Enter your dateofbirth: "); // Display "Enter your dateofbirth".
        String dateofbirth = in.nextLine();
        for (int i=0;i<1;){ 
            if(valid.checkDate(dateofbirth)){ // If you entered your document number incorrectly, you need to enter it back.
                break;
            }
            System.out.print("Enter your dateofbirth: "); // If you entered your date of birth incorrectly, you need to enter it back.
            dateofbirth = in.nextLine();
        }
        System.out.println("Enter your citizenship: "); //  Display "Enter your citizenship".
        String citizenship = in.nextLine();
        System.out.println("Enter your Documentno: "); //  Display "Enter your Documentno".
        String documentno= in.nextLine();
        for (int i=0;i<1;) {
            if(valid.checkDocumentno(documentno)){ 
                break;
            }
            System.out.println("Enter your Documentno: "); // If you entered your document number incorrectly, you need to enter it back.
            documentno = in.nextLine();
        }
        System.out.println("Enter your dateofexpire: "); //  Display "Enter your dateofexpire".
        int dateofexpire= Integer.parseInt(in.nextLine());
        /*for (int i=0;i<1;) {
            if(valid.checkDate(dateofexpire)){
                break;
            }
            System.out.println("Enter your dateofexpire: "); // If you entered your date of expire incorrectly, you need to enter it back.
            dateofexpire = in.nextLine();
        }*/
        System.out.println("Enter your IIN: "); //  Display "Enter your IIN".
        String IIN= in.nextLine();
        for (int i=0;i<1;) {
            if(valid.checkIIN(IIN)){
                break;
            }
            System.out.println("Enter your IIN: "); // If you entered your IIN incorrectly, you need to enter it back.
            IIN = in.nextLine();
        }
        String sql="insert into Passengers values ("+pas_id+','+id+",'"+firstname+"','"+secondname+"','"+gender+"','"
            +dateofbirth+"','"+citizenship+"','"+documentno+"','"+dateofexpire+"','"+IIN+"')";

        database.execSQL(sql,null);
    }

}
