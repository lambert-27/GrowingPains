package model;
//GROWING PAINS - Mark Lambert - C00192497
//CartItem class extends Item

/**
 * The CartItem class extends the Item class, holding information about
 * each Product being added into the Customers Cart
 * 
 * The CartItem class holds the qty, price, item and id associateed with each Product in the cart
 */
public class CartItem extends Item{
	
    private int qty;
    private double price;
    private Item item;
    private int id;
    
    /**
     * Constructs a CartItem Object for insertion into a customers Cart
     * @param item The Product being added to the cart
     * @param qty The Quantity being added to the cart
     */
    public CartItem(Item item, int qty) {
    	setItem(item);
    	setQty(qty);
//    	Call the items getPrice method to set the price
    	setPrice(item.getPrice());
    	setID();
    }
    
    /**
     * Sets the Price of the CartItem
     * @param price The price associated with the CartItem
     */
    public void setPrice(double price) {
    	this.price = price;
    }
    
    /**
     * Sets the Quantity of the CartItem
     * @param qty The quantity associated with the CartItem
     */
    public void setQty(int qty) {
    	this.qty = qty;
    }
    /**
     * Gets the Quantity of the CartItem
     * @return The quantity associated with the CartItem
     */
    public int getQty() {
    	return qty;
    }
    /**
     * Gets the Quantity in Stock for the Product
     * @return The quantity in stock associated with the Product
     */
    public int getQtyInStock() {
    	return item.getQty();
    }
    /**
     * Gets the Price of the CartItem
     * @return The price associated with the CartItem
     */
    public double getPrice() {
    	return price;
    }
    /**
     * Sets the Product of the CartItem
     * @param iteem The Product associated with the CartItem
     */
    public void setItem(Item item) {
    	this.item = item;
    }
    /**
     * Gets the Product of the CartItem
     * @return iteem The Product associated with the CartItem
     */
    public Item getItem() {
    	return item;
    }
    /**
     * Sets the ID of the CartItem to that of the Product associated with it
     */
    public void setID() {
    	this.id = item.getItemID();
    }
    /**
     * Gets the ID of the CartItem
     * @return The ID associated with the CartItem
     */
    public int getItemID() {
    	return id;
    }
    /**
     * Gets the Image Path of the CartItem
     * @return The Image Path associated with the CartItem
     */
    public String getImgPath() {
    	return item.getImgPath();
    }
    /**
     *  Calculate the price by multiplying the Item objects price by the qty of the item added to the cart
     * @return The totalPrice associated with the CartItem
     */ 
    public double calculateProductTotal() {
    	return price*qty;
    }

    /**
     * Gets the Type of Product associated with the CartItem
     * @return Type of Product
     */
	public String getType() {
		// TODO Auto-generated method stub
		return item.getType();
	}
}