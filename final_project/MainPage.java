package kz.aitu.oop.final_project;

import java.util.Scanner;

public class MainPage {
    private int id;
    Passenger passenger=new Passenger();
    Scanner in = new Scanner(System.in);
    PostgreSQL database = PostgreSQL.getInstance();
    public MainPage(int id){
        this.id=id;
    }
    public void view(){
        for (int i=0;i<1;) { //infinity loop
            System.out.println();
            System.out.println("               Main Page\n" +
                    "Enter 1 in order to order a ticket\n" +
                    "Enter 2 to view your passenger\n" +
                    "Enter 3 to view your history\n" +
                    "Enter 4 to view your profile\n" +
                    "Enter 5 to exit from account"); //main page menu
            String input = in.nextLine();
            if(input.equals("1")){ //if user enter 1 he will go to the order a ticket
                System.out.println("From: "); //from where
                String from =in.nextLine();
                System.out.println("To: "); //to where
                String to =in.nextLine();
                database.printflights(from,to,id); //flights from database
            }
            if (input.equals("2")) { //if user enter 2 he will go to the picking up the passengers
                for (int j=0;j<1;) { //infinity loop
                    System.out.println("Your passengers:");
                    database.printpassenger(id); //it will be show passengers from database
                    System.out.println("Enter 1 to add your user"); //if user enter 1 he will go to addpassenger
                    System.out.println("Enter 2 to return to main Menu");//he will go to the Main page menu again
                    input = in.nextLine();
                    if (input.equals("1")) {
                        passenger.addpassenger(id); //add passenger
                    } else if (input.equals("2")) {
                        break; //go to the memu
                    }
                }
            }

            else if (input.equals("3")) {
                //database.execSQL();
            }
            else if (input.equals("4")) {
                String query= "select user_id,phone_number,email from users where user_id= "+id;
                database.printinfo(query); //profile view from database
            }
            else if(input.equals("5")) break; //exit
            }
        }
    }

