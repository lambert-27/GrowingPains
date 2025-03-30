package model;
//GROWING PAINS - Mark Lambert - C00192497
//Payment class 

/**
 * The Payment class contains all information for a payment that is placed by a customer when making an order
 * 
 * The Payment class holds the cardNumber, expiryDate, cvv, totalPrice
 */
public class Payment {
	private String cardNumber;
	private String expiryDate;
	private String cvv;
	private double totalPrice;

	/**
	 * Default constructor
	 */
    public Payment() {
    	setExpiryDate("");
    	setCvv("");
    	setCardNumber("");
    }
    /**
     * Constructor for creating a new Payment object with details for inserting into DB
     * @param cardNumber The 16 digit card number
     * @param expiryDate The xx/xx expiry date
     * @param cvv The 3 digit CVV 
     * @param totalPrice The total price
     */
    public Payment(String cardNumber, String expiryDate, String cvv, double totalPrice) {
    	setCardNumber(cardNumber);
    	setExpiryDate(expiryDate);
    	setCvv(cvv);
    }

    /**
     * Get the total price of the order
     * @return The total price
     */
	public double getTotalPrice() {
		return totalPrice;
	}
	/**
	 * Set the total price
	 * @param totalPrice The total price
	 */
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	/**
	 * Get the card number
	 * @return The card number
	 */
	public String getCardNumber() {
		return cardNumber;
	}

	/**
	 * Sets the card number
	 * @param cardNumber The card number
	 */
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	/**
	 * Get the expiry date of the card
	 * @return The expiry date of the card
	 */
	public String getExpiryDate() {
		return expiryDate;
	}

	/**
	 * Set the expiry date of the card
	 * 
	 * @param expiryDate The cards expiry date
	 */
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	
	
	/**
	 * Get the CVV code
	 * @return The 3 digit CVV code
	 */
	public String getCvv() {
		return cvv;
	}

	/**
	 * Set the CVV code
	 * @param cvv The 3 digit CVV code
	 */
	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
}