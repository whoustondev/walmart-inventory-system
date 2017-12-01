
import java.util.*;
import java.io.*;
import javax.swing.*;
import javax.*;
import java.awt.*;
public class Login
{
	
	/**
	 * This static method, LoginScreen can be used anywhere. All it takes is a HashMap of users to verify against
	 * When using anything from this class, use LoginScreen if you want 
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
