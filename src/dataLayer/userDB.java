package dataLayer;

import java.sql.*;

/**
 * Created by jakob on 04-04-2017.
 */
public class userDB {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    //static final String DB_URL = "jdbc:mysql://localhost/dat16B";

    //  Database credentials
    //static final String USER = "dat16buser";
    //static final String PASS = "123";


    public boolean isValidUserLogin(String sUserName, String sUserPassword){

        Connection conn = null;
        Statement stmt = null;
        boolean validLoginResult = false;

        try{
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            //conn = DriverManager.getConnection(DB_URL,USER,PASS);
            String DB_Url = System.getProperty("JDBC_CONNECTION_STRING");
            String DB_User = System.getProperty("JDBC_USER");
            String DB_Password = System.getProperty("JDBC_PASSWORD");
            String DB_Connection_String = DB_Url + "?user=" + DB_User + "&password=" + DB_Password;

            conn = DriverManager.getConnection(DB_Connection_String);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT id, user_name, user_passwordr FROM users WHERE user_name = \"" +
                    sUserName + "\" AND user_passwordr = \"" + sUserPassword + "\"";

            System.out.println(sql);

            ResultSet rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            if(rs.next()){
                validLoginResult = true;
                int id  = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");

                //Display values
                System.out.print("ID: " + id);
                System.out.print(", Username: " + username);
                System.out.println(", Password: " + password);
            }
            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Closing DB Connection - Goodbye!");
        return validLoginResult;
    }
}
