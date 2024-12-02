import java.util.*;
import java.io.*;
/**
 * The VendingMachine class models on the vending machine in real life.
 * @author Dat Dinh
 * @version Version 2
 * Assignment 4
 * Due Date: 11/06/24
 * No sources used
 * No help obtained
 * We confirm that the above list of sources is complete AND that we have not talked to 
 * anyone else about the solution to this problem.
 */
public class VendingMachine {
	/**
	 * this is the one dollar bill box
	 */
	private BillBox oneDollarBills;
	/**
	 * this is the coin box for payment
	 */
	private CoinBox payCoins;
	/**
	 * this is the coin box for giving back the change
	 */
	private CoinBox changeCoins;
	/**
	 * this contains the messages to display to the customers
	 */
	private Display display;
	/**
	 * this coin tray is used if the customer pays by coins or the machine has to give back the change
	 */
	private CoinLever coinTray;
	/**
	 * 3 brand inventories of the machine
	 */
	private BrandInventory[] brands;
	/**
	 * Constructor: receives the BrandInventory config file and a CoinBox for making change
	 * @param BrandTable brand file name
	 * @param changeCoins coin box for change
	 */
	public VendingMachine(String BrandTable, CoinBox changeCoins)
	{
		//display object
		display = new Display();
		//create a brand tray and fill that brands with config file
		brands = new BrandInventory[6];
		fillBrands(BrandTable);
		//bill box of the machine
		oneDollarBills = new BillBox();
		//coin box of the machine
		payCoins = new CoinBox();
		//change coin box from outside
		this.changeCoins = changeCoins;
		//provide a slut for the customers to do stuff with coins
		coinTray = new CoinLever(payCoins, this.changeCoins);
	}
	/**
	 * fills the BrandInventory array with information provided in the config file
	 * @param BrandTable brand file name
	 */
	private void fillBrands(String BrandTable)
	{
		try (Scanner scanBrands = new Scanner(new File(BrandTable)))
		{
			// iterator for brand array
			int brandSlot = 0;
			// condition: brand file still has next line and array is still in bound
			while (scanBrands.hasNextLine() && brandSlot < brands.length)
			{
				// get a single line in the file
				String aBrand = scanBrands.nextLine();
				BrandInventory newBrand;
				try (Scanner scanSingle = new Scanner(aBrand))
				{
					// first field: ID
					int id = scanSingle.nextInt();
					// next field: price
					double price = scanSingle.nextDouble();
					// final field: numSupplies
					int numSupplies = scanSingle.nextInt();
					// call BrandInventory constructor
					newBrand = new BrandInventory(id, price, numSupplies);
					// add into the brand array
					brands[brandSlot] = newBrand;
					// increase the iterator by 1 if this adding process succeeds
					brandSlot++;
				}
			}
		}
		catch (FileNotFoundException e)
		{
			display.brandFileNotFound();
			System.exit(0);
		}
		catch (InputMismatchException e)
		{
			display.brandInputMismatch();
			System.exit(0);
		}
		catch (ArrayIndexOutOfBoundsException e)
		{
			display.brandArrayOutBound();
			System.exit(0);
		}
		catch (Exception e)
		{
			display.brandOtherException();
			System.exit(0);
		}
	}
	
	/**
	 * reports how many of 3 types of coins left in the change box, for diagnostic test
	 * @return message
	 */
	public String reportCoinsLeftInChangeBox()
	{
		return coinTray.toString();
	}
	
	/**
	 * reports the change made in each type of coins
	 * @return message
	 */
	public String reportChangeMade()
	{
		return coinTray.reportCoinOut();
	}
	
	/**
	 * finds brand by ID
	 * @param id desired ID
	 * @return a BrandInventory object
	 */
	private BrandInventory matchBrand(int id)
	{
		for (BrandInventory brand : brands)
		{
			if (brand != null && id == brand.getBrandID())
			{
				return brand;
			}
		}
		throw new IllegalArgumentException();
	}
	
	/**
	 * checks if item exists
	 * @param id ID number
	 * @return boolean, if the brand exists or has items
	 */
	public boolean hasItems(int id)
	{
		try
		{
			BrandInventory brand = matchBrand(id);
			if (brand.getStatusIfRemain()) return true;
			else return false;
		}
		catch (IllegalArgumentException e)
		{
			return false;
		}
	}
	/**
	 * get the price from the brand specified by the ID
	 * @param id ID number
	 * @return price in dollars
	 */
	public double getPriceFromBrand(int id)
	{
		BrandInventory brand = matchBrand(id);
		return brand.getPrice();
	}
	/**
	 * pays without making changee
	 * @param billPaid bills paid
	 * @param quarterPaid quarters paid
	 * @param dimePaid dimes paid
	 * @param nickelPaid nickels paid
	 */
	public void payNoChange(int billPaid, int quarterPaid, int dimePaid, int nickelPaid)
	{
		// Accept bill payment into the bill box object
		oneDollarBills.receiveOneDollar(billPaid);
		//Accept coin payment into the money tray (CoinLever object)
		coinTray.payMoney(quarterPaid, dimePaid, nickelPaid);
	}
	/**
	 * pays with making change
	 * @param billPaid bills paid
	 * @param quarterPaid quarters paid
	 * @param dimePaid dimes paid
	 * @param nickelPaid nickels paid
	 * @param changeToMakeCents change amount to make, in cents
	 */
	public void payWithChange(int billPaid, int quarterPaid, int dimePaid, int nickelPaid, int changeToMakeCents)
	{
		// Accept bill payment into the bill box object
		oneDollarBills.receiveOneDollar(billPaid);
		//Accept coin payment into the money tray (CoinLever object)
		coinTray.payMoney(quarterPaid, dimePaid, nickelPaid);
		//make the change
		makeChange(changeToMakeCents, quarterPaid, dimePaid, nickelPaid);
	}
	/**
	 * reduces the brand inventory after a purchase
	 * @param id ID number
	 */
	public void brandReduceInventory(int id)
	{
		BrandInventory currentBrand = matchBrand(id);
		currentBrand.reduceInventory(1);
	}
	
