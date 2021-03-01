package politicalchess;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*

This is the DataBase class which is responsible to call the database, it also
allows to input and get info from the tables of the database

Created by: Mohamed Soliman --- ID: 17966266

*/

public class DataBase
{
    Connection conn = null;
    String url = "jdbc:derby:ChessDB; create=true";  //url of the DB host
    String username = "pdc";  //your DB username
    String password = "pdc";   //your DB password
    Statement statement = null;
    ResultSet rs;
    
    public DataBase() throws SQLException
    {
        
    }
    
    //this method connects to the database
    public String connectChessDB()
    {
        String x = "";
        try 
        {
            this.conn = DriverManager.getConnection(this.url, this.username, this.password);
            x = url+" connected...";
            System.out.println(x);
            statement = conn.createStatement();
            
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return x;
    }
    
    //this method will pring the instruction of the game from the table in the database
    public String print() throws SQLException
    {
        String s = "SELECT * FROM INFO";
        rs=statement.executeQuery(s);
        String x = "";
        
        while(rs.next())
        {
            String string = rs.getString("INFO");
            System.out.println(string);
            x += string + "\n";
        }
        
        return x;
    }
    
    //this method will pring the moves that happened during the current game
    public String printMoves() throws SQLException
    {
        String s = "SELECT * FROM CHESS";
        rs=statement.executeQuery(s);
        String x = "";
        
        while(rs.next())
        {
            String string = rs.getString("MOVES");
            System.out.println(string);
            x += string + "\n";
        }
        
        return x;
    }
    
    //delete data from a table
    public void delete() throws SQLException
    {
        statement.executeUpdate("DELETE FROM CHESS");
    }
    
    //add info to the table
    public void addInfo(String string) throws SQLException
    {
        statement.executeUpdate("INSERT INTO INFO " + string);
    }
   
    //add moves to the moves table
    public void add(String string) throws SQLException
    {
        statement.executeUpdate("INSERT INTO CHESS " + string);
    }
    
    
    
}
