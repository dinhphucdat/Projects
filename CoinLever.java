
/**
 * The CoinLever class models on the actual coin or bill tray for the customers to put their money on. 
 * Whose instance is the only object where customers can do stuff with money.
 * @author Dat Dinh
 * @version Version 2.
 * Assignment 4
 * Due Date: 11/06/24
 * No sources used
 * No help obtained
 * We confirm that the above list of sources is complete AND that we have not talked to 
 * anyone else about the solution to this problem.
 */
public class CoinLever {
	
	/**
	 * a CoinBox object (for payment) connected with this
	 */
	private CoinBox payCoin;
	/**
	 * a CoinBox object (for change) connected with this
	 */
	private CoinBox changeCoin;
	/**
	 * this field facilitates the report of current transaction only with cents
	 */
	private int onlyCoinPayTotal;
	/**
	 * this field facilitates the report of change made in number of quarter coins
	 */
	private int quartersOut;
	/**
	 * this field facilitates the report of change made in number of dime coins
	 */
	private int dimesOut;
	/**
	 * this field facilitates the report of change made in number of nickel coins
	 */
	private int nickelsOut;
	
	/**
	 * Constructor: takes one Billbox object and other 2 CoinBox objects
	 * @param payCoin CoinBox, for payment
	 * @param changeCoin CoinBox, for change
	 */
	public CoinLever(CoinBox payCoin, CoinBox changeCoin)
	{
		this.payCoin = payCoin;
		this.changeCoin = changeCoin;
	}
	
	/**
	 * If the users accidentally do not pass in any object
	 */
	public CoinLever()
	{
		this(null, null);
	}
	
	/**
	 * serves as a tray for payment, distributes each type into its appropriate storing object
	 * @param quarters number of quarter cents
	 * @param dimes number of dime cents
	 * @param nickels number of nickel cents
	 * @throws NullPointerException when change coin is not found
	 */
	public void payMoney(int quarters, int dimes, int nickels) throws NullPointerException
	{
		//allocates quarter cents into payment CoinBox
		payCoin.acceptQuarter(quarters);
		//allocates dime cents into payment CoinBox
		payCoin.acceptDime(dimes);
		//allocates nickel cents into payment CoinBox
		payCoin.acceptNickel(nickels);
		//reports the current transaction. Note that after every transaction, 
		//the payment CoinBox object is refreshed so we can safely assume that 
		//the total amount of money in the current transaction is equal to 
		//the total amount of current bills plus all coins in the payment CoinBox.
		this.onlyCoinPayTotal = payCoin.reportTotalCent();
		//immediately transfers all coins from payment box to change box.
		payCoin.transferToAnother(changeCoin);
	}
	
	/**
	 * Reports the total cents out of total money paid in the current transaction
	 * @return total coins value in cents
	 */
	public int getThisTransactionWithCents()
	{
		return this.onlyCoinPayTotal;
	}
	
	/**
	 * uses to roll the coins out in case the machine has to make a change 
	 * and stores the change value in memory
	 * @param quarters number of quarters
	 * @param dimes number of dimes
	 * @param nickels number of nickels
	 */
	public void decrementCoins(int quarters, int dimes, int nickels)
	{
		//attempts to make change
		changeCoin.decrementQuarter(quarters);
		changeCoin.decrementDime(dimes);
		changeCoin.decrementNickel(nickels);
		//assigns the change value to the field responsible for storing the current change, 
		//used for reporting the change
		this.quartersOut = quarters;
		this.dimesOut = dimes;
		this.nickelsOut = nickels;
	}
	
	/**
	 * Reports the coins out in each type, such as when making a change or having to roll the money back
	 * @return message
	 */
	public String reportCoinOut()
	{
		return String.format("Change made: %d quarter(s), %d dime(s), %d nickel(s).", quartersOut, 
				dimesOut, nickelsOut);
	}
	
	/**
	 * returns a String telling the number left of three kinds of coins in the change box
	 */
	@Override
	public String toString()
	{
		return String.format("Quarters: %d; Dimes: %d; Nickels: %d", changeCoin.getQuarter(), changeCoin.getDime(), changeCoin.getNickel());
	}


}
