package problem2;


/**
 * Items used with the AuctionLibrary class. Stores various pieces of information about the item
 * and does basic logic and feedback.
 *
 * @author Gagik Hakobyan
 * */
public class AuctionableItem {

	private final String name;
	private final double reservePrice;
	private String highestBidder;
	private double highestBid;
	private State state;

	/**
	 * Constructs an auctionable item given its name and price (which should be permanent)
	 * @param name Item's name
	 * @param price Item's reserve price
	 */
	public AuctionableItem(String name, double price){
		this.name = name;
		this.reservePrice = price;
		state = State.NotYetForSale;
		highestBid = 0;
		highestBidder = "none";
	}

	/**
	 * Auctions off the item, resetting the highest bid and bidder. If it's already up for auction or sold, does nothing.
	 */
	public void auction() {
		if (state == State.CurrentlyBeingAuctioned) {
			System.out.println(name + " is already up for auction.");
		} else if (state == State.Sold) {
			System.out.println(name + " has already been sold.");
		} else {
			this.state = State.CurrentlyBeingAuctioned;
			highestBidder = "none";
			highestBid = 0;
			System.out.println(name + " has been put up for auction.");
		}
	}


	/**
	 * Sets the state of the object to sold, which is permanent from this point forward.
	 * The owner of the object is now considered to be highestBidder.
	 */
	private void sell() {
		state = State.Sold;
	}

	/**
	 * States the state of the object to indicate that it has failed to be auctioned off.
	 */
	private void failAuction() {
		state = State.FailedToBeSold;
	}

	/**
	 * Prints the last action that the item experienced.
	 */
	public void getLastAction() {
		switch (state) {
			case NotYetForSale:
				System.out.println(name + " has not yet been put up for auction.");
				break;
			case CurrentlyBeingAuctioned:
				if (highestBid == 0) {
					System.out.println(name + " is currently up for auction, but no bids have been placed.");
				}
				else {
					System.out.println(name + " is currently up for auction, with the highest bid at " + highestBid + " placed by " + highestBidder + ".");
				}
			break;
			case FailedToBeSold:
				System.out.println(name + " was previously up for auction, but failed to be sold.");
				break;
			case Sold:
				System.out.println(name + " was sold to " + highestBidder + " for " + highestBid + ".");
				break;
		}
	}

	/**
	 * Calls the bid, determining whether or not the object should be sold or returned to the library as unsold.
	 */
	public void callBid() {
		if (highestBid >= reservePrice) {
			sell();
			System.out.println(name + " sold! To " + highestBidder + " for " + highestBid + ".");
		} else {
			failAuction();
			System.out.println("Auction failed. Highest bid: " + highestBid + ".");
		}
	}

	public String getName() {
		return name;
	}

	public double getReservePrice() {
		return reservePrice;
	}

	public double getHighestBid() {
		return highestBid;
	}

	public String getHighestBidder() {
		return highestBidder;
	}

	public State getState() {
		return this.state;
	}

	public String getOwner() {
		if (state == State.Sold){
			return highestBidder;
		}
		else return "Auctioneer";
	}


	/**
	 * Attempts to place a bid on the object. Does nothing if bid is lower than highest bid.
	 * @param customer Person placing the bid
	 * @param bid bid amount - must be higher than highest bid.
	 */
	public void tryBid(String customer, double bid) {
		if (highestBid < bid) {
			highestBid = bid;
			highestBidder = customer;
			System.out.println("Highest bid for " + name + " is now " + highestBid + ".");
		} else {
			System.out.println("Bid must be higher than " + highestBid + ".");
		}
	}

	/**
	 * Enum for the state of the item.
	 */
	public enum State {
		/** Sitting in the library and not yet for sale */
		NotYetForSale,
		/** Is currently being auctioned*/
		CurrentlyBeingAuctioned,
		/** Was previously up for auction, but failed to be sold*/
		FailedToBeSold,
		/** Sold. The owner is now highestBidder*/
		Sold
	}
}
