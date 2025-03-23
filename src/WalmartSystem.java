
import java.util.Collection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.*;
import java.io.*;
import java.util.*;
/**
 *  TODO: Leftover tasks
 *	2. Finish the username and password database check prob. a map.
 *	3. See if there is a way to hide values in the JTextField that holds a password
 *	5. Order new Items.
 *	9. fix buttons so that the quit button is on the left and that one of the buttons is highlighted
 * 
 * @author whouston
 *
 */
public class WalmartSystem
{
	
	
	private LinkedList<Book> books = new LinkedList<Book>();
	private LinkedList<Clothing> clothes = new LinkedList<Clothing>();
	private LinkedList<Toy> toys = new LinkedList<Toy>();
	//public LinkedList<User> users = new LinkedList<User>();
	/**
	 * The following variables are for helping out the drop down menus which exists for viewing items
	 * You will find that in the viewItems function, these variables are used. 
	 */
	private String lastClothingSortingSelection = "";
	private String lastToySortingSelection = "";
	private String lastBookSortingSelection = "";
	//JComboBox clothingDropdown = null;
	JComboBox clothingDropdown = new JComboBox(Clothing.childClassAttributes);
	JComboBox bookDropdown = new JComboBox(Book.childClassAttributes);
	JComboBox toyDropdown = new JComboBox(Toy.childClassAttributes);
	JTextField filterField = new JTextField(10);
	private String filepath = "./data.txt";

	private String userFilePath = "./userdb.txt";
	private String username = "";

    HashMap userMap = Login.getUsers(userFilePath);
    boolean isAdmin = false;
    
	public void run() throws IOException
	{             
        int result = 1;
        username = Login.loginScreen(userMap);
        if(((User)userMap.get(username)).getType().equals("Administrator"))
        {
        		isAdmin = true;
        }
  
        
        this.gatherItems(filepath); 
        
        this.genericResultHandler(result);

        System.exit(0);	         
	    	

	}
        



        

        
	/**
	 * This function will have one job - which is to add a designated item to the database.
	 * How this is going to work is..
	 * 1. find where objects like the one passed to this function are stored
	 * Books as listed first (all in a row) then followed by Clothes (all in a row) then followed by toys (all in a row)
	 * And so, it do
	 * @param filepath
	 */
	public void addItemToDatabase(String filepath, Item toAdd)
	{
	 	try 
	 	{	    				
	 		System.out.print("awd");
	 		BufferedWriter bw = new BufferedWriter(new FileWriter(new File(filepath), true));
	 		if(toAdd instanceof Clothing)
	 		{
	 			Clothing a = (Clothing)toAdd;
	 			bw.append(a.toString());
	 		}
	 		else if (toAdd instanceof Toy)
	 		{
	 			Toy c = (Toy)toAdd;
	 			bw.append(c.toString());
	 		}
	 		else if (toAdd instanceof Book)
	 		{
	 			Book c = (Book)toAdd;
	 			bw.append(c.toString());
	 		}
	 		bw.close();
	 	}
	 	catch(Exception a)
	 	{		
	 		a.printStackTrace();
	 	}
	}
	/**
	 * What this does is add a passed item to the respective LinkedList of which it belongs
	 * @param a
	 */
	public void addItem(Item a)
	{
		if (a instanceof Clothing)
		{		
			clothes.add((Clothing)a);
		}
		else if (a instanceof Toy)
		{
			toys.add((Toy)a);
		}
		else if (a instanceof Book)
		{
			books.add((Book)a);			
		}		
	}
	public void addNewItemOptionsJpanel() throws IOException
	{ 
		//Object[] addItemOptions = {  "Main Menu", "Add Clothing", "Add Toy", "Add Book", "Exit"};
		Object[] addItemOptions = {  "Exit", "Add Book", "Add Toy", "Add Clothing", "Main Menu"};
		
		JPanel panel = new JPanel();
		boolean foreverTrue = true;
		while(foreverTrue == true)
			{
				int result = JOptionPane.showOptionDialog(null, panel, "Add New Item", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, addItemOptions, null);
						
				if (result == 0)
				{
					System.out.println("Exit");
					System.exit(0);
				
				}
				else if (result == 1)
				{
					System.out.println("---Add Book");
					addNewBookPanel();
							
				}
				else if (result == 2)
				{
					System.out.println("---Add Toy");
					addNewToyPanel();
							
				}
				else if (result == 3)
				{
					System.out.println("---Add Clothing");
					addNewClothingPanel();
							
				}
				else if (result == 4)
				{
					System.out.println("Main Menu");
					mainMenu();
							
				}
		}
	}
	
