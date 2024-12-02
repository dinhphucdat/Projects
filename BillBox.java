/**
 * The BillBox class models on the box containing $1 bills.
 * @author Dat Dinh
 * @version Version 2.
 * Assignment 4
 * Due Date: 11/06/24
 * No sources used
 * No help obtained
 * We confirm that the above list of sources is complete AND that we have not talked to 
 * anyone else about the solution to this problem.
 */
public class BillBox {
	
	/**
	 * the main field that stores the total of bills paid
	 */
	private int numOneDollar;
	/**
	 * the field that is used for current transaction report
	 */
	private int currentBills;
	
	/**
	 * Constructor: assigns every field to be 0
	 */
	public BillBox()
	{
		numOneDollar = 0;
		currentBills = 0;
	}
	
	/**
	 * adds a specified amount of bills into the total bills
	 * @param numBills amount of bills paid
	 */
	public void receiveOneDollar(int numBills)
	{
		if (numBills < 0) throw new IllegalArgumentException("Positive bill value is required.");
		this.currentBills = numBills;
		this.numOneDollar += currentBills;
	}
	
	/**
	 * reports the current transaction
	 * @return current transaction
	 */
	public int reportCurrentTransaction()
	{
		return currentBills;
	}
	


}
