
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.*;
import java.io.*;
import java.util.*;
public class WalmartSystem
{
	
	Object[] options1 = { "Exit", "Main Menu"};
	Object[] optionsWithSort = { "Exit", "Main Menu", "Sort Descending", "Sort Ascending",};
	
	public LinkedList<Book> books = new LinkedList<Book>();
	public LinkedList<Clothing> clothes = new LinkedList<Clothing>();
	public LinkedList<Toy> toys = new LinkedList<Toy>();
	//public LinkedList<User> users = new LinkedList<User>();

	
	public void run()
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
	    	this.resultHandler(result, null, null);

    	System.exit(0);	
	}
	/**
	 * This is a function that goes through the database
	 * Then, it adds all the items to their respective LinkedLists which are declared above.
	 * This is not a very  modular fuction, but hey we have no choice really. 
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
    			viewItemsJPanel();
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

public void viewItemsJPanel()
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
		viewItems("Clothing");
	}
	else if (result == 3)
	{
		System.out.println("View Toys");
		viewItems("Toy");

	}
	else if (result == 2)
	{
		System.out.println("View Books");
		viewItems("Book");

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



public void viewItems(String type)
{
	
	Iterator it = null; 
	Clothing clothing = null;
	Toy toy = null;
	Book book = null;
	Item item;
	Object[][] rows = null;
	Object[] cols = null;
	int numberOfColumns = 3;
	if(type.equals("Clothing"))
	{	
		numberOfColumns = Clothing.childClassAttributes.length;
		rows = new Object[clothes.size()][numberOfColumns];
	 	it = clothes.iterator();
	 	cols = Clothing.childClassAttributes;
	 	
	}
	else if(type.equals("Toy"))
	{	
		 numberOfColumns = Toy.childClassAttributes.length;
		 rows = new Object[toys.size()][numberOfColumns];
		 it = toys.iterator();
		 cols = Toy.childClassAttributes;
	}
	else if(type.equals("Book"))
	{
		numberOfColumns = Book.childClassAttributes.length;	
		rows = new Object[books.size()][numberOfColumns];
		it = books.iterator();
		cols = Book.childClassAttributes;

	}

	int count = 0;
	if(it == null)
		return;
	else
	{
		while(it.hasNext())
		{	if(type.equals("Clothing"))
				clothing = (Clothing)it.next();
			else if(type.equals("Toy"))
				toy = (Toy)it.next();
			else if(type.equals("Book"))
				book= (Book)it.next();
			int i = 0;
			while(i < numberOfColumns)
			{
				if(type.equals("Book"))
					rows[count][i] = book.getAttributeByIndex(i);
				else if(type.equals("Toy"))
					rows[count][i] = toy.getAttributeByIndex(i);
				if(type.equals("Clothing"))
					rows[count][i] = clothing.getAttributeByIndex(i);
				
				i++;
			}
			count++;
		}
		
		JPanel panel = new JPanel();
		JTable table = new JTable(rows, cols);
		panel.add(new JScrollPane(table));
		
		JComboBox bookList = new JComboBox(cols);

		panel.add(bookList);
		bookList.setBounds(50, 100,90,20);  
		//JFrame frame = new JFrame();
		bookList.setVisible(true);
		panel.setVisible(true);
		
		
		
		
		
		
		int result = JOptionPane.showOptionDialog(null, panel, "Books", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, optionsWithSort, null);
		
		String selection = (String) bookList.getSelectedItem();
		System.out.println("going to sort the..." + selection);
		resultHandler(result, type, selection);
	}

	return;
		
}



/**
 * A function that takes a linkedlist of items and sorts it by title. 
 * We are making this static so people can use it regardless on any kind of linkedList
 * This is also modular as to keep us from getting confused.
 * No matter what
 * 
 */

public static LinkedList sortByTitle(LinkedList a, boolean ascending)
{
	Item[] array = new Item[a.size()];
	Iterator it = a.iterator();
	//it.next();
	for(int i = 0; i< array.length; i++)
	{
		array[i] = (Item)a.get(i);	
	}
	
	for(int i=0; i<a.size(); i++)
	{
		int k = i;
		if(ascending == true)
		{
			
			while( k > 0 && array[k].getTitle().compareTo(array[k-1].getTitle()) <= 0)			
			{
				System.out.println(k);
				Item temp = array[k];
				array[k] = array[k-1];
				array[k-1] = temp;
				k--;
			}
		}
		else // if we want descending
		{
			
			while( k > 0 && array[k-1].getTitle().compareTo(array[k].getTitle()) <= 0)			
			{
				//System.out.println(k);
				Item temp = array[k];
				array[k] = array[k-1];
				array[k-1] = temp;
				k--;
			}	
		}
	}
	
	LinkedList listToReturn = new LinkedList();
	for(int i = 0; i < array.length; i++)
	{
		//System.out.println(((Book)array[i]).getTitle());	
		listToReturn.add(array[i]);
	}
	
	return listToReturn;
}


