
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.*;
import java.io.*;
import java.util.*;
/**
 *  TODO: Leftover tasks
 *  1. Make the buttons for sorting right underneath the drop down (ascend and descend)
 *	2. Finish the username and password database check prob. a map.
 *	3. See if there is a way to hide values in the JTextField that holds a password
 *	4. Make sure you can use the sorting algo by itself by passing null values to stuff you don't use
 *		we want modularity in this program - so we should be able  to pass a LinkedList and sort 
 *	5. Order new Items.
 *	6. Add a new Product.
 *	7. Search (filter)
 *	8. Make it so that you can 
 *	9. fix buttons so that the quit button is on the left and that one of the buttons is highlighted
 *	10. Make it so that the title drop down isn't always on the default (Title but whatever the last choice was)
 */
public class WalmartSystem
{
	
	private Object[] options1 = { "Exit", "Main Menu"};
	private Object[] optionsWithSort = { "Exit", "Main Menu", "Sort Descending", "Sort Ascending",};
	
	private LinkedList<Book> books = new LinkedList<Book>();
	private LinkedList<Clothing> clothes = new LinkedList<Clothing>();
	private LinkedList<Toy> toys = new LinkedList<Toy>();
	//public LinkedList<User> users = new LinkedList<User>();
	/**
	 * I hate every but of this
	 * but for now, these three ints help me keep track of the last index that was chosen in the sorting drop down. 
	 * Otherwise, the default will always be Title even after you select and then sort something else
	 */
	private String lastClothingSortingSelection = "";
	private String lastToySortingSelection = "";
	private String lastBookSortingSelection = "";
	
	
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
	
	public void addNewItemOptionsJpanel()
	{ 
		//Object[] addItemOptions = {  "Main Menu", "Add Clothing", "Add Toy", "Add Book", "Exit"};
		Object[] addItemOptions = {  "Exit", "Add Book", "Add Toy", "Add Clothing", "Main Menu"};
		
		JPanel panel = new JPanel();
		int result = JOptionPane.showOptionDialog(null, panel, "Add New Item", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, addItemOptions, null);
		if (result == 0)
		{
			System.out.println("Exit");
			System.exit(0);
		
		}
		else if (result == 1)
		{
			System.out.println("---Add Book");
			addNewBook();
					
		}
		else if (result == 2)
		{
			System.out.println("---Add Toy");
			addNewToy();
					
		}
		else if (result == 3)
		{
			System.out.println("---Add Clothing");
			addNewClothing();
					
		}
		else if (result == 4)
		{
			System.out.println("Main Menu");
			mainMenu();
					
		}
	}
	
	public void addNewClothing()
	{
		JPanel panel = new JPanel();

		Object[] options = {"Exit", "Main Menu", "Add Item"};
		try 
		{
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
			panel.add(brand);
			
			
			
			JTextField qtyLarge = new JTextField(10);
			panel.add(new Label("Qty Large:"));
			panel.add(qtyLarge);
			
			
			JTextField qtyExtraLarge = new JTextField(10);
			panel.add(new Label("Quantity XL: "));
			panel.add(qtyExtraLarge);
			int result = JOptionPane.showOptionDialog(null, panel, "Add New Item", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);

			
		}
		catch(Exception a)
		{
			a.printStackTrace();
			
		}
		
		
		
	}
	
