import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Edward
 */
public class DataHandler{
	// DB details
    private static final String DBURL = "jdbc:ucanaccess://CarGo.accdb";
    private static java.sql.Connection con;
    private static java.sql.Statement stm;
    private static java.sql.ResultSet rs;
    private static java.sql.ResultSetMetaData rsMeta;
    private static int columnCount=0;
    public static boolean success = false;
    public static User loggedInUser;

public static boolean registerUser(String username,String password,
                         String email, String cardNumber, String expiry,
                         String securityCode) throws ParseException, SQLException	{   
    String sqlQuery = "SELECT userEmail FROM AppUser WHERE userEmail='"+email+"';";
    try{
        Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        con = java.sql.DriverManager.getConnection(DBURL, "","");
        stm = con.createStatement(       
          java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE, 
          java.sql.ResultSet.CONCUR_UPDATABLE);
        rs = stm.executeQuery(sqlQuery);
        if(rs.first())
        {
            con.close();
            System.out.println("User Exists Already");
            return false;
        }
        else
        {
             sqlQuery = "INSERT INTO AppUser ("
                     + "userName,"
                     + "userPassword,"
                     +"userEmail,"
                     + "userCardNumber,"
                     + "userCardExpiry,"
                     + "userCardSecurityCode"
                     + ") "
                     + "VALUES "
                     + "("
                     + "'"+username+"',"
                     + "'"+password+"',"
                     + "'"+email+"',"
                     + "'"+cardNumber+"',"
                     + "'"+expiry+"',"
                     + "'"+securityCode+"');";
             try{
                 stm = con.createStatement();
                 stm.executeUpdate(sqlQuery); // execute query on the database
                 con.close();
             }
             catch ( java.sql.SQLException sqlex ) {
                 System.err.println( sqlex );
                 System.out.println("This Failed 2");
                 con.close();
             }
             con.close();
             return true;

        }
    }
    catch ( ClassNotFoundException cnfex ) {
       System.err.println("Issue with driver." );
       System.exit( 1 );  // terminate program
    }
    catch ( java.sql.SQLException sqlex ) {
      System.err.println("Check your SQL " + sqlex );
    }
    catch ( Exception ex ) {
        System.err.println( ex );
    }
    con.close();
    return false;
}//End Register User

public static boolean login(String email, String password) throws SQLException
{
    String sqlQuery = "SELECT * FROM AppUser WHERE userEmail='"+email+"'"
            + "AND userPassword='"+password+"';";
    try{
        Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        con = java.sql.DriverManager.getConnection(DBURL, "","");
        stm = con.createStatement(       
          java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE, 
          java.sql.ResultSet.CONCUR_UPDATABLE);
        rs = stm.executeQuery(sqlQuery);
        if(rs.first())
        {
            int Id = rs.getInt(1);
            DataHandler.loggedInUser = new User(
                Id,rs.getString(2), 
                rs.getString(3),rs.getString(4), 
                rs.getString(5),rs.getString(7),
                rs.getString(6));
            System.out.println(Id);
            System.out.println(rs.getString(2));
            System.out.println(rs.getString(3));
            System.out.println(rs.getString(4));
            System.out.println(rs.getString(5));
            System.out.println(rs.getString(6));
            System.out.println(rs.getString(7));
            System.out.println(DataHandler.loggedInUser);
            con.close();
            return true;
            
        }
        else
        {
            System.out.println("ERROR~~~~~~~~~~~~~~~~~~~~~~~");
            con.close();
            return false;
        }
    }
    catch ( ClassNotFoundException cnfex ) {
        System.err.println("Issue with driver." );
        System.exit( 1 );  // terminate program
    }
    catch ( java.sql.SQLException sqlex ) {
      System.err.println("Check your SQL " + sqlex );
    }
    catch ( Exception ex ) {
        System.err.println( ex );
    }
    con.close();
    return false;
        
}

/*############################################################################*/
public static void updateUser(int userId, String name,String password, String email, 
            String cardNumber ,Date cardExpiry,String securityCode) throws SQLException
{
 String sqlQuery = "SELECT * FROM AppUser WHERE userId='"+userId+"';";
    try{
        Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        con = java.sql.DriverManager.getConnection(DBURL, "","");
        stm = con.createStatement(       
          java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE, 
          java.sql.ResultSet.CONCUR_UPDATABLE);
        rs = stm.executeQuery(sqlQuery);
        System.out.println(userId);
        if(rs.first())
        {
            sqlQuery = "UPDATE AppUser SET "
                        + "userName='"+name+"',"
                        + "userPassword='"+password+"',"
                        +"userEmail='"+email+"',"
                        + "userCardNumber='"+cardNumber+"',"
                        + "userCardExpiry='"+cardExpiry+"',"
                        + "userCardSecurityCode='"+securityCode+"' "
                        + "WHERE userId='"+userId+"';";
            try{
                stm = con.createStatement();
                stm.executeUpdate(sqlQuery); // execute query on the database
                con.close();
            }
            catch ( java.sql.SQLException sqlex ) {
                System.err.println( sqlex );
                System.out.println("This Failed 2");
                con.close();
            }
            catch (Exception e)
            {
                System.out.println(e);
            }
        }
        else
        {
            System.out.println("UPDATE FAILED");
            con.close();
        }
    }
    catch ( ClassNotFoundException cnfex ) {
       System.err.println("Issue with driver." );
       System.exit( 1 );  // terminate program
    }
    catch ( java.sql.SQLException sqlex ) {
      System.err.println("Check your SQL " + sqlex );
    }
    catch ( Exception ex ) {
        System.err.println( ex );
    }
    con.close();
}

/*############################################################################*/
public static void addAdmin()
{

}
/*############################################################################*/

/*##########################################################################*/
public static void checkUser(String table)
{
        String sqlQuery2 = "SELECT * FROM "+table+";";
        try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            con = java.sql.DriverManager.getConnection(DBURL, "","");
            stm = con.createStatement(       
              java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE, 
              java.sql.ResultSet.CONCUR_UPDATABLE);
            rs = stm.executeQuery(sqlQuery2);
            rsMeta = rs.getMetaData();
            columnCount = rsMeta.getColumnCount();
        }
        catch ( ClassNotFoundException cnfex ) {
            System.err.println("Issue with driver." );
            System.exit( 1 );  // terminate program
        }
        catch ( java.sql.SQLException sqlex ) {
            System.err.println("Check your SQL " + sqlex );
         }
        catch ( Exception ex ) {
            System.err.println( ex );
        } 
}

