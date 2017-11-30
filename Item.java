
public abstract class Item 
{
	private int quantity;
	private String type;
	private int totalItemsSold;
	private String title;
	private static int idCounter = 0;
	private int itemId;
	public void setTitle(String title)
	{
		this.title = title;
		idCounter++;
		this.itemId = idCounter;
		
		
	}
	public int getId()
	{
		return itemId;
	}

	public void setId(int id)
	{
		this.itemId = id;
	}
	/**
	 * YEah so we need this method because we are storing objects in a database with their IDs. We also need a way to keep track of the ID's that have been previously used.
	 * One way to do this, is to find the highest ID number and then start the creation process at that specific number. 
	 * @param a
	 */
	public static void setidCounter(int a)
	{
		idCounter = a;
	}
	public String getTitle()
	{
		return title;
	}
	public void setQuantity(int n)
	{
		this.quantity = n;
		
	}
	
	public int getQuantity()
	{
		return quantity;
	}
	public void setType(String a)
	{
		this.type = a;
		
	}
	
	public String getType()
	{
		return this.type;
	}
	
	public void setTotalItemsSold(int a)
	{
		this.totalItemsSold = a;
		
	}
	
	public int getTotalItemsSold()
	{
		return this.totalItemsSold;
	}
}
