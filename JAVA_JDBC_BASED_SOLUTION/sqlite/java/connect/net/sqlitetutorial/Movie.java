package net.sqlitetutorial;

import java.sql.*;
   
public class Movie {  

//Connecting to database
private static Connection connect() {  
        // SQLite connection string  
        String url = "jdbc:sqlite:C://sqlite/db/fav_movie.db";  
        Connection conn = null;  
        try {  
            conn = DriverManager.getConnection(url);  
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
        return conn;  
    } 
  
    public static void createNewDatabase() {  
        try {  
            Connection conn = Movie.connect();  
            if (conn != null) {  
                DatabaseMetaData meta = conn.getMetaData();  
                System.out.println("The driver name is " + meta.getDriverName());  
                System.out.println("A new database has been created.");  
            }  
   
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
    }

//creating movie table
 public static void createNewTable() {  
        String sql = "CREATE TABLE IF NOT EXISTS movies (\n"  
                + " mov_id integer PRIMARY KEY,\n"  
                + " mov_name text NOT NULL,\n"  
	+"actor_name text NOT NULL,\n"
	+"actress_name text NOT NULL,\n"
	+"director_name text NOT NULL,\n"
	+"year_of_release text NOT NULL\n"
                + ");";  
          
        try{  
            Connection conn = Movie.connect();  
            Statement stmt = conn.createStatement();  
            stmt.execute(sql);  
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
    } 

// Inserting Into Movie Table
    public void insert(String mov_name,String actor_name,String actress_name,String director_name,String year_of_release) {  
        String sql = "INSERT INTO movies(mov_name,actor_name,actress_name,director_name,year_of_release) VALUES(?,?,?,?,?)";  
   
        try{  
            Connection conn = this.connect();  
            PreparedStatement pstmt = conn.prepareStatement(sql);  
            pstmt.setString(1, mov_name);  
            pstmt.setString(2, actor_name);
            pstmt.setString(3, actress_name);
            pstmt.setString(4, director_name);
            pstmt.setString(5, year_of_release);  
            pstmt.executeUpdate();  
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
    } 

//Selecting All Movie Details
 public void selectAll(){  
        String sql = "SELECT * FROM movies";  
          
        try {  
            Connection conn = this.connect();  
            Statement stmt  = conn.createStatement();  
            ResultSet rs    = stmt.executeQuery(sql);  
              
            // loop through the result set  
            while (rs.next()) {  
                System.out.println("(" +rs.getInt("mov_id") + "," +   
                                   rs.getString("actor_name") + ","+  
                                   rs.getString("actress_name")+","+
		 rs.getString("director_name")+","+
 		rs.getString("year_of_release")+")");  
            }  
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
    }    

//Selecting Movies Based On Actor Name
 public void selectByActorName(String actor_name){  
        String sql = "SELECT * FROM movies WHERE actor_name=\""+actor_name+"\"";  
          
        try {  
            Connection conn = this.connect();  
            Statement stmt  = conn.createStatement();  
            ResultSet rs    = stmt.executeQuery(sql);  
              
            // loop through the result set  
            while (rs.next()) {  
                System.out.println("(" +rs.getInt("mov_id") + "," +   
                                   rs.getString("actor_name") + ","+  
                                   rs.getString("actress_name")+","+
		 rs.getString("director_name")+","+
 		rs.getString("year_of_release")+")"); 
            }  
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
    }    
  
//Main Function
    public static void main(String[] args) {
      //object of movie class
        Movie app = new Movie();  
        /* createNewDatabase();  
        createNewTable(); 
        app.insert("Doctor Strange in the Multiverse of Madness", "Benedict Cumberbatch", "Elizabeth Olsen", "Sam Raimi", "2022");
        app.insert("KGF2", "Yash", "Srinidhi Shetty", "Prashanth Neel", "2022") ;
        app.insert("Iron Man", "Robert Downey", "Gwyneth Paltrow", "Jon Favreau", "2008");
        app.insert("Spider Man Far From Home", "Tom Holland", "Zendaya", "Jon Watts", "2019");
        app.insert("777 charlie", "Rakshit Shetty", "Sangeetha Sringeri", "Kiranraj K", "2022");
        app.insert("KGF1", "Yash", "Srinidhi Shetty", "Prashanth Neel", "2018"); */
        app.selectAll();
        // app.selectByActorName("Yash");
    }  
}  
