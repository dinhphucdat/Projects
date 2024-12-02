# Projects
1. BillBox: accepts one-dollar bills and reports how many it has received in the
current transaction.
2. CoinBox: accepts coins, reports the number of each coin type it has, reports the
total value (cash value in cents) currently stored in the box, and can transfer all
coins from itself to another coin box.
1. The CoinBox class will model the coin box inside a vending machine. The
coin box contains some number of quarters, some number of dimes, and
some number of nickels. See below an incomplete UML representation of
the CoinBox
3. BrandInventory: for each brand that will be stocked in the machine, this keeps
the ID number of the brand, the price per can, and the number of cans currently
in the machine. It will also need “getter” methods to return the ID, the price, and
the number on hand, a “toString()” method, the status of the inventory (i.e., is it
sold out?). Finally, it will need a “setter” method to reduce inventory when an
item is sold.
4. Display: must be used to convey ALL messages to the user.
5. CoinLever: reports the change or additional cash that the purchaser deposited.
