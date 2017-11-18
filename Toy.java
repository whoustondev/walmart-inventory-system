
public class Toy extends Item
{
	private int recommendedAge;
	public static Object [] childClassAttributes = {"Title","Quantity","Recommended Age"};
	public Toy(String name, int quantity, int recommendedAge )
	{
		this.setQuantity(quantity);
		this.recommendedAge = recommendedAge;
		this.setTitle(name);
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
	public Object getAttributeByIndex(int index)
	{
		if(index == 0)
			return getTitle();
		else if(index == 1)
			return getQuantity();
		else if(index == 2)
			return getRecAge();
	
		
		return null;
	}

}
