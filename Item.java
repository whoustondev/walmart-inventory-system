
public abstract class Item 
{
	private int quantity;
	private String type;
	private int totalItemsSold;
	
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
