
/**
 * The BrandInventory class models on the tray containing drinks of a single brand.
 * @author Dat Dinh
 * @version Version 2.
 * Assignment 4
 * Due Date: 11/06/24
 * No sources used
 * No help obtained
 * We confirm that the above list of sources is complete AND that we have not talked to 
 * anyone else about the solution to this problem.
 */
public class BrandInventory {
	
	/**
	 * ID number of the brand
	 */
	private int brandID;
	/**
	 * Price per can
	 */
	private double price;
	/**
	 * Number still in stock
	 */
	private int numInStock;
	
	/**
	 * Constructor: assigns is, price, and initial amount of drinks
	 * @param id ID number
	 * @param price price
	 * @param numSupplies number of drinks to supply initially
	 */
	public BrandInventory(int id, double price, int numSupplies)
	{
		if (numSupplies < 0) throw new IllegalArgumentException("Positive numSupplies is required.");
		brandID = id;
		this.price = price;
		numInStock = numSupplies;
	}
	
	/**
	 * Constructor: does the same thing as the one with 
	 * 3 parameters, but assigns number in stock to 0 
	 * if the user does not specify the last argument
	 * @param id ID number
	 * @param price price
	 */
	public BrandInventory(int id, double price)
	{
		this(id, price, 0);
	}
	
	/**
	 * This happens when the suppliers want to provide the tray 
	 * with more drinks
	 * @param addMore number of supplying drinks, postive number
	 * @throws IllegalArgumentException if addMore is negative
	 */
	public void supplyWithAdditionalDrinks(int addMore)
	{
		if (addMore < 0) throw new IllegalArgumentException("Positive supply value is required.");
		numInStock += addMore;
	}
	
	/**
	 * reduces the tray when some drinks are sold
	 * @param numSold number of sold drinks, positive number
	 * @throws IllegalArgumentException if numSold exceeds num in stock
	 */
	public void reduceInventory(int numSold)
	{
		if (numInStock >= numSold)
			numInStock -= numSold;
		else throw new IllegalArgumentException("numInStock has to be >=0.");
	}
	
	/**
	 * gets the ID
	 * @return ID 
	 */
	public int getBrandID()
	{
		return brandID;
	}

	/**
	 * gets the price
	 * @return price
	 */
	public double getPrice()
	{
		return price;
	}
	
	/**
	 * gets the number of drinks remained in stock
	 * @return number of drinks remained in stock
	 */
	public int getNumInStock()
	{
		return numInStock;
	}
	/**
	 * determines if there is any drink still in stock
	 * @return boolean representing if there is still any drink remains.
	 */
	public boolean getStatusIfRemain()
	{
		return numInStock > 0;
	}
	
	/**
	 * gets the idea of what the object looks like
	 */
	@Override
	public String toString()
	{
		String idNPrice = String.format("ID: %d | Price: $%.2f | ", brandID, price);
		String status = (getStatusIfRemain()) ? String.format("In stock: %d", numInStock) : "Out of stock";
		return idNPrice.concat(status);
	}

}