	public void addNewClothingPanel() throws IOException
	{
		JPanel panel = new JPanel();

		Object[] options = {"Exit", "Main Menu", "Add Item"};
		try 
		{
			panel.setLayout(new GridLayout(8,1));
			JTextField title = new JTextField(10);
			panel.add(new Label("Title: "));
			panel.add(title);
			JTextField color = new JTextField(10);
			panel.add(new Label("Color: "));
			panel.add(color);
			
			JTextField brand = new JTextField(10);
			panel.add(new Label("Brand: "));
			panel.add(brand);			
			
			JTextField qtySmall = new JTextField(10);
			panel.add(new Label("Qty Small:"));
			panel.add(qtySmall);
			
			JTextField qtyMedium = new JTextField(10);
			panel.add(new Label("Qty Medium:"));
			panel.add(qtyMedium);		
			
			JTextField qtyLarge = new JTextField(10);
			panel.add(new Label("Qty Large:"));
			panel.add(qtyLarge);		
			
			JTextField qtyExtraLarge = new JTextField(10);
			panel.add(new Label("Quantity XL: "));
			panel.add(qtyExtraLarge);
			
			int result = JOptionPane.showOptionDialog(null, panel, "Add New Item", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);			
			

			
			int smallQuantity = Integer.parseInt(qtySmall.getText());	
			int mediumQuantity = Integer.parseInt(qtyMedium.getText());	
			int largeQuantity = Integer.parseInt(qtyLarge.getText());	
			int extraLargeQuantity = Integer.parseInt(qtyExtraLarge.getText());

			Clothing newPiece = new Clothing(title.getText(), brand.getText(), color.getText(), smallQuantity, mediumQuantity, largeQuantity, extraLargeQuantity);
			this.addItemToDatabase(filepath, newPiece);
			this.addItem(newPiece);
		
		}
		catch(NumberFormatException a)
		{
			a.printStackTrace();
			invalidInputPanel();
		}		
		
	}
	
	
	public void addNewBookPanel() throws IOException
	{
		JPanel panel = new JPanel();
		Object[] options = {"Exit", "Main Menu", "Add Item"};

		try 
		{
			panel.setLayout(new GridLayout(8,1));
			
			JTextField title = new JTextField(10);
			panel.add(new Label("Title: "));
			panel.add(title);

			JTextField author = new JTextField(10);
			panel.add(new Label("Author: "));
			panel.add(author);

			
			JTextField quantity = new JTextField(10);
			panel.add(new Label("Quantity: "));
			panel.add(quantity);
			
			JTextField genre = new JTextField(10);
			panel.add(new Label("Genre: "));
			panel.add(genre);
			
			int result = JOptionPane.showOptionDialog(null, panel, "Add New Item", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);			
		
			int qty = Integer.parseInt(quantity.getText());
			Book book = new Book(title.getText(), author.getText(), genre.getText(), qty);
			this.addItemToDatabase(filepath, book);
			this.addItem(book);
						
		}
		catch(NumberFormatException a)
		{
			a.printStackTrace();
			invalidInputPanel();
			
		}
		catch(Exception b)
		{	b.printStackTrace();
			invalidInputPanel();
			
		}
		
	}
	/**
	 * Perhaps after most of the functionality is complete,
	 * there could be a way to dynamically make the add functions.
	 * Perhaps dynamically storing the text field info in a hashmap (or something)
	 */
	public void addNewToyPanel() throws IOException
	{
		JPanel panel = new JPanel();

		Object[] options = {"Exit", "Main Menu", "Add Item"};
		try 
		{
			panel.setLayout(new GridLayout(8,1));
			
			JTextField title = new JTextField(10);
			panel.add(new Label("Title: "));
			panel.add(title);
		
			JTextField recAge = new JTextField(10);
			panel.add(new Label("Recommended Age: "));
			panel.add(recAge);
			
			JTextField qty = new JTextField(10);
			panel.add(new Label("Qty:"));
			panel.add(qty);
			
			int result = JOptionPane.showOptionDialog(null, panel, "Add New Item", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);			
			
			int quantity = Integer.parseInt(qty.getText());
			int recommendedAge = Integer.parseInt(recAge.getText());
			Toy toy = new Toy(title.getText(), quantity, recommendedAge);
			this.addItemToDatabase(filepath, toy);
			this.addItem(toy);
		}
		catch(NumberFormatException a)
		{
			a.printStackTrace();
			invalidInputPanel();
			
		}
		catch(Exception b)
		{	b.printStackTrace();
			invalidInputPanel();
			
		}
		
		
	}
	
