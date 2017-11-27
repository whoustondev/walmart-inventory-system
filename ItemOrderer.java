import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.*;
import java.io.*;
import java.util.*;

/**
 *  I decided to start splitting out the code into more modules.
 *  This module is specifically for ordering a new item.
 *  It takes a walmart system, but it doesn't need it if this isn't being used in a walmartsystem context. 
 *  
 * @author whouston
 *
 */

public class ItemOrderer 

{
	LinkedList items;
	String type;
	WalmartSystem system; // we  need a reference to the system so we can go back and 
	/**
	 * To be honest, all you need to use ItemOrderer is a filepath (database) and a LinkedList of Books, Clothes, or Toys
	 * Walmart system is just to loop back. 
	 * @param system
	 * @param a
	 * @param filepath
	 */
	public ItemOrderer(WalmartSystem system, LinkedList a, String filepath)
	{
		this.system = system; // so we can access this object's main menu. 
		if(a.get(0) instanceof Clothing)
		{
			items = new LinkedList<Clothing>();
			//items = new LinkedList<Clothing>();
			type = "Clothing";
			items = a;
		}
		else if(a.get(0) instanceof Toy)
		{
			items = new LinkedList<Toy>();
			type = "Toy";
			items = a;
		}
		else if(a.get(0) instanceof Book)
		{
			items = new LinkedList<Book>();
			type = "Book";
			items = a;
		}
		
	}
	


	public void orderItemPanel()
	{
		JComboBox itemDropdown;
		JPanel panel = new JPanel();
		Object[] options = { "Exit", "Main Menu", "Order Items"};
		
		JTextField quantityToOrder = new JTextField(10);
		itemDropdown = new JComboBox(items.toArray());
		panel.add(itemDropdown);
		panel.add(quantityToOrder);
		for (Item item : items)
			((Book)items.get(0)).getTitle();
		if(type.equals("Clothing"))
		{
			itemDropdown = new JComboBox(items.toArray());
		}
		else if (type.equals("Toy"))
		{
			
			itemDropdown = new JComboBox(items.toArray());
		}
		else if (type.equals("Book"))
		{
			itemDropdown = new JComboBox(items.toArray());
		}
		
		int result = JOptionPane.showOptionDialog(null, panel, "Main Menu", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);

		if (result == 2)
		{
			System.out.println("Order Item");
			orderItemPanel();

		}
		else if (result == 1)
		{
			System.out.println("Main Menu");
			if(system!=null)
				system.mainMenu();
			else
				badInputDialog();

		}
		else if (result == 0)
		{
			System.exit(0);
		}
		return;	
		
	}
	
	public void badInputDialog()
	{
		Object[] options = { "Exit", "Order more stuff"};
		JPanel panel = new JPanel();
		panel.add(new Label("This instance is not associated with a System. Can't go to main Menu"));
		int result = JOptionPane.showOptionDialog(null, panel, "Main Menu", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);
		if (result == 1)
		{
			
			orderItemPanel();

		}
		else if (result == 0)
		{
			System.exit(0);
		}
	}

}
