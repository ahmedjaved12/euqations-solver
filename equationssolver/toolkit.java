package equationssolver;

// useful imports
import Jama.Matrix;
import java.sql.*;  
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

// toolkit class to provide tools as functions to provide various tasks
public class toolkit {
    
    /*
    function to get connection with database
    parameters : none
    return type : Connection con
    */
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        
        Class.forName("com.mysql.cj.jdbc.Driver");  
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/equationssolver","root","12345");  
        
        return con;
    }
    
    /*
    function to extract varibales from equation with 3 variables 
    parameters : equation as string
    return type : double[] result(result[0] = x, result[1] = y, result[2] = z, result[3] = constant)
    */
    public static double[] extractInputThreeVariable(String eq){
        
        double[] result = new double[4];
        
        result[0] = Double.parseDouble(eq.substring(0, eq.indexOf("x")));
        result[1] = Double.parseDouble(eq.substring(eq.indexOf("x")+1, eq.indexOf("y")));
        result[2] = Double.parseDouble(eq.substring(eq.indexOf("y")+1, eq.indexOf("z")));
        result[3] = Double.parseDouble(eq.substring(eq.indexOf("=")+1, eq.length()));
        
        return result;
    }
    
    /*
    function to extract varibales from equation with 2 variables 
    parameters : equation as string
    return type : double[] result(result[0] = x, result[1] = y, result[3] = constant)
    */
    public static double[] extractInputTwoVariable(String eq){
        
        double[] result = new double[3];
        
        result[0] = Double.parseDouble(eq.substring(0, eq.indexOf("x")));
        result[1] = Double.parseDouble(eq.substring(eq.indexOf("x")+1, eq.indexOf("y")));
        result[2] = Double.parseDouble(eq.substring(eq.indexOf("=")+1, eq.length()));
        
        return result;
    }
    
    /*
    function to solve system of equations with 2 variables 
    parameters : equation1 as string, equation2 as string
    return type : double[] result(result[0] = x, result[1] = y)
    */
    public static double[] solveEquation(String eq1, String eq2){
        
        double[] equation1 = extractInputTwoVariable(eq1);
        double[] equation2 = extractInputTwoVariable(eq2);
        
        double[][] lhsArray = {{equation1[0], equation1[1]}, {equation2[0], equation2[1]}};
        double[] rhsArray = {equation1[2], equation2[2]};
        
        Matrix lhs = new Matrix(lhsArray);
        Matrix rhs = new Matrix(rhsArray, 2);
        
        Matrix ans = lhs.solve(rhs);
        
        double[] result = {ans.get(0 , 0), ans.get(1 , 0)};
        
        return result;
    }
    
    /*
    function to solve system of equations with 3 variables 
    parameters : equation1 as string, equation2 as string, equation3 as string
    return type : double[] result(result[0] = x, result[1] = y, result[2] = z)
    */
    public static double[] solveEquation(String eq1, String eq2, String eq3){
        
        double[] equation1 = extractInputThreeVariable(eq1);
        double[] equation2 = extractInputThreeVariable(eq2);
        double[] equation3 = extractInputThreeVariable(eq3);
        
        double[][] lhsArray = {{equation1[0], equation1[1], equation1[2]}, {equation2[0], equation2[1], equation2[2]}, {equation3[0], equation3[1], equation3[2]}};
        double[] rhsArray = {equation1[3], equation2[3], equation3[3]};
        
        Matrix lhs = new Matrix(lhsArray);
        Matrix rhs = new Matrix(rhsArray, 3);
        
        Matrix ans = lhs.solve(rhs);
        
        double[] result = {ans.get(0 , 0), ans.get(1 , 0), ans.get(2, 0)};
        
        return result;
    }
    
    /*
    function to check if solution exists for system of equations with 2 variables
    parameters : equation1 as string, equation2 as string
    return type : boolean (true if solution exists otherwise false)
    */
    public static boolean solutionExists(String eq1, String eq2){
        try{
            solveEquation(eq1, eq2);
            return true;
        }
        catch(Exception e){
            System.out.println(e);
            return false;
        }
        
    }
    
    /*
    function to check if solution exists for system of equations with 3 variables
    parameters : equation1 as string, equation2 as string, equation3 as string
    return type : boolean (true if solution exists otherwise false)
    */
    public static boolean solutionExists(String eq1, String eq2, String eq3){
        
        try{
            solveEquation(eq1, eq2, eq3);
            return true;
        }
        catch(Exception e){
            System.out.println(e);
            return false;
        }
        
    }
    
    /*
    function to save results in database for system of equations with 2 variables
    parameters : equation1 as string, equation2 as string, result as double[], solvedby as string)
    return type : void
    */
    public static void saveResults(String eq1, String eq2, double[] result, String solvedBy){
        
        String d;
        int rowsAffected;
        Date date = Calendar.getInstance().getTime();  
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
        d = dateFormat.format(date);
        
        String query = "insert into result2 (eq1,eq2,resultx,resulty,datesolved,solvedby) values";
        query = query + "(\'"+eq1+"\',\'"+eq2+"\',"+result[0]+","+result[1]+",\'"+d+"\',\'"+solvedBy+"\')";
        try {
            Connection con = toolkit.getConnection();
            PreparedStatement createPlayer = con.prepareStatement(query);
            rowsAffected = createPlayer.executeUpdate(); 
            
            if(rowsAffected > 0){
                System.out.println("Result saved.");
            }else{
                System.out.println("There was error saving result.");
            }
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(user.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    /*
    function to save results in database for system of equations with 3 variables
    parameters : equation1 as string, equation2 as string, equtaion3 as string, result as double[], solvedby as string)
    return type : void
    */
     public static void saveResults(String eq1, String eq2, String eq3, double[] result, String solvedBy){
        
        String d;
        int rowsAffected;
        Date date = Calendar.getInstance().getTime();  
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
        d = dateFormat.format(date);
        
        String query = "insert into result3 (eq1,eq2,eq3,resultx,resulty,resultz,datesolved,solvedby) values";
        query = query + "(\'"+eq1+"\',\'"+eq2+"\',\'"+eq3+"\',"+result[0]+","+result[1]+","+result[2]+",\'"+d+"\',\'"+solvedBy+"\')";
        try {
            Connection con = toolkit.getConnection();
            PreparedStatement createPlayer = con.prepareStatement(query);
            rowsAffected = createPlayer.executeUpdate(); 
            
            if(rowsAffected > 0){
                System.out.println("Result saved.");
            }else{
                System.out.println("There was error saving result.");
            }
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(user.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
    

    