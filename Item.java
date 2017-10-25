
public abstract class Item 
{
	private int quantity;
	private String type;
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
	
}
