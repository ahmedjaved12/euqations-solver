package equationssolver;

// useful imorts
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

// user class to hold admin data and admin related functions
public class admin {
    
    // attributes
    private String username;
    private String pass;
    private String fullName;
    
    // only constructor
    public admin(String username, String pass, String fullName){
        this.username = username;
        this.pass = pass;
        this.fullName = fullName;
    }
    
    // getter for username
    public String getUsername(){
        return this.username;
    }
    
    // getter for password
    public String getPass(){
        return this.pass;
    }
    
    // getter for full name
    public String getFullName(){
        return this.fullName;
    }
    
    /*
    function for login validation of admin
    parameters : admin username and password as string
    return type : int (1 if a valid login otherwise 0)
    */
    public static int validateLogin(String username, String pass){
        
        String query = "select username, pass from admins where username = \'"+username+"\'";
        try {
            Connection con = toolkit.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query); 
            
            if(rs.next() && rs.getString("pass").equals(pass)){
                return 1;
            }else{
                return 0;
            }
        
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(user.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return 0;
    }
    
    /*
    function to modify admin profile
    parameters : new full name as string, new password as string, username as string
    return type : boolean (true if profile updated successfully otherwise flase)
    */
    public static boolean modifyProfile(String fullname, String pass, String username){
        
        String query = "update admins set pass = \'"+pass+"\',fullname = \'"+fullname+"\' where username = \'"+username+"\'";
        int rowsAffected;
        
        try {
            Connection con = toolkit.getConnection();
            PreparedStatement createPlayer = con.prepareStatement(query);
            rowsAffected = createPlayer.executeUpdate();
            
            if(rowsAffected > 0){
                return true;
            }else{
                return false;
            }
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(user.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    /*
    function to print all users
    parameters : none
    return type : void
    */
    public static void printAllUsers(){
        
        String query = "select * from users";
        try {
            Connection con = toolkit.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query); 
            
            System.out.printf("| %-20s | %-15s | %10s | %-15s | %-20s |%n", "FULL NAME", "USERNAME", "AGE", "PASSWORD", "DATE JOINED");
            while(rs.next()){
                System.out.printf("| %-20s | %-15s | %10s | %-15s | %-20s |%n", rs.getString("fullname"), rs.getString("username"),  rs.getInt("age"), rs.getString("pass"), rs.getString("datecreated"));
            }
            
        
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(user.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    /*
    function to print all users
    parameters : none
    return type : void
    */    
    public static boolean deleteUser(String username){
        
        String query = "delete from users where username = \'"+username+"\'";
        int rowsAffected;
        
        try {
            Connection con = toolkit.getConnection();
            PreparedStatement createPlayer = con.prepareStatement(query);
            rowsAffected = createPlayer.executeUpdate();
            
            if(rowsAffected > 0){
                return true;
            }else{
                return false;
            }
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(user.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    /*
    function to print operations performed by specific user
    parameters : username as string
    return type : void
    */
    public static void listOperationsByUser(String username){
        
        String query = "select * from result2 where solvedby = \'"+username+"\'";
        String query2 = "select * from result3 where solvedby = \'"+username+"\'";
        try {
            
            Connection con = toolkit.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            boolean foundResult2 = false;
            boolean foundResult3 = false;
            
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("| %-25s | %-25s | %25s | %-25s | %-20s | %-20S |%n", "EQUATION 1", "EQUATION 2", "RESULT X", "RESULT Y", "DATE SOLVED", "SOLVED BY");
            while(rs.next()){
                foundResult2 = true;
                System.out.printf("| %-25s | %-25s | %25s | %-25s | %-20s | %-20S |%n", rs.getString("eq1"), rs.getString("eq2"),  rs.getDouble("resultx"), rs.getDouble("resulty"), rs.getString("datesolved"), rs.getString("solvedby"));
            }
            
            if(!foundResult2){
                System.out.println("No results found for syatem of equations with two variables.");
            }
            
            
            Connection con2 = toolkit.getConnection();
            Statement st2 = con2.createStatement();
            ResultSet rs2 = st2.executeQuery(query2);
            
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("| %-25s | %-25s | %-25s | %25s |%25s | %-25s | %-20s | %-20S |%n", "EQUATION 1", "EQUATION 2", "EQUATION3", "RESULT X", "RESULT Y", "RESULT Z", "DATE SOLVED", "SOLVED BY");
            while(rs2.next()){
                foundResult3 = true;
                System.out.printf("| %-25s | %-25s | %-25s | %25s |%25s | %-25s | %-20s | %-20S |%n", rs2.getString("eq1"), rs2.getString("eq2"), rs2.getString("eq3"),  rs2.getDouble("resultx"), rs2.getDouble("resulty"), rs2.getDouble("resultz"), rs2.getString("datesolved"), rs2.getString("solvedby"));
            }
            
            if(!foundResult3){
                System.out.println("No results found for syatem of equations with two variables.");
            }
            
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(user.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    /*
    function to print all operations
    parameters : none
    return type : void
    */
    public static void listAllOperations(){
        
        String query = "select * from result2";
        String query2 = "select * from result3";
        try {
            Connection con = toolkit.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            boolean foundResult2 = false;
            boolean foundResult3 = false;
            
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("| %-25s | %-25s | %25s | %-25s | %-20s | %-20S |%n", "EQUATION 1", "EQUATION 2", "RESULT X", "RESULT Y", "DATE SOLVED", "SOLVED BY");
            while(rs.next()){
                foundResult2 = true;
                System.out.printf("| %-25s | %-25s | %25s | %-25s | %-20s | %-20S |%n", rs.getString("eq1"), rs.getString("eq2"),  rs.getDouble("resultx"), rs.getDouble("resulty"), rs.getString("datesolved"), rs.getString("solvedby"));
            }
            
            if(!foundResult2){
                System.out.println("No results found for syatem of equations with two variables.");
            }
            
            
            Connection con2 = toolkit.getConnection();
            Statement st2 = con2.createStatement();
            ResultSet rs2 = st2.executeQuery(query2);
            
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("| %-25s | %-25s | %-25s | %25s |%25s | %-25s | %-20s | %-20S |%n", "EQUATION 1", "EQUATION 2", "EQUATION3", "RESULT X", "RESULT Y", "RESULT Z", "DATE SOLVED", "SOLVED BY");
            while(rs2.next()){
                foundResult3 = true;
                System.out.printf("| %-25s | %-25s | %-25s | %25s |%25s | %-25s | %-20s | %-20S |%n", rs2.getString("eq1"), rs2.getString("eq2"), rs2.getString("eq3"),  rs2.getDouble("resultx"), rs2.getDouble("resulty"), rs2.getDouble("resultz"), rs2.getString("datesolved"), rs2.getString("solvedby"));
            }
            
            if(!foundResult3){
                System.out.println("No results found for syatem of equations with two variables.");
            }
        
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(user.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
