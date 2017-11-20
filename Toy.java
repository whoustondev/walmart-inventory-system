import java.util.HashMap;
import java.util.Map;

public class Toy extends Item
{
	private int recommendedAge;
	public static String [] childClassAttributes = {"Title","Quantity","Recommended Age"};
	Map<String, Object> attributeMap = new HashMap<String, Object>();
	public Toy(String title, int quantity, int recommendedAge )
	{
		this.setQuantity(quantity);
		this.recommendedAge = recommendedAge;
		this.setTitle(title);
		this.setType("Toy");		
		attributeMap.put("Title", title);
		attributeMap.put("Recommended Age", recommendedAge);
		attributeMap.put("Quantity", quantity);

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
	
	public String toString()
	{
		return this.getType()+"; " +this.getTitle()+"; "+this.getQuantity()+"; "+this.getRecAge()+"\n";	
	}

}
