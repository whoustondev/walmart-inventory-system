import java.util.HashMap;
import java.util.Map;

public class Clothing extends Item
{
	
	private String brand;
	private String color;
	private int qtySmall;
	private int qtyMedium;
	private int qtyLarge;
	private int qtyExtraLarge;
	private int totalQty;
	public static String [] childClassAttributes = {"ItemId", "Title","Brand","Color", "Qty Small", "Qty Medium", "Qty Large", "Qty XL"};
	Map<String, Object> attributeMap = new HashMap<String, Object>();
	public Clothing(String title, String brand, String color, int qtySmall, int qtyMedium, int qtyLarge, int qtyExtraLarge)
	{
		attributeMap.put("ItemId", this.getId());
		
		attributeMap.get("Title");
		this.setTitle(title);
		this.setType("Clothing");
		this.brand = brand;
		this.color = color;
		this.qtySmall = qtySmall;
		this.qtyMedium = qtyMedium;
		this.qtyLarge = qtyLarge;
		this.qtyExtraLarge  = qtyExtraLarge;
		this.totalQty = qtySmall + qtyMedium + qtyLarge + qtyExtraLarge;
		attributeMap.put("Title", title);
		attributeMap.put("Color", color);
		attributeMap.put("Brand", brand);
		attributeMap.put("Qty Small", qtySmall);
		attributeMap.put("Qty Medium", qtyMedium);
		attributeMap.put("Qty Large", qtyLarge);
		attributeMap.put("Qty XL", qtyExtraLarge);
		
	}
	

	

	public void setBrand(String brand)
	{
		this.brand = brand;
	}
	
	public String getBrand()
	{
		return brand;
	}
	
	public void setColor(String color)
	{
		this.color = color;
	}
	
	public String getColor()
	{
		return color;
	}
	
	public void setQtySmall(int n)
	{
		this.qtySmall = n;
	}
	
	public int getQtySmall()
	{
		return qtySmall;
	}
	
	public void setQtyMedium(int n)
	{
		this.qtyMedium = n;
	}
	
	public int getQtyMedium()
	{
		return qtyMedium;
	}
	public void setQtyLarge(int n)
	{
		this.qtyLarge = n;
	}
	
	public int getQtyLarge()
	{
		return qtyLarge;
	}
	public void setQtyExtraLarge(int n)
	{
		this.qtyExtraLarge = n;
	}
	
	public int getQtyExtraLarge()
	{
		return qtyExtraLarge;
	}	
	
	/**
	 * This is a function that returns an attribute to an index. 
	 * See the childClassAttributes array. Title is index 0. Brand is index 1. Color is index 3. (and so forth)
	 * This function is to be used in conjunction with the view<Items> methods - It is necessary for creating a Jtable also it appears..
	 * For example, view books uses it. viewClothes as well
	 * @param index
	 * @return
	 */
	
	public Object getAttributeByIndex(int index)
	{
		
		if(index == 0)
			return getId();
		else if(index == 1)
			return getTitle();
		else if(index == 2)
			return getBrand();
		else if(index == 3)
			return getColor();
		else if(index == 4)
			return getQtySmall();
		else if(index == 5)
			return getQtyMedium();
		else if(index == 6)
			return getQtyLarge();
		else if(index == 7)
			return getQtyExtraLarge();
		
		return null;
	}
	
	public String toString()
	{
		return this.getType()+"; " +this.getId() + "; " + this.getTitle()+"; "+this.getBrand()+"; "+this.getColor()+"; "+this.getQtySmall()+"; "+this.getQtyMedium()+"; "+this.getQtyLarge()+"; "+this.getQtyExtraLarge()+"\n";	
	}
}