/*############################################################################*/
public static void searchRecords(String table, String type)	{
        String sqlQuery = "SELECT * FROM "+table+" WHERE vehicleType='"+type+"';";
        try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            con = java.sql.DriverManager.getConnection(DBURL, "","");
            stm = con.createStatement(       
              java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE, 
              java.sql.ResultSet.CONCUR_UPDATABLE);
            rs = stm.executeQuery(sqlQuery);
            rsMeta = rs.getMetaData();
            columnCount = rsMeta.getColumnCount();
        }
        catch ( ClassNotFoundException cnfex ) {
            System.err.println("Issue with driver." );
            System.exit( 1 );  // terminate program
        }
        catch ( java.sql.SQLException sqlex ) {
            System.err.println("Check your SQL " + sqlex );
         }
        catch ( Exception ex ) {
            System.err.println( ex );
        }
}
        
public static Object[] getVehicleTitles(String table) throws SQLException        {
     Object [] columnNames = new Object[columnCount];
     try{
         for(int col = columnCount; col > 0; col--)
              columnNames[col-1] =  
                      rsMeta.getColumnName(col);
     }
     catch( java.sql.SQLException sqlex ) {
          System.err.println( sqlex );
     }
     con.close();
     return columnNames;
}
        
public static Object[][] getVehicleRows(String table, String type)        {
      searchRecords(table,type);
      Object [][] content;
      try{
          // determine the number of rows
          rs.last();
          int number = rs.getRow();
          content = new Object[number][columnCount];
          rs.beforeFirst();
          
          int i =0;
          while(rs.next()) {
            // each row is an array of objects
            for(int col = 1; col <= columnCount; col++)   
                content[i][col - 1] = rs.getObject(col); 
            i++;
          }
          return content;
       }
       catch( java.sql.SQLException sqlex ) {
              System.err.println( sqlex );
       }
       return null;
}


public static Object[] getUserTitles(String table) throws SQLException        {
     Object [] columnNames = new Object[columnCount];
     try{
         for(int col = columnCount; col > 0; col--)
              columnNames[col-1] =  
                      rsMeta.getColumnName(col);
     }
     catch( java.sql.SQLException sqlex ) {
          System.err.println( sqlex );
     }
     con.close();
     return columnNames;
}
        
public static Object[][] getUserRows(String table)        {
      checkUser(table);
      Object [][] content;
      try{
          // determine the number of rows
          rs.last();
          int number = rs.getRow();
          content = new Object[number][columnCount];
          rs.beforeFirst();
          
          int i =0;
          while(rs.next()) {
            // each row is an array of objects
            for(int col = 1; col <= columnCount; col++)   
                content[i][col - 1] = rs.getObject(col); 
            i++;
          }
          return content;
       }
       catch( java.sql.SQLException sqlex ) {
              System.err.println( sqlex );
       }
       return null;
}

}//End DataHandler class