	/**
	 * Whenever someone enters an invalid input, you can call this frame
	 * It will give a user the option to go back to the main menu, or exit. 
	 * 
	 */
	public void invalidInputPanel() throws IOException
	{
		Object[] simpleOptions = { "Exit", "Main Menu"};
		JPanel panel = new JPanel();
		panel.add(new Label("Unable to add input. Most likely unable to parse an int from a String. "));
		int result = JOptionPane.showOptionDialog(null, panel, "Error", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, simpleOptions, null);			
		genericResultHandler(result);
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
		
		int highestIdNumber = 0; /** this int here is to find out the highest ID that is stored in the DB. Then, we sssssset the Item class's static variable to be this for newly created objects**/
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
		    		
		    			if (tokens.length == 6 && tokens[0].trim().equals(new String("Book")))
		    			{
		    				Book a = new Book(tokens[2].trim(), tokens[3].trim(), tokens[5].trim(), Integer.parseInt(tokens[4].trim()));
		    				System.out.println("madeithere");
		    				int var = Integer.parseInt(tokens[1].trim());
		    				if(var > highestIdNumber)
		    				{
		    					 highestIdNumber = var; 
		    				}
		    				a.setId(var);
		    				this.books.add(a);
		    			}	
	    			}
	    			else if(line.contains("Toy"))
	    			{
	    				System.out.println("Contains a toy.");
		    			String [] tokens = line.split(";");
		    			if (tokens.length == 5 && tokens[0].trim().equals(new String("Toy")))
		    			{
		    				Toy a = new Toy(tokens[2].trim(), Integer.parseInt(tokens[3].trim()), Integer.parseInt(tokens[4].trim()));
		    				int var = Integer.parseInt(tokens[1].trim());
		    				if(var > highestIdNumber)
		    				{
		    					 highestIdNumber = var; 
		    				}
		    				a.setId(var);
		    				toys.add(a);
		    			}	
	    			}
	    			else if(line.contains("Clothing"))
	    			{
	    				System.out.println("Contains a Clothing item");
		    			String [] tokens = line.split(";");
		    			if (tokens.length == 9 && tokens[0].trim().equals(new String("Clothing")))
		    			{
		    				Clothing a = new Clothing(tokens[2].trim(), tokens[3].trim(), tokens[4].trim(), Integer.parseInt(tokens[5].trim()), Integer.parseInt(tokens[6].trim()), Integer.parseInt(tokens[7].trim()), Integer.parseInt(tokens[8].trim()));
		    				int var = Integer.parseInt(tokens[1].trim());
		    				if(var > highestIdNumber)
		    				{
		    					 highestIdNumber = var; 
		    				}
		    				a.setId(var);
		    				clothes.add(a);
		    			}	
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
	    	System.out.println("Highest Number");
	    	System.out.println(highestIdNumber);
	    	Item.setidCounter(highestIdNumber); /** This is so that we don't recreate any more ids that already exist. **/
	    	return;
	}

	/**
	 * The main menu function. 
	 */
	public void mainMenu() throws IOException
	
