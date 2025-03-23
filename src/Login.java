
import java.util.*;

import java.io.*;
import javax.swing.*;
import javax.*;
import java.awt.*;



/**
 * @author whouston

 */
public class Login
{
	
	/**
	 * This static method, LoginScreen can be used anywhere. All it takes is a HashMap of users to verify against
	 * When using anything from this class, use LoginScreen if you want 
	 * 
	 * It will return the username of the verified user. Otherwise, it won't return anything. 
	 * A user will not be able to get past this loop without proper credentials. 
	 * @param map
	 * @return
	 */
	public static String loginScreen(HashMap map)
	
	{
		boolean displayWrongInputMessage = false;
		boolean isAdmin = false;
		String returnTheUserName = "";
		boolean validUser = false;
		while(validUser == false)
		{
			Object[] options2 = { "Login", "Cancel", };
			String[] info = new String[2];
		    	JPanel panel = new JPanel();
		    	panel.setBackground(Color.lightGray);
		    	JTextField username = new JTextField(10);
		    	JTextField password = new JTextField(10);
		    	
		    	 // a spacer
		    	panel.add(new Label("Username:"));
		    	panel.add(username);
		    	panel.add(Box.createVerticalStrut(20));
		    	panel.add(new Label("Password:"));
	    		panel.add(password);
	    		if(displayWrongInputMessage == true)
	    		{
	    			panel.add(new Label("Wrong input. "));
	    			
	    		}
		    	int result = JOptionPane.showOptionDialog(null, panel, "Login Page", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options2, null);

		    
		    info[0] = username.getText();
		    	info[1] = password.getText();
		    	if(result == 0)
		    	{
		    		validUser = validateLogin(map, info[0], info[1]);
		    		returnTheUserName = info[0];
		    	}
		    	else
		    	{
		    		System.exit(0);
		    		
		    	}
		    	displayWrongInputMessage = true;
			
		}
		if(validUser == true)
			return returnTheUserName;
		
		return null;
		
	}
        	
	/**
	 * This function can be used standalone or in conjunction with loginScreen. 
	 * It returns a boolean. True if a valid login, false if it isn't 
	 * @param map
	 * @param username
	 * @param password
	 * @return
	 */
	public static boolean validateLogin(HashMap map, String username, String password)
	{
		try
		{
			//System.out.println(map.get("whouston"));
			if((((User)map.get(username)).getPassword()).equals(password)) //check if passwords match
			{
				System.out.println("validated a user as true");
				return true;
				
			}
		}
		catch(Exception a)
		{
			a.printStackTrace();
			
		}
		
		return false;
	}
	
	
	/**
	 * returns a hashmap full of users given a filepath
	 * This function is to be used with a database.
	 * It returns all the users in hashmap form. 
	 * 
	 * Give it a filepath and we will return a 
	 * **/
	public static HashMap getUsers(String filepath)
	{
		HashMap<String, User> map = new HashMap<String, User>();
        try 
        {
        	System.out.println("we are reading");
            BufferedReader br = new BufferedReader(new FileReader(filepath));
            String line = "";
            while((line = br.readLine()) != null)
            {
            		String [] tokens = line.split(";");
            		if(tokens.length == 5)
            		{	System.out.println(tokens[0]);
            			map.put(tokens[0].trim(), new User(tokens[0].trim(), tokens[1].trim(), tokens[2].trim(), tokens[3].trim(), tokens[4].trim()));  			
            		}

            }
        	}
        catch(IOException e)
        {
        		e.printStackTrace();  	
        }
        catch(Exception f)
        {
        		f.printStackTrace();
        }
        System.out.println("this map is this big"+map.size());
        return map;
	}
/**
 * What this function does is take a filepath and a HashMap.
 * It will attempt to add a new User. If the same user already exists in the hashmap,it will return null.
 * 
 * This function either returns an updated hashmap of users (plus the new one )
 * OR, it returns null so that the main program can do something about it. 
 * 
 * @param filepath
 * @param users
 * @return
 * @throws IOException
 */
	public static HashMap addNewUserPanel(String filepath, HashMap users) throws IOException
	{
		JPanel panel = new JPanel();

		Object[] options = {"Exit", "Add New User"};

			panel.setLayout(new GridLayout(8,1));
			JTextField username = new JTextField(10);
			panel.add(new Label("Username: "));
			panel.add(username);
			JTextField password = new JTextField(10);
			panel.add(new Label("Password: "));
			panel.add(password);
			
			JTextField firstName = new JTextField(10);
			panel.add(new Label("Firstname: "));
			panel.add(firstName);			
			
			JTextField lastName = new JTextField(10);
			panel.add(new Label("Last Name:"));
			panel.add(lastName);
			
			JTextField typeOfAccount = new JTextField(15);
			panel.add(new Label("Administrator or User: "));
			panel.add(typeOfAccount);		
			
		
			int result = JOptionPane.showOptionDialog(null, panel, "Add New Item", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);			
			
			//Validation checks
			if(password.getText().trim().length()<8) // Make sure password is long enough
				{
					return null;	
				}
			else if((typeOfAccount.getText().trim().toString().equals("Administrator") == false))
				{	
					if((typeOfAccount.getText().trim().toString().equals("User") == false))
						{
							return null;
						}
				}
	
			User grabIt = (User)users.get(username.getText().trim()); // Here we want to see if the user already exists
			
			if(grabIt == null)
			{
				System.out.println("User does not exist, let's add it!");
				
				User newUser = new User(username.getText().trim(), firstName.getText().trim(), lastName.getText().trim(), password.getText().trim(), typeOfAccount.getText().trim());
				try
				{
					filewriter(filepath, newUser );
					users.put(username.getText().trim(), newUser);
					return users;
				}
				catch(IOException a)
				{
					return null;
				}
		
				
			}
				
			return null; 

	}
	
    /**
     * Method to write user information into text file
     *
     * @param outputPath
     * @param newUser
     * @throws java.io.IOException
     */
    public static void filewriter(String outputPath, User newUser) throws IOException 
    {

        //writing into text file 
        FileWriter fw = null;

        fw = new FileWriter(new File(outputPath), true);
        BufferedWriter bw = new BufferedWriter(fw);
        String writableString = "";

        writableString += newUser.getUsername() + ";" + newUser.getFirstName() + ";" + newUser.getLastName() + ";"+ newUser.getPassword() + ";" + newUser.getType() + "\n";

        //System.out.println(writableString);
        bw.write(writableString);
        bw.close();
    }
	
	
	
}
