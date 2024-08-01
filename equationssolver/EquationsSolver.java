package equationssolver;

// useful imports
import java.util.Scanner;

// main class
public class EquationsSolver {

    // main function
    public static void main(String[] args) {
        
        // variable declaration to hold input
        String username;
        String pass;
        int age;
        String fullName;
        String currentUser;
        String eq1, eq2, eq3;
        double[] result = new double[3];
        
        Scanner in = new Scanner(System.in);
        
        int option;
        char opt;
        System.out.println("EQUATIONS SOLVER APP");
        
        // Main menu infinite loop
        mainMenuLoop:
        while(true){
            
            option = printMainMenu();
            
            // main menu switch
            switch(option){
                
                // main menu case 1
                case 1 -> {
                    System.out.println("----------------------------------------");
                    System.out.println("Enter user name: ");
                    username = in.nextLine();
                    System.out.println("Enter password:");
                    pass = in.nextLine();
                    
                    option = user.validateLogin(username, pass);
                    
                    if(option == 0){
                        System.out.println("----------------------------------------");
                        System.out.println("Invalid username or password. Try again.");
                    }else{
                        System.out.println("----------------------------------------");
                        System.out.println("Successfully logged in.");
                        currentUser = username;
                        
                        // user menu infinite loop
                        while(true){
                            
                            option = printUserMenu();
                            
                            switch(option){
                                
                                case 1:
                                    System.out.println("----------------------------------------");
                                    System.out.println("Enter new password:");
                                    pass = in.nextLine();
                                    System.out.println("Enter new full name:");
                                    fullName = in.nextLine();
                                    System.out.println("Enter new age:");
                                    age = in.nextInt();
                                    in.nextLine();
                                    
                                    if(user.modifyProfile(pass, fullName, age, currentUser)){
                                        System.out.println("----------------------------------------");
                                        System.out.println("Profile updated successfully."); 
                                    }else{
                                        System.out.println("----------------------------------------");
                                        System.out.println("There was an error updating your profile. Try again."); 
                                    }
                                    
                                    break;
                                 
                                case 2:
                                    System.out.println("----------------------------------------");
                                    System.out.println("Input format:");
                                    System.out.println("*Equation should not contain any white spaces.");
                                    System.out.println("*Equation should not contain any brackets.");
                                    System.out.println("*Constant should always be on RHS of equation.");
                                    System.out.println("*Varible should always have constant with it(1x instead of x).");
                                    System.out.println("*Always use x, y for variables.");
                                    System.out.println("*Examples: 1x+3y=-2, 2x-3y=7");
                                    System.out.println("Enter equation 1:");
                                    eq1 = in.nextLine();
                                    System.out.println("Enter equation 2:");
                                    eq2 = in.nextLine();
                                    
                                    if(toolkit.solutionExists(eq1, eq2)){
                                        result = toolkit.solveEquation(eq1, eq2);
                                        System.out.println("----------------------------------------");
                                        System.out.println("Solution of equation:");
                                        System.out.println("x = "+result[0]);
                                        System.out.println("y = "+result[1]);
                                        System.out.println("Save results?(y/n)");
                                        opt = in.nextLine().charAt(0);
                                        
                                        if(opt == 'y' || opt == 'Y'){
                                            toolkit.saveResults(eq1, eq2, result, currentUser);
                                        }
                                        
                                    }else{
                                        System.out.println("----------------------------------------");
                                        System.out.println("Solution does not exists.");
                                    }
                                    
                                    break;
                                
                                case 3:
                                    System.out.println("----------------------------------------");
                                    System.out.println("Input format:");
                                    System.out.println("*Equation should not contain any white spaces.");
                                    System.out.println("*Equation should not contain any brackets.");
                                    System.out.println("*Constant should always be on RHS of equation.");
                                    System.out.println("*Varible should always have constant with it(1x instead of x).");
                                    System.out.println("*Always use x, y, z for variables.");
                                    System.out.println("*Examples: 1x+3y-3z=-5, 8x-4y-1z=-16");
                                    System.out.println("Enter equation 1:");
                                    eq1 = in.nextLine();
                                    System.out.println("Enter equation 2:");
                                    eq2 = in.nextLine();
                                    System.out.println("Enter equation 3:");
                                    eq3 = in.nextLine();
                                    
                                    if(toolkit.solutionExists(eq1, eq2, eq3)){
                                        result = toolkit.solveEquation(eq1, eq2, eq3);
                                        System.out.println("----------------------------------------");
                                        System.out.println("Solution of equation:");
                                        System.out.println("x = "+result[0]);
                                        System.out.println("y = "+result[1]);
                                        System.out.println("z = "+result[2]);
                                        System.out.println("Save results?(y/n)");
                                        opt = in.nextLine().charAt(0);
                                        
                                        if(opt == 'y' || opt == 'Y'){
                                            toolkit.saveResults(eq1, eq2, eq3, result, currentUser);
                                        }
                                        
                                    }else{
                                        System.out.println("----------------------------------------");
                                        System.out.println("Solution does not exists.");
                                    }
                                    
                                    break;
                                
                                case 4:
                                    continue mainMenuLoop;
                                case 5:
                                    System.exit(0);
                                default:
                                    System.out.println("----------------------------------------");
                                    System.out.println("Invalid Entry. Please try again.");
                            }
                        }
                        // user menu infinite loop ends here
                    }
                    // user menu else block ends here
                }
                // main menu case 1 ends here
                
                // main menu case 2
                case 2 -> {
                    System.out.println("----------------------------------------");
                    System.out.println("---SIGN UP---");
                    System.out.println("Enter username: ");
                    username = in.nextLine();
                    if(user.usernameExists(username)){
                       System.out.println("----------------------------------------");
                        System.out.println("Username already exists. Try again.");
                        continue;
                    }
                    System.out.println("Enter Full name:");
                    fullName = in.nextLine();
                    System.out.println("Enter age:");
                    age = in.nextInt();
                    in.nextLine();
                    System.out.println("Enter password:");
                    pass = in.nextLine();
                    user u = new user(username, pass, fullName, age);
                    user.addUser(u);
                    System.out.println("----------------------------------------");
                    System.out.println("Account created successfully.");
                    continue;
                }
                // main menu case 2 ends here
                
                // main menu case 3 
                case 3 -> {
                    System.out.println("----------------------------------------");
                    System.out.println("Enter user name: ");
                    username = in.nextLine();
                    System.out.println("Enter password:");
                    pass = in.nextLine();
                    
                    option = admin.validateLogin(username, pass);
                    
                    if(option == 0){
                        System.out.println("----------------------------------------");
                        System.out.println("Invalid username or password. Try again.");
                    }else{
                        System.out.println("----------------------------------------");
                        System.out.println("Successfully logged in.");
                        currentUser = username;
                        
                        while(true){
                            
                            option = printAdminMenu();
                            
                            switch(option){
                                
                                case 1:
                                    System.out.println("----------------------------------------");
                                    System.out.println("Enter full name:");
                                    fullName = in.nextLine();
                                    System.out.println("Enter password:");
                                    pass = in.nextLine();
                                    
                                    if(admin.modifyProfile(fullName, pass, currentUser)){
                                        System.out.println("----------------------------------------");
                                        System.out.println("Profile updated successfully."); 
                                    }else{
                                        System.out.println("----------------------------------------");
                                        System.out.println("There was an error updating your profile. Try again."); 
                                    }
                                    
                                    break;
                                    
                                case 2:
                                    System.out.println("----------------------------------------");
                                    admin.printAllUsers();
                                    break;
                                
                                case 3:
                                    System.out.println("----------------------------------------");
                                    System.out.println("Enter username");
                                    username = in.nextLine();
                                    if(admin.deleteUser(username)){
                                        System.out.println("----------------------------------------");
                                        System.out.println("Account deleted successfully.");
                                    }else{
                                        System.out.println("----------------------------------------");
                                        System.out.println("There was a problem deleting the acount.");
                                    }
                                    break;
                                case 4:
                                    System.out.println("----------------------------------------");
                                    System.out.println("Enter username");
                                    username = in.nextLine();
                                    admin.listOperationsByUser(username);
                                    break;
                                case 5:
                                    admin.listAllOperations();
                                    break;
                                case 6:
                                    continue mainMenuLoop;
                                case 7:
                                    System.exit(0);
                                default:
                                    System.out.println("----------------------------------------");
                                    System.out.println("Invalid Entry. Please try again.");
                            }
                            // admin menu switch ends here
                        }
                        // admin menu infinite loop ends here
                    }   
                    // admin menu else block ends here   
                }
                // main menu case 3 ends here
                
                // main menu case 4
                case 4 -> {
                    System.exit(0);
                }
                
                // default case
                default -> {
                    System.out.println("----------------------------------------");
                    System.out.println("Invalid Entry. Please try again.");
                }
            }
            // main menu switch ends here
        }
        // main menu infinite loop ends here3
    }
    
