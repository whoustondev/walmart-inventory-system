
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.*;
import java.io.*;
import java.util.*;
public class WalmartSystem
{
	
	Object[] options1 = { "Exit", "Main Menu"};
	public LinkedList<Book> books = new LinkedList<Book>();
	public LinkedList<Clothing> clothes = new LinkedList<Clothing>();
	public LinkedList<Toy> toys = new LinkedList<Toy>();
	//public LinkedList<User> users = new LinkedList<User>();

	
	public void mainProgram()
	{
		
	    Object[] options2 = { "Cancel", "Login",};

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
	    	
	    	int result = JOptionPane.showOptionDialog(null, panel, "Login Page", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options2, null);
	    	this.gatherItems("./IT-306WalmartInventorySystem/data.txt");   
	    	resultHandler(result);

	    	System.exit(0);	
	}
	/**
	 * This is a function that goes through the database
	 * Then, it adds all the items to their respective LinkedLists which are declared above.
	 * 
	 * @param filepath
	 */
	public void gatherItems(String filepath)
	{
	    	try 
	    	{	    		
	    		BufferedReader br = new BufferedReader(new FileReader(new File(filepath)));
	    		String line = "";
	    		while((line = br.readLine()) != null)
	    		{
	    			if(line.contains("Book"))
	    			{
		    			String [] tokens = line.split(";");
		    			System.out.println(tokens[0].trim());
		    			System.out.println(tokens.length);
		    			if (tokens.length == 5 && tokens[0].trim().equals(new String("Book")))
		    			{
		    				Book a = new Book(tokens[1].trim(), tokens[2].trim(), tokens[4].trim(), Integer.parseInt(tokens[3].trim()));
		    				this.books.add(a);
		    			}	
	    			}
	    			else if(line.contains("Toy"))
	    			{
	    				System.out.println("Contains a toy.");
		    			String [] tokens = line.split(";");
		    			if (tokens.length == 4 && tokens[0].trim().equals(new String("Toy")))
		    			{
		    				Toy a = new Toy(tokens[1].trim(), Integer.parseInt(tokens[2].trim()), Integer.parseInt(tokens[3].trim()));
		    				toys.add(a);
		    			}	
	    			}
	    			else if(line.contains("Clothing"))
	    			{
	    				System.out.println("Contains a Clothing item");
		    			String [] tokens = line.split(";");
		    			if (tokens.length == 8 && tokens[0].trim().equals(new String("Clothing")))
		    			{
		    				Clothing a = new Clothing(tokens[1].trim(), tokens[2].trim(), tokens[3].trim(), Integer.parseInt(tokens[4].trim()), Integer.parseInt(tokens[5].trim()), Integer.parseInt(tokens[6].trim()), Integer.parseInt(tokens[7].trim()));
		    				clothes.add(a);
		    			}	
	    			}
	    		}
	    		br.close();
	    		return;
	    	}
	    	catch(FileNotFoundException e)
	    	{
	    		e.printStackTrace();
	    	}
	    	catch(IOException f)
	    	{
	    		f.printStackTrace();	
	    	}	
	    	
	return;
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
		viewClothing();
	}
	else if (result == 3)
	{
		System.out.println("View Toys");
		viewToys();

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
	
	
	Iterator it = books.iterator();
	Book book;
	Object[][] rows = new Object[books.size()][4];
	int count = 0;
	
	while(it.hasNext())
	{
		book = (Book)it.next();
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
	
	resultHandler(result);
		
	return;
	
}
public void viewToys()
{
	
	Iterator it = toys.iterator();
	Toy toy;
	Object[][] rows = new Object[toys.size()][3];
	int count = 0;
	
	while(it.hasNext())
	{
		toy = (Toy)it.next();
		rows[count][0] = toy.getName();
		rows[count][1] = toy.getQuantity();
		rows[count][2] = toy.getRecAge();
		count++;
	}
	
	Object [] cols = {"Title","Quantity","Recommended Age"};
	JTable table = new JTable(rows, cols);

	int result = JOptionPane.showOptionDialog(null, new JScrollPane(table), "Toys", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options1, null);
	resultHandler(result);

	return;
	
	
	
}

public void viewClothing()
{
	
	Iterator it = clothes.iterator();
	Clothing clothing;
	Object[][] rows = new Object[clothes.size()][7];
	int count = 0;
	
	while(it.hasNext())
	{
		clothing = (Clothing)it.next();
		rows[count][0] = clothing.getName();
		rows[count][1] = clothing.getBrand();
		rows[count][2] = clothing.getColor();
		rows[count][3] = clothing.getQtySmall();
		rows[count][4] = clothing.getQtyMedium();
		rows[count][5] = clothing.getQtyLarge();
		rows[count][6] = clothing.getQtyExtraLarge();
		
		count++;
	}
	
	Object [] cols = {"Name","Brand","Color", "Qty Small", "Qty Medium", "Qty Large", "Qty XL"};
	JTable table = new JTable(rows, cols);

	int result = JOptionPane.showOptionDialog(null, new JScrollPane(table), "Toys", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options1, null);
	resultHandler(result);

	return;
	
	
	
}


/**
 * There exist two options very often - Exit and Main Menu. 
 * All this does is evaluate the result 1 or 0 and either exits or goes back to the main menu accordingly. 
 * @param result
 */
public void resultHandler(int result)
{
	
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
		
}




}