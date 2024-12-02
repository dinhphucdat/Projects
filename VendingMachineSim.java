import java.util.*;
/**
 * The VendingMachineSim class acts as the physical controls of a vending machine, 
 * accepting bills or coins using the VendingMachine methods and its display 
 * object
 * @author Dat Dinh
 * @version Version 2
 * Assignment 4
 * Due Date: 11/06/24
 * No sources used
 * No help obtained
 * We confirm that the above list of sources is complete AND that we have not talked to 
 * anyone else about the solution to this problem.
 */
public class VendingMachineSim {
	/**
	 * the vending machine
	 */
	private VendingMachine machine;
	/**
	 * its Display object
	 */
	private Display display;
	/**
	 * its CoinBox for making change
	 */
	private CoinBox changeBox;
	/**
	 * Scanner object to read input from the customers
	 */
	private Scanner keyboard;
	/**
	 * Cents to Dollars
	 * @param cents
	 * @return
	 */
	private static double toDollars(int cents)
	{
		return cents / 100.0;
	}
	/**
	 * Constructors:
	 * TODO: create a Display object.
	 * TODO: create a CoinBox object with config provided in the coin config file.
	 * TODO: create a VendingMachine object with config provided in the 
	 * brand config file and with the CoinBox for making change.
	 * TODO: add some initial coins into the CoinBox for change.
	 * TODO: initialize the Scanner so users can type in information.
	 */
	public VendingMachineSim()
	{
		display = new Display();
		changeBox = new CoinBox("configCoins.dat");
		machine = new VendingMachine("configBrands.dat", changeBox);
		keyboard = new Scanner(System.in);
	}
	
	/**
	 * main method to call to demo a transaction
	 */
	public void demoTransaction()
	{
		// start the purchase message
		display.startPurchase();
		// asks if customer wants to buy drinks
		String yesNo = keyboard.nextLine();
		boolean doBuy = (yesNo.equals("y")) ? true : false;
		// keeps looping while customer still wants to buy stuff
		while (doBuy)
		{
			pay();
			display.startPurchase();
			yesNo = keyboard.nextLine();
			doBuy = (yesNo.equals("y")) ? true : false;
		}
		// ends when customer no longer wants to buy
		display.endTheMachine();
	}
	/**
	 * check if a String has only digits
	 * @param string a String to test
	 * @return boolean
	 */
	private boolean isDigit(String string)
	{
		for (int i = 0; i < string.length(); i++)
		{
			if (!Character.isDigit(string.charAt(i))) return false;
		}
		return true;
	}
	/**
	 * proceeds payment
	 */
	public void pay()
	{
		// shows selection, prompts for choice
		display.displaySelectionsEnterID(machine.reportBrands());
		// scan ID's choice
		String rawid = keyboard.next();
		// this is to guard against mistyping ID into some Strings not of digits
		while (!isDigit(rawid))
		{
			display.errorWhenTransactTyping();
			rawid = keyboard.next();
		}
		int id = Integer.parseInt(rawid);
		keyboard.nextLine();
		// when item does not exist
		if (!machine.hasItems(id))
		{
			display.soldOutOrWrongID();
			return;
		}
		// these are to keep looping until customer pays an amount that is 
		// at least equal to the price
		int paymentCents = 0;
		double price = machine.getPriceFromBrand(id);
		int priceToCents = (int) (price * 100);
		int billToPay = 0;
		int quarterToPay = 0;
		int dimeToPay = 0;
		int nickelToPay = 0;
		while (paymentCents < priceToCents)
		{
			display.askPayReportDeposited(price, toDollars(paymentCents));
			String payChoice = keyboard.nextLine();
			switch (payChoice)
			{
			case "bill":
				billToPay++;
				paymentCents += 100; break;
			case "quarter":
				quarterToPay++;
				paymentCents += CoinBox.CentEquivalent.QUARTER.getCent(); break;
			case "dime":
				dimeToPay++;
				paymentCents += CoinBox.CentEquivalent.DIME.getCent(); break;
			case "nickel":
				nickelToPay++;
				paymentCents += CoinBox.CentEquivalent.NICKEL.getCent(); break;
			}
		}
		// goes ahead and pays, makes change if needed.
		proceedPayMent(id, priceToCents, paymentCents, billToPay, quarterToPay, dimeToPay, nickelToPay);
	}
	/**
	 * goes ahead and receives payment, maybe with change. 
	 * When a change cannot be made, the transaction fails.
	 * @param id ID number
	 * @param priceToCents price of items, in cents
	 * @param paymentCents payments, in cents
	 * @param billToPay bills paid
	 * @param quarterToPay quarters paid
	 * @param dimeToPay dimes paid
	 * @param nickelToPay nickels paid
	 */
	private void proceedPayMent(int id, int priceToCents, int paymentCents, int billToPay, 
			int quarterToPay, int dimeToPay, int nickelToPay)
	{
		// when amount paid is equal to price
		if (priceToCents == paymentCents)
		{
			// pay
			machine.payNoChange(billToPay, quarterToPay, dimeToPay, nickelToPay);
			// display
			display.successfulTransaction();
			// reduce
			machine.brandReduceInventory(id);
		}
		// when a change is needed
		else if (priceToCents < paymentCents)
		{
			if (machine.isAbleChange(paymentCents - priceToCents, quarterToPay, dimeToPay, nickelToPay))
			{
				// pay & change
				machine.payWithChange(billToPay, quarterToPay, dimeToPay, nickelToPay, paymentCents - priceToCents);
				// display enough change
				display.ifHaveEnoughChange(true);
				// please take the change
				display.displayChange(machine.reportChangeMade());
				// successful transaction displayed
				display.successfulTransaction();
				// reduce
				machine.brandReduceInventory(id);
			}
			else
			{
				// not enough change
				display.ifHaveEnoughChange(false);
				// fail to perform a transaction
				display.whenFail();
			}
		}
	}

	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		VendingMachineSim demo = new VendingMachineSim();
		demo.demoTransaction();
	}

}
