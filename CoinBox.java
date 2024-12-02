import java.util.*;
import java.io.*;
/**
 * The CoinBox class models on the box containing all kinds of coins.
 * @author Dat Dinh
 * @version Version 2.
 * Assignment 4
 * Due Date: 11/06/24
 * No sources used
 * No help obtained
 * We confirm that the above list of sources is complete AND that we have not talked to 
 * anyone else about the solution to this problem.
 */
public class CoinBox {
	
	/**
	 * total number of quarter coins
	 */
	private int numQuarter;
	/**
	 * total number of dime coins
	 */
	private int numDime;
	/**
	 * total number of nickel coins
	 */
	private int numNickel;
	/**
	 * this enum stores the cent equivalent of each type of coin
	 */
	public enum CentEquivalent
	{
		/**
		 * quarter's cent equivalent
		 */
		QUARTER(25), 
		/**
		 * dime's cent equivalent
		 */
		DIME(10),
		/**
		 * nickel's cent equivalent
		 */
		NICKEL(5);
		
		private int centValue;
		/**
		 * Constructor: assigns the private field with cent equivalent
		 * @param value
		 */
		private CentEquivalent(int value)
		{
			centValue = value;
		}
		/**
		 * returns the cent equivalent of each coin type
		 * @return cent equivalent
		 */
		int getCent()
		{
			return centValue;
		}
	}
	/**
	 * reads the coin configuring file, assign some initial number of coins for change box
	 * @param configCoinFile coin file name
	 * @throws IllegalArgumentException when file does not config for only those 3 types of coins
	 */
	private void configCoins(String configCoinFile)
	{
		try (Scanner scanCoins = new Scanner(new File(configCoinFile)))
		{
			while (scanCoins.hasNextLine())
			{
				// reads each line in the file
				String scanLine = scanCoins.nextLine();
				try (Scanner scanSingle = new Scanner(scanLine))
				{
					// first field: coin type name
					String coinType = scanSingle.next();
					switch (coinType)
					{
					case "QUARTERS":
						// next field: coin type value
						int quarter = scanSingle.nextInt();
						acceptQuarter(quarter);
						break;
					case "DIMES":
						// next field: coin type value
						int dime = scanSingle.nextInt();
						acceptDime(dime);
						break;
					case "NICKELS":
						// next field: coin type value
						int nickel = scanSingle.nextInt();
						acceptNickel(nickel);
						break;
					default:
						// if format is not supported, throws.
						throw new IllegalArgumentException("Coin types CAPITAL, plural, "
								+ "only 3 types: QUARTERS, DIMES, NICKELS with a value follows.");
					}
				}
			}
		}
		catch (FileNotFoundException e)
		{
			new Display().coinFileNotFound();
			System.exit(0);
		}
		catch (InputMismatchException e)
		{
			new Display().coinFormatUnsupported();
			System.exit(0);
		}
		catch (Exception e)
		{
			new Display().coinOtherException();
			System.exit(0);
		}
	}
	/**
	 * Constructor: initializes the numbers of three coin types to 0; 
	 * reads the coin configuring file and assigns cent equivalent for 
	 * each type of coin.
	 * @param configFile coin file name
	 */
	public CoinBox(String configFile)
	{
		this.numDime = 0;
		this.numNickel = 0;
		this.numQuarter = 0;
		// read config file, assign cent equivalent
		configCoins(configFile);
	}
	/**
	 * Default constructor: initialize fields
	 */
	public CoinBox()
	{
		this.numDime = 0;
		this.numNickel = 0;
		this.numQuarter = 0;
	}
	/**
	 * receives the quarter coins
	 * @param numQuarterPaid number of coin, positive
	 * @throws IllegalArgumentException when negative
	 */
	public void acceptQuarter(int numQuarterPaid)
	{
		if (numQuarterPaid < 0) throw new IllegalArgumentException("coin paid >= 0");
		numQuarter += numQuarterPaid;
	}
	/**
	 * receives the dime coins
	 * @param numDimePaid number of coin, positive
	 * @throws IllegalArgumentException when negative
	 */
	public void acceptDime(int numDimePaid)
	{
		if (numDimePaid < 0) throw new IllegalArgumentException("coin paid >= 0");
		numDime += numDimePaid;
	}
	/**
	 * receives the nickel coins
	 * @param numNickelPaid number of coin, positive
	 * @throws IllegalArgumentException when negative
	 */
	public void acceptNickel(int numNickelPaid)
	{
		if (numNickelPaid < 0) throw new IllegalArgumentException("coin paid >= 0");
		numNickel += numNickelPaid;
	}
	
	/**
	 * used when we want to take some quarters out for change
	 * @param numQuarterDown number of coin, positive
	 * @throws IllegalArgumentException when greater than coin number
	 */
	public void decrementQuarter(int numQuarterDown)
	{
		if (numQuarter >= numQuarterDown)
			numQuarter -= numQuarterDown;
		else throw new IllegalArgumentException("negative coin number");
	}
	
	/**
	 * used when we want to take some dimes out for change
	 * @param numDimeDown number of coin, positive
	 * @throws IllegalArgumentException when greater than coin number
	 */
	public void decrementDime(int numDimeDown)
	{
		if (numDime >= numDimeDown)
			numDime -= numDimeDown;
		else throw new IllegalArgumentException("negative coin number");
	}
	
	/**
	 * used when we want to take some nickels out for change
	 * @param numNickelDown number of coin, positive
	 * @throws IllegalArgumentException when greater than coin number
	 */
	public void decrementNickel(int numNickelDown)
	{
		if (numNickel >= numNickelDown)
			numNickel -= numNickelDown;
		else throw new IllegalArgumentException("negative coin number");
	}
	
	/**
	 * reports the total number of quarter coins this object currently has
	 * @return total number of quarter coins
	 */
	public int getQuarter()
	{
		return numQuarter;
	}
	/**
	 * reports the total number of dime coins this object currently has
	 * @return total number of dime coins
	 */
	public int getDime()
	{
		return numDime;
	}
	/**
	 * reports the total number of nickel coins this object currently has
	 * @return total number of nickel coins
	 */
	public int getNickel()
	{
		return numNickel;
	}
	/**
	 * reports the total cent amount of this coin box
	 * @return total cent amount of this coin box
	 */
	public int reportTotalCent()
	{
		// converts quarters to dollars
		int QuartersInTotal = numQuarter * CentEquivalent.QUARTER.getCent();
		// converts dimes to dollars
		int DimesInTotal = numDime * CentEquivalent.DIME.getCent();
		// converts nickels to dollars
		int NickelsInTotal = numNickel * CentEquivalent.NICKEL.getCent();
		return QuartersInTotal + DimesInTotal + NickelsInTotal;
	}
	/**
	 * transfer all of the coins to another coin box
	 * @param anotherBox another coin box object
	 */
	public void transferToAnother(CoinBox anotherBox)
	{
		anotherBox.acceptQuarter(numQuarter);
		//we have to reset all coins to 0 because they have 
		//been transferred it their entirety to anther box
		numQuarter = 0;
		anotherBox.acceptDime(numDime);
		numDime = 0;
		anotherBox.acceptNickel(numNickel);
		numNickel = 0;
	}

}
