
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.*;
import java.io.*;
import java.util.*;
public class WalmartSystem
{
	
	public LinkedList<Book> books = new LinkedList<Book>();
	public LinkedList<Book> clothes = new LinkedList<Book>();
	public LinkedList<Book> toys = new LinkedList<Book>();
	public LinkedList<Book> users = new LinkedList<Book>();

	
	
	public void mainProgram()
	{
		
	    Object[] options1 = { "Login", "Cancel"};

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
	    	int result = JOptionPane.showOptionDialog(null, panel, "Login Page", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options1, null);
	    	if (result == 0)
	    	{
	    		System.out.println("Option was 0");
	    		//TODO Put logic here to check the database for username and password. If there isn't then we stop the program here. 
	    		// Otherwise, we get permission to continue with the main menu - which is called below. 
	    		//JOptionPane.showMessageDialog(null, username.getText());
	    	
	    	}
	    	else 
	    	{
	    		
	    		System.exit(0);
	    		
	    	}
	    	
	    	books = this.gatherBooks("book", "./IT-306WalmartInventorySystem/data.txt");
	    	mainMenu();
	    	System.exit(0);
		
	}
	public LinkedList<Book> gatherBooks(String type, String filepath)
	
	{
		if (type.compareTo("book") == 0) 
		{
		    	LinkedList<Book> books = new LinkedList<Book>();
		    	try 
		    	{
		    		
		    		BufferedReader br = new BufferedReader(new FileReader(new File(filepath)));
		    		String line = "";
		    		while((line = br.readLine()) != null)
		    		{
		    			String [] tokens = line.split(";");
		    			if (tokens.length == 4)
		    			{
		    				// we have a book!
		    				Book a = new Book(tokens[0], tokens[1], tokens[3], Integer.parseInt(tokens[2].trim()));
		    				books.add(a);
		    			}	
		    		}
		    		br.close();
		    	}
		    	catch(FileNotFoundException e)
		    	{
		    		e.printStackTrace();
		    	}
		    	catch(IOException f)
		    	{
		    		f.printStackTrace();
		    		
		    	}				
		    	return books;
		}
		return null;
	}
	
	public void mainMenu()
	
	{
		// This function provides a JPanel for the main options to choose from 
		
	    Object[] options1 = { "Exit", "Order Items", "Add Users", "Special Sort", "Add Items", "View Items"};

    		JPanel panel = new JPanel();
    		//panel.setBackground(Color.lightGray);
    		
    		int result = JOptionPane.showOptionDialog(null, panel, "Main Menu", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options1, null);
    		System.out.println(result);
    		if (result == 5)
    		{
    			System.out.println("View Items");
    			viewItems();
    	
    		}
    		else if (result == 4)
    		{
    			System.out.println("Add Items");
    	
    		}
    		else if (result == 3)
    		{
    			System.out.println("SpecialSort");
    	
    		}
    		else if (result == 2)
    		{
    			System.out.println("Add User");
    	
    		}
      		else if (result == 1)
    		{
    			System.out.println("Order Items");
    	
    		}
    		else 
    		{
    			System.out.println("result was 0");
    			System.exit(0);
    		
    		}
		
		
		return;
	}

public void viewItems()
{
	// This function provides a JPanel for the main options to choose from 
	
    		Object[] options1 = { "Exit", "Main Menu", "View Books", "View Toys", "View Clothes"};

		JPanel panel = new JPanel();
		//panel.setBackground(Color.lightGray);
		
		int result = JOptionPane.showOptionDialog(null, panel, "Main Menu", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options1, null);
		System.out.println(result);
		if (result == 4)
		{
			System.out.println("View Clothes");
			
	
		}
		else if (result == 3)
		{
			System.out.println("View Toys");
	
		}
		else if (result == 2)
		{
			System.out.println("View Books");
			viewBooks();
	
		}
		else if (result == 1)
		{
			System.out.println("Main Menu");
			mainMenu();
	
		}
  		else if (result == 0)
		{
  			System.exit(0);
	
		}
		
	
	return;
	
	
}


public void viewBooks()
{
	
	Object[] options1 = { "Exit", "Main Menu"};
	String a  = "";
	Iterator it = books.iterator();
	Book b;
	while(it.hasNext())
	{
		b = (Book)it.next();
		a += b.getTitle() + "\n";
		
	}
	JPanel panel = new JPanel();
	Label labe = new Label(a);
	panel.add(labe);
	panel.setBackground(Color.LIGHT_GRAY);
	//panel.setBackground(Color.lightGray);
	
	//int result = JOptionPane.showOptionDialog(null, panel, "Main Menu", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options1, null);
	int result = JOptionPane.showOptionDialog(null, panel, "Main Menu", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options1, null);
	
	
	
	
	if (result == 1)
	{
		System.out.println("Main Menu");
		mainMenu();
		
	
	}
	else if (result == 0)
	{
		System.out.println("Exit");
		System.exit(0);
				
	}

	
	return;
	
	
}



}