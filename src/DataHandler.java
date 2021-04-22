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
    public boolean success = false;
    
   
public boolean registerUser(String username,String password,
                         String email, String cardNumber, String expiry,
                         String securityCode) throws ParseException	{
    DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
    Date datePicker = df.parse(expiry);    
    String sqlQuery = "SELECT * FROM AppUser WHERE "
            + "userName='"+username+"'"
            + " AND userPassword='"+password+"'"
            + " AND userEmail='"+email+"';";
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
                     + "'"+datePicker+"',"
                     + "'"+securityCode+"');";
             try{
                 stm = con.createStatement();
                 stm.executeUpdate(sqlQuery); // execute query on the database
                 con.close();
             }
             catch ( java.sql.SQLException sqlex ) {
                 System.err.println( sqlex );
                 System.out.println("This Failed 2");
             }
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
    return false;
}//End Register User

/*############################################################################*/
public static void addAdmin()
{

}
/*############################################################################*/
public static void searchRecords(String table)	{
    
  
}

/*##########################################################################*/
public void checkUser()
{
  
}

/*############################################################################*/
public static Object[] getTitles(String table)        {
    Object [] columnNames = new Object[columnCount];
    try{
       for(int col = columnCount; col > 0; col--)
           columnNames[col-1] =  rsMeta.getColumnName(col);
    }
    catch( java.sql.SQLException sqlex ) {
         System.err.println( sqlex );
    }
    return columnNames;
}
/*############################################################################*/      
public static Object[][] getRows(String table)
{
    searchRecords(table);
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
