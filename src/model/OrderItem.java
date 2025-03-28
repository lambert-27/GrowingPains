package model;
//GROWING PAINS - Mark Lambert - C00192497
//OrderItem class extends Item

/**
 * The OrderItem class extends the Item class, holding information about
 * each Product being added into the Customers Order
 * 
 * The OrderItem class holds the qty, price, item and id associateed with each Product in the Order
 */
public class OrderItem extends Item{
	
    private int qty;
    private double price;
    private Item item;
    private int id;
    
    /**
     * Constructs a OrderItem Object for insertion into a customers Order
     * @param item The Product being added to the Order
     * @param qty The Quantity being added to the Order
     */
    public OrderItem(Item item, int qty) {
    	setItem(item);
    	setQty(qty);
//    	Call the items getPrice method to set the price
    	setPrice(item.getPrice());
    	setID();
    }
    
    /**
     * Sets the Price of the OrderItem
     * @param price The price associated with the OrderItem
     */
    public void setPrice(double price) {
    	this.price = price;
    }
    
    /**
     * Sets the Quantity of the OrderItem
     * @param qty The quantity associated with the OrderItem
     */
    public void setQty(int qty) {
    	this.qty = qty;
    }
    /**
     * Gets the Quantity of the OrderItem
     * @return The quantity associated with the OrderItem
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
     * Gets the Price of the OrderItem
     * @return The price associated with the OrderItem
     */
    public double getPrice() {
    	return price;
    }
    /**
     * Sets the Product of the OrderItem
     * @param item The Product associated with the OrderItem
     */
    public void setItem(Item item) {
    	this.item = item;
    }
    /**
     * Gets the Product of the OrderItem
     * @return iteem The Product associated with the OrderItem
     */
    public Item getItem() {
    	return item;
    }
    /**
     * Sets the ID of the OrderItem to that of the Product associated with it
     */
    public void setID() {
    	this.id = item.getItemID();
    }
    /**
     * Gets the ID of the OrderItem
     * @return The ID associated with the OrderItem
     */
    public int getItemID() {
    	return id;
    }
    /**
     * Gets the Image Path of the OrderItem
     * @return The Image Path associated with the OrderItem
     */
    public String getImgPath() {
    	return item.getImgPath();
    }
    /**
     *  Calculate the price by multiplying the Item objects price by the qty of the item added to the Order
     * @return The totalPrice associated with the OrderItem
     */ 
    public double calculateProductTotal() {
    	return price*qty;
    }

    /**
     * Gets the Type of Product associated with the OrderItem
     * @return Type of Product
     */
	public String getType() {
		return item.getType();
	}
	
    /**
     * Gets the neq quantity of an Item in the Cart
     * @return The quantity of item
     */
	public int getNewQty() {
		return this.item.getQty() - this.qty;
	}
}