	/**
	 * attempts to check if the machine is able to make change
	 * @param changeInCents change to make, in cents
	 * @param payerQuarters quarters paid
	 * @param payerDimes dimes paid
	 * @param payerNickels nickels paid
	 * @return boolean value
	 */
	public boolean isAbleChange(int changeInCents, int payerQuarters, int payerDimes, int payerNickels)
	{
		// get the initial number of coins in the change box. 
		// these variables simulate a clone of a real change box so that 
		// this checking process would not affect the real change box itself
		// TODO: New Update: this also adds up with the amount paid in coins
		// from the customers, so that this also takes into account the scenario 
		// when the change box doesn't have anything but the customer just paid 
		// an amount of coins.
		int numQuar = changeCoins.getQuarter() + payerQuarters;
		int numDime = changeCoins.getDime() + payerDimes;
		int numNick = changeCoins.getNickel() + payerNickels;
		// General algorithm: prioritize the coin type with highest dollar value, 
		// until it can no longer make change, then shift to the one with the next lower value,
		// then keep going until the change is fully compensated or impossible to be made (though it is unlikely, 
		// oftentimes when the change is not divisible by the dollar values of all coin types in the change CoinBox).
		while (changeInCents >= CoinBox.CentEquivalent.QUARTER.getCent() && numQuar > 0)
		{
			changeInCents -= CoinBox.CentEquivalent.QUARTER.getCent();
			numQuar--;
		}
		while (changeInCents >= CoinBox.CentEquivalent.DIME.getCent() && numDime > 0)
		{
			changeInCents -= CoinBox.CentEquivalent.DIME.getCent();
			numDime--;
		}
		
		while (changeInCents >= CoinBox.CentEquivalent.NICKEL.getCent() && numNick > 0)
		{
			changeInCents -= CoinBox.CentEquivalent.NICKEL.getCent();
			numNick--;
		}
		// if exact change is achieved.
		return changeInCents == 0;
	}
	
	/**
	 * tries to make the change if every condition is satisfied
	 * @param changeInCents change to make, in cents
	 * @param payerQuarters quarters paid
	 * @param payerDimes dimes paid
	 * @param payerNickels nickels paid
	 */
	public void makeChange(int changeInCents, int payerQuarters, int payerDimes, int payerNickels)
	{
		//throws exception if the machine cannot make the change, used for JUnit test, though practically impossible.
		if (!isAbleChange(changeInCents, payerQuarters, payerDimes, payerNickels)) 
			throw new IllegalArgumentException("can't make change!");
		//accumulates the number of each coin that should be dispensed as change.
		int numQuarterOut = 0;
		int numDimeOut = 0;
		int numNickelOut = 0;
		//does the same thing as did the isAbleChange()
		int numQuar = changeCoins.getQuarter();
		int numDime = changeCoins.getDime();
		int numNick = changeCoins.getNickel();
		//General algorithm: prioritize the coin type with highest dollar value, 
		//until it can no longer make change, then shift to the one with the next lower value,
		//then keep going until the change is fully compensated.
		while (changeInCents >= CoinBox.CentEquivalent.QUARTER.getCent() && numQuar > 0)
		{
			changeInCents -= CoinBox.CentEquivalent.QUARTER.getCent();
			numQuar--;
			numQuarterOut++;
		}
		
		while (changeInCents >= CoinBox.CentEquivalent.DIME.getCent() && numDime > 0)
		{
			changeInCents -= CoinBox.CentEquivalent.DIME.getCent();
			numDime--;
			numDimeOut++;
		}
		
		while (changeInCents >= CoinBox.CentEquivalent.NICKEL.getCent() && numNick > 0)
		{
			changeInCents -= CoinBox.CentEquivalent.NICKEL.getCent();
			numNick--;
			numNickelOut++;
		}
		//after everything is set, it goes ahead and decrement the money in 
		//the change box through the CoinLever object.
		coinTray.decrementCoins(numQuarterOut, numDimeOut, numNickelOut);
		
	}
	
	/**
	 * reports information about all brands
	 * @return String message
	 */
	public String reportBrands()
	{
		StringBuilder reportLine = new StringBuilder();
		for (BrandInventory brand : brands)
		{
			if (brand != null)
				reportLine.append(String.format("%s%n", brand.toString()));
		}
		return reportLine.toString();
	}
}
