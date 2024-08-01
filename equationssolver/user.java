package equationssolver;

// useful imorts
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.sql.*; 
import java.util.logging.Level;
import java.util.logging.Logger;

// user class to hold user data and user related functions
public class user {
    
    // attributes
    private String username;
    private String pass;
    private String fullName;
    private int age;
    private String accountCreationDate;
    
    // only constructor
    public user(String username, String pass, String fullName, int age){
        this.username = username;
        this.pass = pass;
        this.fullName = fullName;
        this.age = age;
        Date date = Calendar.getInstance().getTime();  
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
        this.accountCreationDate = dateFormat.format(date);
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
    
    // getter for age
    public int getAge(){
        return this.age;
    }
    
    // getter for account creation date
    public String getAccountCreationDate(){
        return this.accountCreationDate;
    }
    
    /*
    function for login validation
    parameters : username and password as string
    return type : int (1 if a valid login otherwise 0)
    */
    public static int validateLogin(String username, String pass){
        
        String query = "select username, pass from users where username = \'"+username+"\'";
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
    function to check if username already exists in database
    parameters : username as string
    return type : boolean (true if username already exists otherwise false)
    */
    public static boolean usernameExists(String username){
        
        String query = "select username, pass from users where username = \'"+username+"\'";
        try {
            Connection con = toolkit.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query); 
            
            if(rs.next()){
                return true;
            }else{
                return false;
            }
        
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(user.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return true;
    }
    
    /*
    function to modify user profile
    parameters : new password as string, new full name as string, new age as int, username as string
    return type : boolean (true if profile updated successfully otherwise flase)
    */
    public static boolean modifyProfile(String pass, String fullname, int age, String username){
        
        String query = "update users set pass = \'"+pass+"\',fullname = \'"+fullname+"\',age = "+age+" where username = \'"+username+"\'";
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
    function to add user to database
    parameters : user as user (user to be added)
    return type : void
    */
    public static void addUser(user u){
        
        String query = "insert into users () values";
        query = query + "(\'"+u.getUsername()+"\',\'"+u.getPass()+"\',\'"+u.getFullName()+"\',"+u.getAge()+",\'"+u.getAccountCreationDate()+"\')";
        
        try {
            Connection con = toolkit.getConnection();
            PreparedStatement createPlayer = con.prepareStatement(query);
            createPlayer.executeUpdate();
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(user.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