    /*
    function to print main menu
    parameters : none
    return type: int (option that user choosed)
    */
    public static int printMainMenu(){
        
        int option;
        
        System.out.println("----------------------------------------");
        System.out.println("----MAIN MENU---");
        System.out.println("Please select one of the following options.");
        System.out.println("1. Log in ");
        System.out.println("2. Sign Up");
        System.out.println("3. Login as admin.");
        System.out.println("4. Exit");
        System.out.println("Enter your choice>");
        
        Scanner in = new Scanner(System.in);
        option = in.nextInt();
        
        return option;
    }
    
    /*
    function to print user menu
    parameters : none
    return type: int (option that user choosed)
    */
    public static int printUserMenu(){
        
        int option;
        
        System.out.println("---USER MENU---");
        System.out.println("Please select one of the following options.");
        System.out.println("1. Modify profile.");
        System.out.println("2. Solve system of linear equations of two variables.");
        System.out.println("3. Solve system of linear equations of three variables.");
        System.out.println("4. Go back to main menu.");
        System.out.println("5. Exit");
        System.out.println("Enter your choice>");
        
        Scanner in = new Scanner(System.in);
        option = in.nextInt();
        
        return option;
    }
    
     /*
    function to print admin menu
    parameters : none
    return type: int (option that user choosed)
    */
    public static int printAdminMenu(){
        
        int option;
        
        System.out.println("---ADMIN MENU---");
        System.out.println("Please select one of the following options.");
        System.out.println("1. Modify profile.");
        System.out.println("2. List all users.");
        System.out.println("3. Remove user.");
        System.out.println("4. List operations performed by a specific user.");
        System.out.println("5. List all operations.");
        System.out.println("6. Go back to main menu.");
        System.out.println("7. Exit");
        System.out.println("Enter your choice>");
        
        Scanner in = new Scanner(System.in);
        option = in.nextInt();
        
        return option;
    }
}
