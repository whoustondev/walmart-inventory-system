
public class Clothing extends Item
{
	private String name;
	private String brand;
	private String color;
	private int qtySmall;
	private int qtyMedium;
	private int qtyLarge;
	private int qtyExtraLarge;
	private int totalQty;
	
	public Clothing(String name, String brand, String color, int qtySmall, int qtyMedium, int qtyLarge, int qtyExtraLarge)
	{

		this.name = name;
		this.setType("Clothing");
		this.color = color;
		this.qtySmall = qtySmall;
		this.qtyMedium = qtyMedium;
		this.qtyLarge = qtyLarge;
		this.qtyExtraLarge  = qtyExtraLarge;
		this.totalQty = qtySmall + qtyMedium + qtyLarge + qtyExtraLarge;
	}
	
	public void setTitle(String title)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return name;
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
	
	
}