public static LinkedList sortByQuantity(LinkedList a, boolean ascending)
{
	Item[] array = new Item[a.size()];
	Iterator it = a.iterator();
	//it.next();
	for(int i = 0; i< array.length; i++)
	{
		array[i] = (Item)a.get(i);	
	}
	
	for(int i=0; i<a.size(); i++)
	{
		int k = i;
		if(ascending == true)
		{
			
			while( k > 0 && array[k].getQuantity() <= array[k-1].getQuantity())			
			{
				System.out.println(k);
				Item temp = array[k];
				array[k] = array[k-1];
				array[k-1] = temp;
				k--;
			}
		}
		else // if we want descending
		{
			
			while( k > 0 && array[k-1].getQuantity() <= array[k].getQuantity())			
			{
				System.out.println(k);
				Item temp = array[k];
				array[k] = array[k-1];
				array[k-1] = temp;
				k--;
			}	
		}
	}
	
	LinkedList listToReturn = new LinkedList();
	for(int i = 0; i < array.length; i++)
	{
		//System.out.println(((Book)array[i]).getTitle());	
		listToReturn.add(array[i]);
	}
	
	return listToReturn;
}
public static LinkedList sortByGenre(LinkedList a, boolean ascending)
{
	Item[] array = new Item[a.size()];
	Iterator it = a.iterator();
	//it.next();
	for(int i = 0; i< array.length; i++)
	{
		array[i] = (Item)a.get(i);	
	}	
	for(int i=0; i<a.size(); i++)
	{
		int k = i;
		if(ascending == true)
		{			
			while( k > 0 && ((Book)array[k]).getGenre().compareTo(((Book)array[k-1]).getGenre())	<= 0 )		
			{
				System.out.println(k);
				Item temp = array[k];
				array[k] = array[k-1];
				array[k-1] = temp;
				k--;
			}
		}
		else // if we want descending
		{		
			while( k > 0 && ((Book)array[k-1]).getGenre().compareTo(((Book)array[k]).getGenre())	<= 0 )			
			{
				System.out.println(k);
				Item temp = array[k];
				array[k] = array[k-1];
				array[k-1] = temp;
				k--;
			}	
		}
	}
	
	LinkedList listToReturn = new LinkedList();
	for(int i = 0; i < array.length; i++)
	{
		//System.out.println(((Book)array[i]).getTitle());	
		listToReturn.add(array[i]);
	}
	
	return listToReturn;
}
public static LinkedList sortByAuthor(LinkedList a, boolean ascending)
{
	Item[] array = new Item[a.size()];
	Iterator it = a.iterator();
	//it.next();
	for(int i = 0; i< array.length; i++)
	{
		array[i] = (Item)a.get(i);	
	}
	
	for(int i=0; i<a.size(); i++)
	{
		int k = i;
		if(ascending == true)
		{
			
			while( k > 0 && ((Book)array[k]).getAuthor().compareTo(((Book)array[k-1]).getAuthor())<= 0 )		
			{
				System.out.println(k);
				Item temp = array[k];
				array[k] = array[k-1];
				array[k-1] = temp;
				k--;
			}
		}
		else // if we want descending
		{
			
			while( k > 0 && ((Book)array[k-1]).getAuthor().compareTo(((Book)array[k]).getAuthor()) <= 0 )			
			{
				System.out.println(k);
				Item temp = array[k];
				array[k] = array[k-1];
				array[k-1] = temp;
				k--;
			}	
		}
	}
	
	LinkedList listToReturn = new LinkedList();
	for(int i = 0; i < array.length; i++)
	{
		//System.out.println(((Book)array[i]).getTitle());	
		listToReturn.add(array[i]);
	}
	
	return listToReturn;
}
/**
 * There exist the options - Exit and Main Menu. 
 * @param result
 * @param LinkedList
 * 
 * Added  a LinkedList as an argument because to use resulthandler with the sorting functionality, 
 * we need to be able to know what kind of elements we are sorting. Previously, resulthandler was just used to exit or go back to the main menu
 * Now, resulthandler is used for sorting as well under the options available for viewing items (view books, view toys, view clothing)
 */
public void resultHandler(int result, String whatItemType, String sortCriteria)
{
	if (result == 3) // someone hits the sort ascending button
	{
		System.out.println("Sort Ascending");
		if(whatItemType.equals("Book"))
		{
			if( sortCriteria.equals("Title") || sortCriteria.equals("title"))
				{
					books = sortByTitle(books, true);
					
				}
			else if( sortCriteria.equals("Quantity") || sortCriteria.equals("quantity"))
				{
					books = sortByQuantity(books, true);
					
				}
			else if( sortCriteria.equals("genre") || sortCriteria.equals("Genre"))
				{
					
					books = sortByGenre(books, true);
				}
			else if( sortCriteria.equals("Author") || sortCriteria.equals("Author"))
			{
				
				books = sortByAuthor(books, true);
			}
			
			viewItems("Book");
		}
			
		else if (whatItemType.equals("Clothing"))
		{
			clothes = sortByTitle(clothes, true);
			viewItems("Clothing");
		}
			
		else if (whatItemType.equals("Toy"))
		{
			toys = sortByTitle(toys, true);
			viewItems("Toy");
			
		}	
	}
	else if (result == 2) // Someone hits the sort descending button
	{
		System.out.println("Sort Descending");
		
		if(whatItemType.equals("Book"))
		{	
			if( sortCriteria.equals("Title") || sortCriteria.equals("title"))
				books = sortByTitle(books, false);
			else if( sortCriteria.equals("Quantity") || sortCriteria.equals("quantity"))
				books = sortByQuantity(books, false);
			else if( sortCriteria.equals("genre") || sortCriteria.equals("Genre"))
				books = sortByGenre(books, false);
			else if( sortCriteria.equals("Author") || sortCriteria.equals("Author"))
				books = sortByAuthor(books, false);
	
			viewItems("Book");
		}
			
		else if (whatItemType.equals("Clothing"))
		{
			clothes = sortByTitle(clothes, false);
			viewItems("Clothing");
		}
			
		else if (whatItemType.equals("Toy"))
		{
			toys = sortByTitle(toys, false);
			viewItems("Toy");
			
		}
	
	}
	else if (result == 1)
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