	public void addNewToy()
	{
		JPanel panel = new JPanel();


		try 
		{
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
			panel.add(brand);
			
			
			
			JTextField qtyLarge = new JTextField(10);
			panel.add(new Label("Qty Large:"));
			panel.add(qtyLarge);
			
			
			JTextField qtyExtraLarge = new JTextField(10);
			panel.add(new Label("Quantity XL: "));
			panel.add(qtyExtraLarge);
		}
		catch(Exception a)
		{
			a.printStackTrace();
			
		}
		
		
	}
	public void addNewBook()
	{
		JPanel panel = new JPanel();


		try 
		{
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
			panel.add(brand);
			
			
			
			JTextField qtyLarge = new JTextField(10);
			panel.add(new Label("Qty Large:"));
			panel.add(qtyLarge);
			
			
			JTextField qtyExtraLarge = new JTextField(10);
			panel.add(new Label("Quantity XL: "));
			panel.add(qtyExtraLarge);
		}
		catch(Exception a)
		{
			a.printStackTrace();
			
		}
		
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

	/**
	 * The main menu function. 
	 */
	public void mainMenu()
	
	{
		// This function provides a JPanel for the main options to choose from 
		
	    Object[] options1 = { "Exit", "Order Items", "Add Users", "Add Items", "View Items"};
    		JPanel panel = new JPanel();
    		//panel.setBackground(Color.lightGray);
    		
    		int result = JOptionPane.showOptionDialog(null, panel, "Main Menu", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options1, null);
    		System.out.println(result);
    		if (result == 4)
    		{
    			System.out.println("View Items");
    			viewItemOptionsJPanel();
    		}
    		else if (result == 3)
    		{
    			System.out.println("Add Items");
    			addNewItemOptionsJpanel();
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

public void viewItemOptionsJPanel()
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

/**
 * Tell this function what type of item you want to view. 
 * @param type
 */
public void viewItems(String type)
{
	Iterator it = null; 
	Clothing clothing = null;
	Toy toy = null;
	Book book = null;
	Item item;
	Object[][] rows = null;
	Object[] cols = null;
	JComboBox bookList = null;
	int numberOfColumns = 3;
	if(type.equals("Clothing"))
	{	
		numberOfColumns = Clothing.childClassAttributes.length;
		rows = new Object[clothes.size()][numberOfColumns];
	 	it = clothes.iterator();
	 	cols = Clothing.childClassAttributes;
	 	bookList= new JComboBox(cols);
	 	System.out.println("our last selection was...." + lastClothingSortingSelection );
	 	bookList.setSelectedItem(lastClothingSortingSelection);
		System.out.println("printing out the selected item after calling setSelectedItem" + bookList.getSelectedItem());
		
	}
	else if(type.equals("Toy"))
	{	
		 numberOfColumns = Toy.childClassAttributes.length;
		 rows = new Object[toys.size()][numberOfColumns];
		 it = toys.iterator();
		 cols = Toy.childClassAttributes;
		 bookList = new JComboBox(cols);
		 System.out.println("our last selection was...." + lastToySortingSelection );
		 bookList.setSelectedItem(lastToySortingSelection);
			System.out.println("printing out the selected item after calling setSelectedItem" + bookList.getSelectedItem());
			
	}
	else if(type.equals("Book"))
	{
		numberOfColumns = Book.childClassAttributes.length;	
		rows = new Object[books.size()][numberOfColumns];
		it = books.iterator();
		cols = Book.childClassAttributes;
		bookList = new JComboBox(cols);
		System.out.println("our last selection was...." + lastBookSortingSelection );
		bookList.setSelectedItem(lastBookSortingSelection);
		System.out.println("printing out the selected item after calling setSelectedItem" + bookList.getSelectedItem());
	
	}

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
				toy = (Toy)it.next();
			else if(type.equals("Book"))
				book = (Book)it.next();
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
		
		
		panel.add(bookList);
		bookList.setBounds(50, 100,90,20);  
		//JFrame frame = new JFrame();
		bookList.setVisible(true);
		
		panel.setVisible(true);		 
		int result = JOptionPane.showOptionDialog(null, panel, "Looking at"+type+"s", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, optionsWithSort, null);	
		String selection = (String) bookList.getSelectedItem();
		System.out.println("going to sort the..." + selection);
		if(type.equals("Clothing"))
		{	System.out.println("made it where i needed to");
			lastClothingSortingSelection = selection;		
			System.out.println(lastClothingSortingSelection);
		}
		else if(type.equals("Toy"))
		{
			System.out.println("made it where i needed to");
			lastBookSortingSelection = selection;
			System.out.println(lastToySortingSelection);
		}
		else if(type.equals("Book"))
		{
			System.out.println("made it where i needed to");
			lastToySortingSelection = selection;
			System.out.println(lastBookSortingSelection);
		}
		
		System.out.println("Selection: " + selection );
		resultHandler(result, type, selection);
	}
	return;	
}
/**
 * This is a simple function that loops through and returns the index of wherever the String called selection is located. 
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
public static Item[] convertToArray(LinkedList a)
{
	Item[] array = new Item[a.size()];
	//Iterator it = a.iterator();
	//it.next();
	for(int i = 0; i< array.length; i++)
	{
		array[i] = (Item)a.get(i);	
	}
	
	return array;
}

public static LinkedList convertToLinkedList(Item[] array)
{
	
	LinkedList listToReturn = new LinkedList();

	for(int i = 0; i < array.length; i++)
	{
		//System.out.println(((Book)array[i]).getTitle());	
		listToReturn.add(array[i]);
	}
	return listToReturn;
}


/**
 * This function takes in two arguments and it compares the two for purposes of the sorting.
 * This is a little bit tricky since it has been refactored down. This function is used in the sorting function to compare stuff for ordering. 
 * But, just think of this function as a wrapper around the compareTo function. 
 * This function handles 2 items, and compares the attributes of those 2 items (depending on what kind - Book, Clothing, Toy, etc. )
 * First - checks to see what kind of objects we are dealing with by using instanceof
 * Second - we check to see what kind of object the attribute is. A String or an Integer are the most common
 * Third - if it's a string, we use compareTo of the attribute we grabbed (via the user choice) for the specific objects to compare against
 * 			if it's an int, it just returns a negative value (-1) if the first val is less than the second val so that the sort method knows to keep looping through objects. 
 * 
 * @return
 */
public static int customComparator(Item a, Item b, String attributeToSortBy)
{	
	int comparatorValue = -1;
	if(a instanceof Clothing && b instanceof Clothing)
	{
		if(((Clothing)a).attributeMap.get(attributeToSortBy) instanceof String)
		{
			comparatorValue = ((String)((Clothing)a).attributeMap.get(attributeToSortBy)).compareTo((String)((Clothing)b).attributeMap.get(attributeToSortBy));
		}
		else if(((Clothing)a).attributeMap.get(attributeToSortBy) instanceof Integer)
		{	
			//System.out.println("is a less than b? : " + ((Clothing)a).attributeMap.get(Integer.parseInt(attributeToSortBy)) + "  " + ((Clothing)b).attributeMap.get(Integer.parseInt(attributeToSortBy)));
			if((Integer)((Clothing)a).attributeMap.get(attributeToSortBy) <= (Integer)((Clothing)b).attributeMap.get(attributeToSortBy))
			{
				System.out.println((Integer)((Clothing)a).attributeMap.get(attributeToSortBy) + "is less than " + (Integer)((Clothing)b).attributeMap.get(attributeToSortBy));
				comparatorValue = -1;
			}
			else
			{
				comparatorValue = 1;
			}
		}
	
	}
	else if (a instanceof Toy && b instanceof Toy)
	{
		if(((Toy)a).attributeMap.get(attributeToSortBy) instanceof String)
		{
			comparatorValue = ((String)((Toy)a).attributeMap.get(attributeToSortBy)).compareTo((String)((Toy)b).attributeMap.get(attributeToSortBy));
		}
		else if(((Toy)a).attributeMap.get(attributeToSortBy) instanceof Integer)
		{	
			//System.out.println("is a less than b? : " + ((Toy)a).attributeMap.get(Integer.parseInt(attributeToSortBy)) + "  " + ((Toy)b).attributeMap.get(Integer.parseInt(attributeToSortBy)));
			if((Integer)((Toy)a).attributeMap.get(attributeToSortBy) <= (Integer)((Toy)b).attributeMap.get(attributeToSortBy))
			{
				System.out.println((Integer)((Toy)a).attributeMap.get(attributeToSortBy) + "is less than " + (Integer)((Toy)b).attributeMap.get(attributeToSortBy));
				comparatorValue = -1;
			}
			else
			{
				comparatorValue = 1;
			}
		}		
	}
	else if (a instanceof Book && b instanceof Book)
	{	
		if(((Book)a).attributeMap.get(attributeToSortBy) instanceof String)
		{
			comparatorValue = ((String)((Book)a).attributeMap.get(attributeToSortBy)).compareTo((String)((Book)b).attributeMap.get(attributeToSortBy));
		}
		else if(((Book)a).attributeMap.get(attributeToSortBy) instanceof Integer)
		{	
			//System.out.println("is a less than b? : " + ((Book)a).attributeMap.get(Integer.parseInt(attributeToSortBy)) + "  " + ((Book)b).attributeMap.get(Integer.parseInt(attributeToSortBy)));
			if((Integer)((Book)a).attributeMap.get(attributeToSortBy) <= (Integer)((Book)b).attributeMap.get(attributeToSortBy))
			{
				System.out.println((Integer)((Book)a).attributeMap.get(attributeToSortBy) + "is less than " + (Integer)((Book)b).attributeMap.get(attributeToSortBy));
				comparatorValue = -1;
			}
			else
			{
				comparatorValue = 1;
			}
		}
	}
	
	
	return comparatorValue;	
}

/**
 * This function takes a LinkedList -> preferably of books, clothes, etc. It will return the linkedlist in order of whatever attribute you provide 
 *
 * @param LinkedList a
 * @param boolean ascending
 * @param String attributeToSortBy
 * @return
 */
public static LinkedList sort(LinkedList a, boolean ascending, String attributeToSortBy)
{
	Item[] array = convertToArray(a);
	
	for(int i=0; i<a.size(); i++)
	{
		int k = i;
		if(ascending == true)
		{
			
			while( k > 0 && customComparator(array[k], array[k-1], attributeToSortBy) <= 0)			
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
			//array[k-1].getTitle().compareTo(array[k].getTitle())
			while( k > 0 && customComparator(array[k-1], array[k], attributeToSortBy) <= 0)			
			{
				//System.out.println(k);
				Item temp = array[k];
				array[k] = array[k-1];
				array[k-1] = temp;
				k--;
			}	
		}
	}
	
	
	LinkedList listToReturn = convertToLinkedList(array);
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
	if (result == 3 || result == 2) // someone hits the sort ascending button (3) or descending (2)
	{
		boolean ascending = false;
		if(result == 3)
			ascending = true;
		else if(result == 2)
			ascending = false;
			
		if(whatItemType.equals("Book"))
		{
			books = sort(books, ascending, sortCriteria);	
			viewItems("Book");
		}			
		else if (whatItemType.equals("Clothing"))
		{
			clothes = sort(clothes, ascending, sortCriteria);
			viewItems("Clothing");
		}			
		else if (whatItemType.equals("Toy"))
		{
			toys = sort(toys, ascending, sortCriteria);
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