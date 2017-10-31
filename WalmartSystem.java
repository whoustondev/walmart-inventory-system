
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.*;
import java.io.*;
import java.util.*;
public class WalmartSystem
{
	
	public LinkedList<Book> books = new LinkedList<Book>();
	//public LinkedList<Clothing> clothes = new LinkedList<Clothing>();
	public LinkedList<Toy> toys = new LinkedList<Toy>();
	//public LinkedList<User> users = new LinkedList<User>();

	
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
	    	
	    	books = this.gatherBooks("./IT-306WalmartInventorySystem/data.txt");
	    	mainMenu();
	    	System.exit(0);
		
	}
	public LinkedList<Book> gatherBooks(String filepath)
	
	{
		    	//LinkedList<Book> books = new LinkedList<Book>();
		    	try 
		    	{	    		
		    		BufferedReader br = new BufferedReader(new FileReader(new File(filepath)));
		    		String line = "";
		    		while((line = br.readLine()) != null)
		    		{
		    			String [] tokens = line.split(";");
		    			System.out.println(tokens[0].trim());
		    			System.out.println(tokens.length);
		    			if (tokens.length == 5 && tokens[0].trim().equals(new String("Book")))
		    			{
		    				Book a = new Book(tokens[1], tokens[2], tokens[4], Integer.parseInt(tokens[3].trim()));
		    				this.books.add(a);
		    			}	
		    		}
		    		br.close();
		    		return books;
		    	}
		    	catch(FileNotFoundException e)
		    	{
		    		e.printStackTrace();
		    	}
		    	catch(IOException f)
		    	{
		    		f.printStackTrace();
		    		
		    	}				
		    	
		
		return null;
	}
	public LinkedList<Toy> gatherToys(String filepath)
	{
		

		    	try 
		    	{	    		
		    		BufferedReader br = new BufferedReader(new FileReader(new File(filepath)));
		    		String line = "";
		    		while((line = br.readLine()) != null)
		    		{
		    			if(line.contains("Toy"))
		    			{
			    			String [] tokens = line.split(";");
			    			System.out.println(tokens[0].trim());
			    			System.out.println(tokens.length);
			    			if (tokens.length == 5 && tokens[0].trim().equals(new String("Book")))
			    			{
			    				Toy a = new Toy(tokens[1], tokens[2], tokens[4], Integer.parseInt(tokens[3].trim()));
			    				toys.add(a);
			    			}	
		    			}
		    		}
		    		
		    		br.close();
		    		return toys;
		    	}
		    	catch(FileNotFoundException e)
		    	{
		    		e.printStackTrace();
		    	}
		    	catch(IOException f)
		    	{
		    		f.printStackTrace();
		    		
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
	String booksString  = "<html>Title&nbsp;&nbsp;&nbsp;Author&nbsp;&nbsp;&nbsp;Quantity<br>";
	Iterator it = books.iterator();
	Book book;
	Object[][] rows = new Object[books.size()][4];
	int count = 0;
	
	while(it.hasNext())
	{
		book = (Book)it.next();
		//booksString += book.getTitle() + " " + book.getAuthor() + " " + book.getQuantity() + " " + "<br>";
		rows[count][0] = book.getTitle();
		rows[count][1] = book.getAuthor();
		rows[count][2] = book.getQuantity();
		rows[count][3] = book.getGenre();
		
		count++;
	}
	
	
		
		Object [] cols = {"Title","Author","Quantity", "Genre"};
		JTable table = new JTable(rows, cols);
		//JOptionPane.showMessageDialog(null, new JScrollPane(table));
		int result = JOptionPane.showOptionDialog(null, new JScrollPane(table), "Books", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options1, null);
		
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