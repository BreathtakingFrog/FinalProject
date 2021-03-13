package kz.aitu.oop.final_project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class PostgreSQL implements BaseConnect{
    //Singleton Design Pattern
    private Connection con =null;
    private static PostgreSQL single_instance;
    private PostgreSQL(){//private constructor

    }

    //static method to create instance of a class
    public static PostgreSQL getInstance(){
        if (single_instance==null)
            single_instance =new PostgreSQL();

        return single_instance;
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
    
    //database connection
    @Override
    public Connection connect(String url, String user, String password){
        try {
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Connection established: PostgreSQL");

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
              System.out.println("Query was executed");


          } catch ( SQLException e ) {
              System.out.println(e.getMessage());
          }

      }

      return null;
   }
    //return maxid in order to register users
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
    
    //return passengermaxid in order to register passenger
    public int passengermaxid(){
        String sql="select max(pasenger_id) from Passengers";
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
    
    //printpassenger method to print all passenger in your account
    void printpassenger(int id){
        Statement stmt;
        String sql= "select first_name ,second_name,gender,dateofbirth, citizenship, documentno, dateofexpare, IIN from passengers,users where Passengers.user_id="+id;
        try {
            stmt =con.createStatement();
            ResultSet rs = stmt.executeQuery( sql );
            rs.next();


            System.out.println(rs.getString(1)+' '+rs.getString(2)+ ' '+ rs.getString(3)+ ' '+rs.getString(4)+ ' '+
                    rs.getString(5)+ ' '+rs.getString(6)+' '+ rs.getString(7)+ ' '+rs.getString(8));
            System.out.println();

            stmt.close();

        } catch (SQLException e ) {
            System.out.println(e.getMessage());
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
            System.out.println(e.getMessage());
        }
       return false;
    }

    //method for getting id thanks to phonenumber
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
    
    //print all flight from city where user indicate and to city which user indicate
    public void printflights(String from,String to){
        from="'"+from+"'";
        to="'"+to+"'";
        String sql="select fromwhere,towhere,dateofflight, min_cost from flight where fromwhere="+from+"and towhere="+to;
        Statement stmt;

        try {
            stmt =con.createStatement();
            ResultSet rs = stmt.executeQuery( sql );

            while(rs.next()){
            System.out.println(rs.getString(1)+' '+rs.getString(2)+ ' '+ rs.getString(3)+ " Ticket cost: "+rs.getString(4));}
            System.out.println();

            stmt.close();

        } catch (SQLException e ) {
            System.out.println(e.getMessage());
        }
    }
    
    //print all user details
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

    //close database connnection
    public void close(){
        try{
           con.close();
        } catch (SQLException e ) {
            System.out.println(e.getMessage());
        }
    }

 }



