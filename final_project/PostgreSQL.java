package kz.aitu.oop.final_project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class PostgreSQL implements BaseConnect{
    //Singleton Design Pattern
    private Connection con =null;
    private static PostgreSQL single_instance;
    //private constructor
    private PostgreSQL(){

    }

    
    public static PostgreSQL getInstance(){
        if (single_instance==null)
            single_instance =new PostgreSQL();

        return single_instance;
    }
    @Override
    public Connection connect(String url, String user, String password){
        try {
            con = DriverManager.getConnection(url, user, password);
            //System.out.println("Connection established: PostgreSQL");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
              return con;
        }


  @Override
  public String execSQL(String sql,String [] params){
      Statement stmt;
      if (sql.charAt(0)=='s'){
          try {
              stmt =con.createStatement();
              ResultSet rs = stmt.executeQuery( sql );

              while ( rs.next() ) {

                  System.out.println(rs.getInt(1)+ " "+rs.getString(2)+ " "+
                          rs.getString(3));

              }

              stmt.close();


          } catch (SQLException e ) {
              System.out.println(e.getMessage());
          }
      } else {
          try {
              stmt =con.createStatement();
              stmt.executeUpdate(sql);
             // System.out.println("Query was executed");


          } catch ( SQLException e ) {
              System.out.println(e.getMessage());
          }

      }

      return null;
   }
  public int maxid(){
        String sql="select max(user_id) from users";
      Statement stmt;

          try {
              stmt =con.createStatement();
              ResultSet rs = stmt.executeQuery(sql);
                rs.next();
                  return rs.getInt(1);


              //stmt.close();
          } catch (SQLException e ) {
              System.out.println(e.getMessage());
          }
          return 0;
    }
    public int passengermaxid(){
        String sql="select max(passenger_id) from Passengers";
        Statement stmt;

        try {
            stmt =con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            return rs.getInt(1);


            //stmt.close();
        } catch (SQLException e ) {
            System.out.println(e.getMessage());
        }
        return 0;
    }
    void printpassenger(int id){
        Statement stmt;
        String sql= "select first_name ,second_name,gender,dateofbirth, citizenship, documentno, dateofexpire, IIN from passengers,users where Passengers.user_id="+id;
        try {
            stmt =con.createStatement();
            ResultSet rs = stmt.executeQuery( sql );
            rs.next();


            System.out.println(rs.getString(1)+' '+rs.getString(2)+ ' '+ rs.getString(3)+ ' '+rs.getString(4)+ ' '+
                    rs.getString(5)+ ' '+rs.getString(6)+' '+ rs.getString(7)+ ' '+rs.getString(8));
            System.out.println();

            stmt.close();

        } catch (SQLException e ) {
            //System.out.println(e.getMessage());
            System.out.println("You don't have any passenger");
            System.out.println();
        }
    }
    public boolean checkauthentication(String phonenumber,String password){
        Statement stmt;
        String sql="select users_password from users where phone_number= '"+phonenumber+"'";
        try {
            stmt =con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
             rs.next();
            String s=(rs.getString(1));
            s=s.replaceAll("\\s","");
            return s.equals(password);


            //stmt.close();
        } catch (SQLException e ) {
            //System.out.println(e.getMessage());
        }
       return false;
    }

    public int getid(String phonenumber){
        String sql="select user_id from users where phone_number= '"+phonenumber+"'";
        Statement stmt;

        try {
            stmt =con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            return rs.getInt(1);


            //stmt.close();
        } catch (SQLException e ) {
            System.out.println(e.getMessage());
        }
        return 0;
    }
    public void printflights(String from1,String to1,int user_id){
        from1="'"+from1+"'";
        to1="'"+to1+"'";
        String sql="select flight_id,fromwhere,towhere,dateofflight, min_cost from flight where fromwhere="+from1+"and towhere="+to1;
        Statement stmt;

        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                System.out.println(rs.getInt(1) + "   " + rs.getString(2) + ' ' + rs.getString(3) + ' ' + rs.getString(4) + " Ticket min_cost: " + rs.getString(5));
                System.out.println();
            }
            //stmt.close();

        }
            catch (SQLException e ){
                // System.out.println(e.getMessage());
                System.out.println("there are no flights from this city to the destination city");
                return;
            }

        Scanner in= new Scanner(System.in);
        System.out.println("Enter id of flight which you want to book: \n" +
                "Type exit to return to main menu");
        String flight_id=in.nextLine();

            if(flight_id.equals("exit")) {}
            else {
                booknum(flight_id, user_id);
            }

    }
    public int numberseats(String flight_id){
        String sql="select count(seat_num) from place inner join planes on place.plane_id=planes.plane_id " +
                "inner join flight on flight.plane_id=planes.plane_id  where status='available' and flight_id="+flight_id;
        Statement stmt;

        try {
            stmt =con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            return rs.getInt(1);


            //stmt.close();
        } catch (SQLException e ) {
            System.out.println(e.getMessage());
        }
        return 0;
    }


    public void booknum(String flight_id,int user_id){
        //String sql="";
        Scanner in= new Scanner(System.in);
        int numberofseats= numberseats(flight_id);
        System.out.println("Number of available tickets "+numberofseats);
        System.out.println("How many tickets do you want to book?");
        int bookseats=Integer.parseInt(in.nextLine());
        if(bookseats>numberofseats){
            System.out.println("There are no so many tickets");
            return;
        }
        int cost=ticketcost(flight_id,bookseats);
        System.out.println("Total price is "+cost);
        System.out.println("Enter 1 if you sure you want to book a ticket\n" +
                "Enter 2 to exit");
        String ans = in.nextLine();
        if(ans.equals("1")){
            bookingtickets(flight_id,bookseats,user_id);
        }

    }
    public int ticketcost(String flight_id,int bookseats){
        String sql="select min_cost from flight where flight_id="+flight_id;
        Statement stmt;

        try {
            stmt =con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            return rs.getInt(1)*bookseats;


            //stmt.close();
        } catch (SQLException e ) {
            return 0;
        }
    }

    public int getplaneid(String flight_id){
        String sql="select plane_id from flight where flight_id="+flight_id;
        Statement stmt;

        try {
            stmt =con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            return rs.getInt(1);


            //stmt.close();
        } catch (SQLException e ) {
            return 0;
        }
    }
    public String getseatnum(int plane_id){
     String sql="select seat_num from place where status = 'available' and plane_id="+plane_id+" offset 0 limit 1";
        Statement stmt;

        try {
            stmt =con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            return rs.getString(1);


            //stmt.close();
        } catch (SQLException e ) {
            System.out.println("No places");
        }
        return null;
    }
    public void updateseat(int plane_id,String seatnum){
        String update="update place set status='unavailable' where plane_id= "+plane_id+" and seat_num='"+seatnum+"'";
        Statement stmt;
        try {
            stmt =con.createStatement();
            stmt.executeUpdate(update);
            // System.out.println("Query was executed");


        } catch ( SQLException e ) {
            System.out.println(e.getMessage());
        }
    }
    public void bookingtickets(String flight_id,int numbers,int user_id){

        int booking_id=bookingmaxid();
        int plane_id=getplaneid(flight_id);
        Statement stmt;
        for (int i=0;i<numbers;i++){
            String seatnum=getseatnum(plane_id);
            String sql="insert into booking values ("+booking_id+",'"+ user_id+"','"+flight_id+ "','"+plane_id+"','"+seatnum+"')";
            try {
                stmt =con.createStatement();
                stmt.executeUpdate(sql);
                // System.out.println("Query was executed");

            } catch ( SQLException e ) {
                System.out.println(e.getMessage());
            }

            updateseat(plane_id,seatnum);
            booking_id++;
                //stmt.close();

        }
    }

    public int bookingmaxid(){
        String sql="select max(booking_id) from booking";
        Statement stmt;

        try {
            stmt =con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            return rs.getInt(1)+1;


            //stmt.close();
        } catch (SQLException e ) {
            System.out.println(e.getMessage());
        }
        return 1;
    }
    public boolean checkphone(String phonenumber){
        String sql="select phone_number from users where phone_number='"+phonenumber+"'";
        Statement stmt;

        try {
            stmt =con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            return false;

        } catch (SQLException e ) {
            return true;
        }
    }
    public void printhistory(int id){
        String sql="select fromwhere, towhere,dateofflight,seat_num " +
                "from flight inner join booking on booking.flight_id=flight.flight_id where user_id= "+id;
        Statement stmt;

        try {
            stmt =con.createStatement();
            ResultSet rs = stmt.executeQuery( sql );
            while ( rs.next() ) {
                System.out.println("From " + rs.getString(1) + " To " + rs.getString(2) + " Date: " +
                        rs.getString(3)+ " Seatnum: " + rs.getString(4));
                System.out.println();
            }
           // stmt.close();

        } catch (SQLException e ) {
            //System.out.println(e.getMessage());
            System.out.println("You don't have any flights");
        }
    }


    public void printinfo(String sql){
        Statement stmt;

            try {
                stmt =con.createStatement();
                ResultSet rs = stmt.executeQuery( sql );
                rs.next();


                    System.out.println("Your id is:"+ rs.getInt(1)+ "\nYour phonenumber is: "+rs.getString(2)+ "\nYour email is: "+
                            rs.getString(3));
                    System.out.println();

                stmt.close();

            } catch (SQLException e ) {
                System.out.println(e.getMessage());
            }
    }


    public void close(){
        try{
           con.close();
        } catch (SQLException e ) {
            System.out.println(e.getMessage());
        }
    }

 }