	{
		// This function provides a JPanel for the main options to choose from 
		Object[] adminOptions = { "Exit", "Add Users", "Add Items", "View Items", "Logout"};
	
		Object[] userOptions = { "Exit", "Add Items", "View Items", "Logout"};
			
	    
    		JPanel panel = new JPanel();
    		int result = 0;
    		if(isAdmin == true)
    		{
    			result = JOptionPane.showOptionDialog(null, panel, "Admin Main Menu", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, adminOptions, null);
        		if(result == 4)
        		{
        			isAdmin = false; 
        			username = Login.loginScreen(userMap);
        	        if(((User)userMap.get(username)).getType().equals("Administrator"))
        	        {
        	        		isAdmin = true;
        	        }	
        			mainMenu();
        		}
        		else if (result == 3)
        		{
        			viewItemOptionsJPanel();
        		}
        		else if (result == 2)
        		{
        			addNewItemOptionsJpanel();
        		}
        		else if (result == 1)
        		{
        			HashMap map = Login.addNewUserPanel(userFilePath, userMap);
        			if(map != null)
        			{
        				userMap = map;
        			}
        			else
        			{
        				badInputDialog();
        			}	
        			mainMenu();
        		}

        		else 
        		{
        			System.exit(0);
        		}		

    		}
    		else
    			{
	    			result = JOptionPane.showOptionDialog(null, panel, "User Main Menu", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, userOptions, null);
	        		if(result == 3)
	        		{
	        			isAdmin = false; 
	        			username = Login.loginScreen(userMap);
	        	        if(((User)userMap.get(username)).getType().equals("Administrator"))
	        	        {
	        	        		isAdmin = true;
	        	        }	
	        			mainMenu();
	        		}
	        		else if (result == 2)
	        		{
	        			viewItemOptionsJPanel();
	        		}
	        		else if (result == 1)
	        		{
	        			addNewItemOptionsJpanel();
	        		}
	 
	        		else 
	        		{
	        			System.exit(0);
	        		}		
    			}
    		
		return;
	}

	
	


public void viewItemOptionsJPanel() throws IOException
{
	// This function provides a JPanel for the main options to choose from 
	filterField.setText("");// So Filter is empty
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


public static LinkedList filter(String criteria, LinkedList a, String attribute)
{
	LinkedList<Item> newList = new LinkedList<Item>();
	System.out.println("I am actually in the filter function WTF>>>>");
	if(criteria.equals(""))
		return a;
	
	for(Object string: a)
	{
		System.out.println("inside for");
		if(((Item)string).getTitle().startsWith(criteria))
		{	
			newList.add((Item)string);
		}
		
		
	}
	return newList;
	
}

/**
 * Tell this function what type of item you want to view. 
 * pass in "Book" "Toy" or "Clothing". You know?
 * @param type
 */
public void viewItems(String type) throws IOException
{
	Object[] optionsWithSort = { "Exit", "Main Menu", "Sort Descending", "Sort Ascending", "Filter", "Delete"};
	Iterator it = null; 
	Clothing clothing = null;
	Toy toy = null;
	Book book = null;
	Item item;
	Object[][] rows = null;
	Object[] cols = null;
	LinkedList copy;
	
	String filterFieldString = filterField.getText();
	LinkedList filteredList = new LinkedList();
	
	
	
	int numberOfColumns = 3;
	if(type.equals("Clothing"))
	{	
		numberOfColumns = Clothing.childClassAttributes.length;

	 	//clothingDropdown = new JComboBox(cols);
	 	System.out.println("our last selection was...." + lastClothingSortingSelection );
	 	clothingDropdown.setSelectedItem(lastClothingSortingSelection);
		System.out.println("printing out the selected item after calling setSelectedItem" + clothingDropdown.getSelectedItem());
		if(filterFieldString == null || filterFieldString.equals(""))
			filteredList = clothes;
		else
			filteredList = filter(filterFieldString, clothes, clothingDropdown.getSelectedItem().toString());
			
		it = filteredList.iterator();
		rows = new Object[filteredList.size()][numberOfColumns];
	 	//it = clothes.iterator();
	 	cols = Clothing.childClassAttributes;

	}
	else if(type.equals("Toy"))
	{	
		 numberOfColumns = Toy.childClassAttributes.length;

		 System.out.println("our last selection was...." + lastToySortingSelection );
		 toyDropdown.setSelectedItem(lastToySortingSelection);
		 System.out.println("printing out the selected item after calling setSelectedItem" + toyDropdown.getSelectedItem());
		
		
		if(filterFieldString == null || filterFieldString.equals(""))
			filteredList = toys;
		else
			filteredList = filter(filterFieldString, toys, toyDropdown.getSelectedItem().toString());
		
		it = filteredList.iterator();
		 rows = new Object[filteredList.size()][numberOfColumns];
		 //it = toys.iterator();
		 cols = Toy.childClassAttributes;
	}
	else if(type.equals("Book"))
	{
		System.out.println("made it here---------------in Book");
		numberOfColumns = Book.childClassAttributes.length;	

		System.out.println();
		//bookDropdown = new JComboBox(cols);
		System.out.println("our last selection was...." + lastBookSortingSelection );
		bookDropdown.setSelectedItem(lastBookSortingSelection);
		System.out.println("printing out the selected item after calling setSelectedItem" + bookDropdown.getSelectedItem());
		if(filterFieldString == null || filterFieldString.equals(""))
			{
				filteredList = books;
			}
		else 
		{
			filteredList = filter(filterFieldString, books, bookDropdown.getSelectedItem().toString());
			System.out.println("I suppose I called the filter function....."+ filterFieldString);
		}
		it = filteredList.iterator();
		rows = new Object[filteredList.size()][numberOfColumns];
		
		cols = Book.childClassAttributes;
	
	}
	/**
	 * Down here we create the rows and the columns to be used by the JTable
	 */
	int count = 0;
	if(it == null)
		return;
	else
	{
		while(it.hasNext())
		{	if(type.equals("Clothing"))
			{
				clothing = (Clothing)it.next();			
			}
			else if(type.equals("Toy"))
			{
				toy = (Toy)it.next();
			}
			else if(type.equals("Book"))
			{
				book = (Book)it.next();
			}
			int i = 0;
			while(i < numberOfColumns)
			{
				if(type.equals("Book"))
				{
					rows[count][i] = book.getAttributeByIndex(i);
				}
				else if(type.equals("Toy"))
				{
					rows[count][i] = toy.getAttributeByIndex(i);
				}
				else if(type.equals("Clothing"))
				{
					rows[count][i] = clothing.getAttributeByIndex(i);			
				}
				i++;
			}
			count++;
		}
		
		JPanel panel = new JPanel();
		JTable table = new JTable(rows, cols);
		panel.add(new JScrollPane(table));
		
		if(type.equals("Clothing"))
		{
			panel.add(clothingDropdown);		
			clothingDropdown.setBounds(50, 100,90,20);  
			//JFrame frame = new JFrame();
			clothingDropdown.setVisible(true);
		}
		else if(type.equals("Toy"))
		{
			panel.add(toyDropdown);	
			toyDropdown.setBounds(50, 100,90,20); 
			toyDropdown.setVisible(true);
		}
		else if(type.equals("Book"))
		{
			panel.add(bookDropdown);	
			bookDropdown.setBounds(50,100,90,20); 
			bookDropdown.setVisible(true);
		}
		Label filterLabel = new Label("\nFilter On Title:");
		Label deleteLabel = new Label ("\nDelete ItemId: ");
		JTextField deletionField = new JTextField(10);
		
		panel.add(filterLabel);
		panel.add(filterField);
		panel.add(Box.createHorizontalStrut(20));
		panel.add(deleteLabel);
		
		
		panel.add(deletionField);
		panel.setVisible(true);		 
		
		
		int result = JOptionPane.showOptionDialog(null, panel, "Looking at"+type+"s", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, optionsWithSort, null);	
		
		
			
		String selection = "";
		
		System.out.println("this is the type of items we are viewing: "+type);
		if(type.equals("Clothing"))
		{	
			selection = (String) clothingDropdown.getSelectedItem();		
		}
		else if(type.equals("Toy"))
		{
			selection = (String) toyDropdown.getSelectedItem();		
		}
		else if(type.equals("Book"))
		{
			selection = (String) bookDropdown.getSelectedItem();
		}
		
		System.out.println("going to sort the..." + selection);
		if(type.equals("Clothing"))
		{	System.out.println("made it where i needed to");
			lastClothingSortingSelection = selection;		
			System.out.println("this should be a string-->>"+lastClothingSortingSelection);
		}
		else if(type.equals("Toy"))
		{
			System.out.println("made it where i needed to");
			lastToySortingSelection = selection;
			System.out.println("This should be an output---->"+lastToySortingSelection);
		}
		else if(type.equals("Book"))
		{
			System.out.println("made it where i needed to");
			lastBookSortingSelection = selection;
			System.out.println("This should be an output----->"+lastBookSortingSelection);
		}
		
		System.out.println("Selection: " + selection );
				
		sortingResultHandler(result, type, selection, deletionField);
	
		
		//System.out.println("Let's print out which row we are selecting" + table.getSelectedRow());
		
		//System.out.println(((Item)filteredList.get(table.getSelectedRow())).getTitle());
		
	}
	return;	
}
/**
 * This is a simple function that loops through and returns the index of wherever a string is located within an array.
 * Don't question it, it is to be used for helping the drop down menu.
 * We need a way to persist the value chosen by the user so it looks nice. 
 * @param array
 * @return
 */
public static int loopThroughReturnIndex(String[] array, String selection)
{
	for(int i = 0; i<array.length; i++)
	{
		if(array[i].equals(selection))
			return i;
				
	}
	return -1;
}






/**
 * There exist the options - Exit and Main Menu. 
 * This can be used in a variety of cases
 * (Errors being the primary reason)
 * @param result

 */
public void genericResultHandler(int result) throws IOException
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

public void badInputDialog()
{
	Object[] options = { "Exit", "Main Menu"};
	JPanel panel = new JPanel();
	panel.add(new Label("Your input was invalid."));
	int result = JOptionPane.showOptionDialog(null, panel, "Main Menu", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);
	if (result == 1)
	{
		try 
		{
			
			mainMenu();
		}
		catch(Exception a)
		{
			
			a.printStackTrace();
		}
	}
	else if (result == 0)
	{
		System.exit(0);
	}
}

/**
 * This is a special kind of resulthandler I decided to make up 
 * It has the options sort ascending, sort descending, menu, and quit. 
 * Hopefully this function can be eradicated and we can move to buttons.
 * But in all honesty, our ViewItems function is so huge that we probably want to start factoring out some of that code. (hence this function). 
 * @param result 
 * @param whatItemType
 * @param sortCriteria
 *
 */
public void sortingResultHandler(int result, String whatItemType, String sortCriteria, JTextField field) throws IOException
{
	
	if (result == 5) // We delete
	{	
		try
		{
			int itemToRemove = Integer.parseInt(field.getText());
			
			if(whatItemType.equals("Book"))
			{
				ItemRemover.removeFromLinkedList(books, itemToRemove );
				ItemRemover.removeItemFromDb(itemToRemove, filepath);
			}
			else if(whatItemType.equals("Clothes"))
			{
				ItemRemover.removeFromLinkedList(clothes, itemToRemove );
				ItemRemover.removeItemFromDb(itemToRemove, filepath);
				
			}
			else if(whatItemType.equals("Toys"))
			{
				ItemRemover.removeFromLinkedList(toys, itemToRemove );
				ItemRemover.removeItemFromDb(itemToRemove, filepath);
				
			}
			
		}
		catch(NumberFormatException nfexception)
		{
			System.out.println("unable to remove item");
			
		}
		catch (Exception b)
		{
			b.printStackTrace();
		}
		viewItems(whatItemType);
	}
	if (result == 4) // We filter
	{	
		viewItems(whatItemType);
	}
	else if (result == 3 || result == 2) // someone hits the sort ascending button (3) or descending (2)
	{
		boolean ascending = false;
		if(result == 3)
			ascending = true;
		else if(result == 2)
			ascending = false;
			
		if(whatItemType.equals("Book"))
		{
			System.out.println("we are good up until here");
			books = SortClass.mergeSort(books, ascending, sortCriteria);	
			System.out.println("WE DIDNT GET BACK UHERE");
			viewItems("Book");
		}			
		else if (whatItemType.equals("Clothing"))
		{
			clothes = SortClass.mergeSort(clothes, ascending, sortCriteria);
			viewItems("Clothing");
		}			
		else if (whatItemType.equals("Toy"))
		{
			toys = SortClass.mergeSort(toys, ascending, sortCriteria);
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

