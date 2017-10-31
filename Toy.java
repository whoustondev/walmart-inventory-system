
public class Toy extends Item
{
	private int recommendedAge;
	String name;
	
	public Toy(String name, int quantity, int recommendedAge )
	{
		this.setQuantity(quantity);
		this.recommendedAge = recommendedAge;
		this.name = name;
		this.setType("Toy");
		
		
	}
	public int getRecAge()
	{
		return recommendedAge;
	}
	public void setRecAge(int n)
	{
		recommendedAge = n;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String n)
	{
		name = n;
	}	
}
