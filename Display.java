
/**
 * The Display class models on the messages displayed to customers.
 * @author Dat Dinh
 * @version Version 2.
 * Assignment 4
 * Due Date: 11/06/24
 * No sources used
 * No help obtained
 * We confirm that the above list of sources is complete AND that we have not talked to 
 * anyone else about the solution to this problem.
 */
public class Display {
	
	// unchanged messages are stored as constants
	/**
	 * introductory message
	 */
	public static final String INTRODUCTORY_MESSAGE = "Welcome to our service! Buy some drinks????? (y for yes and anything else for no)";
	/**
	 * successful transaction message
	 */
	public static final String SUCCESSFUL_PURCHASE = "Transaction has been successful! You're all set!";
	/**
	 * when item is sold out or wrong ID is obtained
	 */
	public static final String ITEM_SOLDOUT_OR_WRONG_ID = "Sorry, your item has been sold out or ID not exists";
	/**
	 * prompt to choose ID
	 */
	public static final String CHOOSE_ID = "Enter the ID of the drink you want to buy";
	/**
	 * We have successfully given back the change!
	 */
	public static final String ENOUGH_CHANGE = "We have successfully given back the change!";
	/**
	 * change prompt
	 */
	public static final String CHANGE_PROMPT = "Please take your change!";
	/**
	 * when lacks change
	 */
	public static final String LACK_CHANGE = "Sorry, we don't have enough coins to make up the change.";
	/**
	 * a transaction fails
	 */
	public static final String FAIL_TRANSACTION = "Sorry, your transaction is not successful.";
	/**
	 * when customer does not pay enough money
	 */
	public static final String PAID_NOT_ENOUGH = "The amount you paid is not enough.";
	/**
	 * reports current deposited amount
	 */
	public static final String DEPOSITED = "Deposited: ";
	/**
	 * asks for deposit
	 */
	public static final String ASK_FOR_DEPOSIT = "Please insert \"bill\" or \"quarter\" or \"dime\" or \"nickel\".";
	/**
	 * ending message
	 */
	public static final String END = "Thank you for using our service! Have a good one";
	/**
	 * when typing a wrong ID
	 */
	public static final String ERROR_ASK_ID = "Please type in a correct response.";
	/**
	 * brand file config not found
	 */
	public static final String BRAND_FILE_NOT_FOUND = "Brand configuring file not found.";
	/**
	 * input in the brand config file not formatted correctly
	 */
	public static final String BRAND_INPUT_MISMATCH = "Order on each line is: id (int), price (double, in dollar), and numSupplies (int).";
	/**
	 * brand array is out of slot
	 */
	public static final String BRAND_ARRAY_OUTBOUND = "Number of brands exceed maximum capacity.";
	/**
	 * other exception for BrandInventory
	 */
	public static final String BRAND_OTHER_EXCEPTION = "Brand reading not successful.";
	/**
	 * coin file config not found
	 */
	public static final String COIN_FILE_NOT_FOUND = "Coin configuring file not found.";
	/**
	 * input in the coin config file not formatted correctly
	 */
	public static final String COIN_FORMAT_UNSUPPORTED = "Format: <COINTYPES> <INITIAL_AMOUNTS>.";
	/**
	 * other exception for CoinBox
	 */
	public static final String COIN_OTHER_EXCEPTION = "Coin figuration reading not successful.";
	/**
	 * brand file is not found
	 */
	public void brandFileNotFound()
	{
		System.err.println(BRAND_FILE_NOT_FOUND);
	}
	/**
	 * brand file is not correctly formatted
	 */
	public void brandInputMismatch()
	{
		System.err.println(BRAND_INPUT_MISMATCH);
	}
	/**
	 * VendingMachine's BrandInventory array is out of slot
	 */
	public void brandArrayOutBound()
	{
		System.err.println(BRAND_ARRAY_OUTBOUND);
	}
	/**
	 * other exceptions for BrandInventory
	 */
	public void brandOtherException()
	{
		System.err.println(BRAND_OTHER_EXCEPTION);
	}
	/**
	 * coin config file is not found
	 */
	public void coinFileNotFound()
	{
		System.err.println(COIN_FILE_NOT_FOUND);
	}
	/**
	 * coin file is not correctly formatted
	 */
	public void coinFormatUnsupported()
	{
		System.err.println(COIN_FORMAT_UNSUPPORTED);
	}
	/**
	 * other exceptions for CoinBox
	 */
	public void coinOtherException()
	{
		System.err.println(COIN_OTHER_EXCEPTION);
	}
	
	/**
	 * displays the message when the purchase starts
	 */
	public void startPurchase()
	{
		System.out.println(INTRODUCTORY_MESSAGE);
	}
	/**
	 * displays all BrandInventory collection, and prompts customers to choose one
	 * @param brandToString brand collection in String
	 */
	public void displaySelectionsEnterID(String brandToString)
	{
		System.out.println("Current selection: ");
		System.out.println(brandToString);
		System.out.println(CHOOSE_ID);
	}
	
	/**
	 * displays after the customer chooses their drinks
	 * @param price the price of the drinks
	 */
	private void reportPrice(double price)
	{
		System.out.print(String.format("Price: $%.2f. ", price));
	}
	/**
	 * reports current deposited, prompts for payment
	 * @param price actual brand price
	 * @param deposited already deposited
	 */
	public void askPayReportDeposited(double price, double deposited)
	{
		reportPrice(price);
		System.out.println(String.format(DEPOSITED + "$%.2f", deposited));
		System.out.println(ASK_FOR_DEPOSIT);
	}
	
	/**
	 * displays when the item customer likes is sold out or customer typed in wrong ID
	 */
	public void soldOutOrWrongID()
	{
		System.out.println(ITEM_SOLDOUT_OR_WRONG_ID);
	}
	
	/**
	 * displays after a transaction is completed
	 */
	public void successfulTransaction()
	{
		System.out.println(SUCCESSFUL_PURCHASE);
	}
	
	/**
	 * displays two different messages depending on whether the machine has enough coins to make change
	 * @param state if the machine has enough change to make
	 */
	public void ifHaveEnoughChange(boolean state)
	{
		String print = (state) ? ENOUGH_CHANGE : LACK_CHANGE;
		System.out.println(print);
	}
	/**
	 * prompts users to take the change
	 * @param changeString String information of change made
	 */
	public void displayChange(String changeString)
	{
		System.out.println(CHANGE_PROMPT);
		System.out.println(changeString);
	}
	/**
	 * displays when a transaction fails
	 */
	public void whenFail()
	{
		System.out.println(FAIL_TRANSACTION);
	}
	
	/**
	 * displays when users don't give enough payment
	 * @return message
	 */
	public String notEnoughPay()
	{
		return PAID_NOT_ENOUGH;
	}
	/**
	 * when customers do not want to buy anymore
	 */
	public void endTheMachine()
	{
		System.out.println(END);
	}
	/**
	 * wrong ID
	 */
	public void errorWhenTransactTyping()
	{
		System.out.println(ERROR_ASK_ID);
	}

}
