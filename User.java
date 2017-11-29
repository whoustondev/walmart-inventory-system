import java.io.IOException;
import java.util.Hashtable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Jlartey10
 */
public class User {

    private String username;
    private int userid;
    private String firstName;
    private String lastName;
    private String password;
    private String type;
    private static int total;

    public User() 
    {
        total++;
        this.userid = total;
    }

    public User(String username, String firstName, String lastName, String password, String type) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.type = type;
        total++;
        this.userid = total;

    }

    public String getUsername() {
        return username;
    }

    public boolean setUsername(String username) 
    {
        this.username = username;
        //return true;
        for (int i = 0; i < username.length(); i++) 
        {
            if (!Character.isLetterOrDigit(username.charAt(i))) 
            {
                return false;
            }
        }
        this.username = username;
        return true;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        //this.userid = userid;
        this.userid =total;
    }

    public String getFirstName() {
        return firstName;
    }

    public boolean setFirstName(String firstName) {
        //this.firstName = firstName;
        //return true;

        for (int i = 0; i < firstName.length(); i++) {
            if (!Character.isLetter(firstName.charAt(i))) {
                return false;
            }
        }
        this.firstName = firstName;
        return true;
    }

    public String getLastName() {
        
        return lastName;
    }

    public boolean setLastName(String lastName) {
        //this.lastName = lastName;
        //return true;

        for (int i = 0; i < lastName.length(); i++) {
            if (!Character.isLetter(lastName.charAt(i))) {
                return false;
            }
        }
        this.lastName = lastName;
        return true;

    }

    public String getPassword() {
        return password;
    }

    public boolean setPassword(String password) {
        if (password.length() < 8) {
            return false;
        }
        for (int i = 0; i < password.length(); i++) {
            if (!Character.isLetterOrDigit(password.charAt(i))) {
                return false;
            }
        }
        this.password = password;
        return true;
    }

    public String getType() {
        return type;
    }

    public boolean setType(int type) {
        boolean isValid = false;
        try{
        if (type == 1) {
            this.type = "Administrator";
            isValid = true;
        } else if (type == 2) {
            this.type = "Employee";
            isValid = true;
        }
        
        }catch(NumberFormatException e){isValid = false;}
        

        return isValid;
    }

    public static int getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return "Username : " + this.getUsername()
                + "\nUserid : " + this.getUserid()
                + "\nFirst Name : " + this.getFirstName()
                + "\nLast Name :  " + this.getLastName()
                + "\nType : " + this.getType();
    }

}