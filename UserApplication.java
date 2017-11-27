/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jlartey10
 */

import java.awt.Color;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UserApplication {

    public static void main(String[] args) throws IOException {
        final int MAX = 20;
        int count = 0;
        int size = 0;
        
        
        
        Hashtable<String, User> all = new Hashtable<String, User>();        
        User[] newUser = new User[MAX];
        

        
        /**
         * Input & Output text file to write Student information into
         */
        String outputPath = "src/out.txt";

        while (JOptionPane.showConfirmDialog(null, "Do you want to Create user?") == JOptionPane.YES_OPTION) {
            
            
            //Add user information to user array
            newUser[count] = getInput();

            //Write information into text file
            filewriter(outputPath, newUser);
            //put information into Hash Table
            all.put(newUser[count].getUsername(), newUser[count]);
            //count of uers increments
            count++;

           
        }
        //display all information in Hash Table
        System.out.println("Printing the content...");
        displayAll(all);

        
        //Search for username and password match
        System.out.println("\n\nSearching for username & password....");
        
        //login(all, newUser);
        boolean match = false;
        do{
            
            match = search_User_Password(all, newUser);
            
            if(!match){
                    JOptionPane.showMessageDialog(null, "Username or Password is Invalid ");
            }
        }while(!match);
        
//        System.out.println("\n\nRemoving the content....");
//        remove(all);
//        displayAll(all);

    }



    
    
    /**
    * Method to get add user information into user class
    */    
public static User getInput() {
        User user = new User();
        //User Name
        String username = "";
        do {
            username = JOptionPane.showInputDialog("Enter a User Name: ");
         
        } while (!user.setUsername(username));

        //First Name
        String firstName = "";
        do {
            firstName = JOptionPane.showInputDialog("Enter a First Name: ");
        } while (!user.setFirstName(firstName));

        //Last Name
        String lastName = "";
        do {
            lastName = JOptionPane.showInputDialog("Enter a Last Name: ");
        } while (!user.setLastName(lastName));

        //Password
        String password = "";
        do {
            password = JOptionPane.showInputDialog("Enter a Password (Greater than 8 characters): ");
        } while (!user.setPassword(password));

        //Employment Type
        int type = 0;
        do {
            type = Integer.parseInt(JOptionPane.showInputDialog("Select Employment Type "
                    + "\n\n 1 - Administrator "
                    + "\n\n 2 - Employee "));
        } while (!user.setType(type));

        
        return user;
    }    




    /**
    * Method to write user information into text file
     * @param outputPath
     * @param newUser
     * @throws java.io.IOException
    */  
    public static void filewriter(String outputPath, User[] newUser) throws IOException {

        //writing into text file 
        FileWriter fw = null;

        fw = new FileWriter(new File(outputPath));
        BufferedWriter bw = new BufferedWriter(fw);

        String writableString = "";
        
        for (int x = 0; x < newUser.length; x++) {
            if (newUser[x] != null) {
                writableString +=   newUser[x].getUserid() + ":" 
                                    + newUser[x].getUsername() + ":" 
                                    + newUser[x].getFirstName() + " " + newUser[x].getLastName() + ":"
                                    + newUser[x].getPassword() + ":"
                                    + newUser[x].getType() + "\n";
                
            }
        }

        //System.out.println(writableString);
        bw.write(writableString);
        bw.close();
    }
    
    
    /**
    * Method to search and verify username and password
     * @param all
     * @param newUser
    */   
    public static boolean search_User_Password(Hashtable<String, User> all, User[] newUser ){
        
        boolean isValid = false;
        
        String username = JOptionPane.showInputDialog("--------Login---------- \n\n\n" +"Enter Employee username: ");
        String password = JOptionPane.showInputDialog("--------Login---------- \n\n\n" +"Enter Employee password: ");
        if(all.containsKey(username)){
            //newUser = all.get(username);  //retrieving student from hashmap
            System.out.println("Found username ");
            //System.out.println("Length of array:"+newUser.length);
            for (int x = 0; x < User.getTotal(); x++) {
                if(newUser[x].getPassword().equals(password)){
                    System.out.println("Found password ");
                    
                    isValid = true;
                    
                    //Enter system after login
                    WalmartSystem system = new WalmartSystem();
                    system.run();
                    //break;
                }
                else {
                    
                System.out.println("Password Not Found ");
            }

        }
            
          
        }
        else{
            System.out.println("username Not Found ");
        }
        
        return isValid;
    }
    
    
    
    

    public static void displayAll(Map<String, User> all) {
        Iterator it = all.entrySet().iterator();
        while (it.hasNext()) {
            System.out.println(((Entry<String, User>) it.next()).getValue().toString() + "\n");
        }
    }

//    public static void remove(Map<String, User> all) {
//        String key = JOptionPane.showInputDialog("Enter a username to remove:");
//        if (all.containsKey(key)) {
//            all.remove(key);
//        }
//    }

//    /**
//     * Method to reads student information from text files and sets them to user class
//     */
    public static User readUser(String outputPath) {
        File file = new File(outputPath);
        int sum = 0;
        String nextLine;
        User user = null;
        String username;
        int id;
        String name;
        //String lastName;
        String password;
        int type;

        Scanner scanRow;
        String print = "------- Reading the student records from a file ------- \n\n";
        //User u = null;

        //This is where we read lines
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            try {
                while ((nextLine = br.readLine()) != null) {
                    
                    user = new User();
                    
                    scanRow = new Scanner(nextLine);
                    scanRow.useDelimiter(":");
                    
                    id = Integer.parseInt(scanRow.next().trim());
                    user.setUserid(id);
                    
                    username = scanRow.next().trim();
                    user.setUsername(username);
                    password = scanRow.next().trim();
                    user.setPassword(password);
                    type = Integer.parseInt(scanRow.next().trim());
                    user.setType(type);
                    sum++;
                }
                br.close();
            } catch (IOException ioex) {
                System.out.println(ioex.getMessage() + "Error reading files");
            }
        } catch (FileNotFoundException fnfex) {
            System.out.println(fnfex.getMessage() + "The file was not found");
            System.exit(0);
        }

        return user;
    }

}