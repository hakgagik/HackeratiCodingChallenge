package problem2;

import java.util.HashMap;

/**
 * Backend library for an auction house's online auction system.
 * Contains support for adding items, starting auctions, receiving bids, calling an auction,
 * and printing an item's latest action.
 *
 * @author Gagik Hakobyan
 */
public class AuctionLibrary {

	public HashMap<String, AuctionableItem> library;
	public HashMap<String, AuctionableItem> currentlyOnSale;

	/** Basic constructor */
	public AuctionLibrary() {
		library = new HashMap<String, AuctionableItem>();
		currentlyOnSale = new HashMap<String, AuctionableItem>();
	}

	/**
	 * Create and add an item to the library
	 * @param itemName item name
	 * @param reservePrice item reserve price
	 */
	public void addItem(String itemName, double reservePrice) {
		if (library.containsKey(itemName)) {
			System.out.println("An instance of " + itemName + " already exists in the library.");
		} else {
			library.put(itemName, new AuctionableItem(itemName, reservePrice));
			System.out.println("New item, " + itemName + ", created.");
		}
	}

	/** Provisional method, in case user wants to pass in an item instead. */
	public void addItem(AuctionableItem item) {
		addItem(item.getName(), item.getReservePrice());
	}

	/**
	 * Put an item up for auction.
	 * @param itemName item to be auctioned
	 */
	public void startAuction(String itemName) {
		if (!library.containsKey(itemName)){
			System.out.println(itemName + " does not exist in the library.");
		} else {
			AuctionableItem item = library.get(itemName);
			item.auction();
			if (item.getState() == AuctionableItem.State.CurrentlyBeingAuctioned) {
				currentlyOnSale.put(item.getName(), item);
			}
		}
	}

	/** Provisional method, in case user wants to pass in an item instead. */
	public void startAuction(AuctionableItem item){
		startAuction(item.getName());
	}

	/**
	 * Receive a customer's bid on an item. Bid must be higher than the reserve price of the item.
	 * @param itemName name of the item to place a bid on
	 * @param bid customer's bid
	 * @param customer customer's name
	 */
	public void bidOnItem(String itemName, double bid, String customer){
		if (!currentlyOnSale.containsKey(itemName)) {
			System.out.println(itemName + " does not exist or is not for sale.");
		} else {
			library.get(itemName).tryBid(customer, bid);
		}
	}

	/** Provisional method, in case user wants to pass in an item instead. */
	public void bidOnItem(AuctionableItem item, double bid, String customer) {
		bidOnItem(item.getName(), bid, customer);
	}

	/**
	 * Auctioneer calls the auction, determining whether or not it was sold.
	 * @param itemName item whose auction should be called
	 */
	public void callAuction(String itemName) {
		if (!currentlyOnSale.containsKey(itemName)) {
			System.out.println(itemName + " does not exist or is not for sale.");
		} else {
			currentlyOnSale.remove(itemName).callBid();
		}
	}

	/** Provisional method, in case user wants to pass in an item instead. */
	public void callAuction(AuctionableItem item) {
		callAuction(item.getName());
	}

	/**
	 * Prints the last action that the item experienced
	 * @param itemName item whose last action is requested
	 */
	public void getLastAction(String itemName){
		if (!library.containsKey(itemName)){
			System.out.println(itemName + " does not exist in the library.");
		} else {
			AuctionableItem item = library.get(itemName);
			item.getLastAction();
		}
	}

	/** Provisional method, in case user wants to pass in an item instead. */
	public void getLastAction(AuctionableItem item) {
		getLastAction(item.getName());
	}
}
