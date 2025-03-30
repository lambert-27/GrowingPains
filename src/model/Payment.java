package model;
//GROWING PAINS - Mark Lambert - C00192497
//Payment class 

public class Payment {
	private String cardNumber;
	private String expiryDate;
	private String cvv;
	private double totalPrice;

    public Payment() {
    	setExpiryDate("");
    	setCvv("");
    	setCardNumber("");
    }
    
    public Payment(String cardNumber, String expiryDate, String cvv, double totalPrice) {
    	setCardNumber(cardNumber);
    	setExpiryDate(expiryDate);
    	setCvv(cvv);
    }

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
}