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
        for (int i=0;i<1;) {
            System.out.println();
            System.out.println("               Main Page\n" +
                    "Enter 1 in order to order a ticket\n" +
                    "Enter 2 to view your passenger\n" +
                    "Enter 3 to view your history\n" +
                    "Enter 4 to view your profile\n" +
                    "Enter 5 to exit from account");
            String input = in.nextLine();
            if(input.equals("1")){
                System.out.println("From: ");
                String from =in.nextLine();
                System.out.println("To: ");
                String to =in.nextLine();
                database.printflights(from,to);
            }
            if (input.equals("2")) {
                for (int j=0;j<1;) {
                    System.out.println("Your passengers:");
                    database.printpassenger(id);
                    System.out.println("Enter 1 to add your user");
                    System.out.println("Enter 2 to return to main Menu");
                    input = in.nextLine();
                    if (input.equals("1")) {
                        passenger.addpassenger(id);
                    } else if (input.equals("2")) {
                        break;
                    }
                }
            }

            else if (input.equals("3")) {
                //database.execSQL();
            }
            else if (input.equals("4")) {
                String query= "select user_id,phone_number,email from users where user_id= "+id;
                database.printinfo(query);
            }
            else if(input.equals("5")) break;
            }
        }
    }

