
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.io.*;
import javax.swing.*;
import javax.*;
import java.awt.*;
public class Login
{
	
	public static boolean loginScreen(HashMap map)
	
	{
		boolean displayWrongInputMessage = false;
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
		    	}
		    	else
		    	{
		    		System.exit(0);
		    		
		    	}
		    	displayWrongInputMessage = true;
			
		}
		return validUser;
		
	}
        	
	
	public static boolean validateLogin(HashMap map, String username, String password)
	{
		try
		{
			System.out.println(map.get("whouston"));
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
	
	
	/**returns a hashamp full of users given a filepath**/
	public static HashMap getUsers(String filepath)
	{
		HashMap<String, User> map = new HashMap<String, User>();
        try 
        {
            BufferedReader br = new BufferedReader(new FileReader(filepath));
            String line = "";
            while((line = br.readLine()) != null)
            {
            		String [] tokens = line.split(";");
            		if(tokens.length == 5)
            		{
            			map.put(tokens[0].trim(), new User(tokens[0].trim(), tokens[1].trim(), tokens[2].trim(), tokens[3].trim(), tokens[4].trim()));
            			System.out.println("added a user");
            			System.out.println(tokens[0]);
            			System.out.println(tokens[1]);
            			System.out.println(tokens[2]);
            			System.out.println(tokens[3]);
            			System.out.println(tokens[4]);
            			
            			
            		}
            		else
            		{
            			return null;
            			
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
        
        return map;
	}
	
}
