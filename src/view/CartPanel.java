package view;
//GROWING PAINS - Mark Lambert - C00192497
//GrowingPains CartPanel class - Contains structure for CartPanel area of app
import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import crud.OrderCrud;
import crud.ProductCrud;
import model.Cart;
import model.Customer;
import model.Order;
import model.OrderItem;

/**
 * The CartPanel class represents the Cart Panel in the GrowingPains application
 * It displays the list of items in the Cart, allowing the user to alter quantities of
 * each item, see the total price and proceed to checkout
 * 
 * It holds a value oldVal used for updating the price by using the JSpinner quantity
 */
public class CartPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton checkoutBtn;
	JLabel totalPrice;
	JPanel checkoutPanel;
	private int oldVal;
	

	/**
	 * Constructs a new CartPanel, initialising the layout, title and the products in the cart
	 * 
	 * @param ARIAL the font used
	 * @param GREEN the colour used
	 * @param cart the cart containing the list of products to be displayed
	 */
	public CartPanel(Font ARIAL, Color GREEN, Cart cart, Customer customer) {
		setLayout(new BorderLayout());
//		Add the title to the NORTH 
		add(new TitlePanel("Cart", ARIAL, GREEN), BorderLayout.NORTH);
		getProducts(ARIAL, GREEN, cart);
		
		checkoutBtn = createButton("Checkout", ARIAL, GREEN);
		checkoutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				Create a new order object for insertion
				Order order = new Order(customer.getCustomerID(), customer.getAddress(), cart.getTotalPrice());
				try {
					OrderCrud crud = new OrderCrud();
					ProductCrud productCrud = new ProductCrud();
					List<OrderItem> cartItems = cart.getCart();
//					Iterate through the cart
					for(OrderItem product : cartItems) {
//						For each item in the cart, execute the update query to update the product quantity
						productCrud.updateQty(product, product.getNewQty());
					}
//					Finally, insert the order
					crud.insertOrder(order);
					cart.clearCart();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
//		Panel that holds the total price andd the checkout button, pushed to the RIGHT BOTTOM of screen
		checkoutPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		totalPrice = new JLabel("Total Price: €" + (float)cart.getTotalPrice());
		checkoutPanel.add(checkoutBtn);
		checkoutPanel.add(totalPrice);
//		Code to push to South
		add(checkoutPanel, BorderLayout.SOUTH);
	}
	
	/**
	 * Retrieves a list of all products and adds them to the panel for display
	 * 
	 * @param ARIAL the font used
	 * @param GREEN the colour used
	 * @param cart the cart containing the list of products to be displayed
	 */
	public void getProducts(Font ARIAL, Color GREEN, Cart cart) {
		List<OrderItem> cartItems = cart.getCart();
//Panel to hold all cart details
		JPanel cartPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		

//		For each Item in the List of Items
		for (OrderItem product : cartItems){
			//Place each product into its own container, as we want to display the image, 
			//product name and price
			JPanel productPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			JPanel infoPanel = new JPanel(new BorderLayout());
			
			JLabel name = new JLabel("Name: " + product.getItem().getItemName());
			name.setFont(ARIAL);
//			Starts at the amount currently in cart, min is 0, max is the amount in stock and a step of 1
			JSpinner qtySpinner = new JSpinner(new SpinnerNumberModel(product.getQty(), 0, product.getQtyInStock(), 1));
			JLabel price = new JLabel("Price: €" + product.getPrice() * product.getQty());
			price.setFont(ARIAL);
			String image_path = product.getImgPath();
			ImageIcon icon = new ImageIcon(getClass().getResource(image_path));
			JLabel imgLabel = new JLabel(icon);
			
//			oldVal holds the qty before the spinner is altered
			oldVal = (int)qtySpinner.getValue();

//			Method to update the price of the cart depending on the current quantity of the spinner
			qtySpinner.addChangeListener(new ChangeListener() {
				public void stateChanged (ChangeEvent e) {
//					JSpinner.getValue returns an Object, so cast it to an int
					int currentVal = (int) qtySpinner.getValue();
//					If the values aren't the same
					if(currentVal != oldVal) {
					product.setQty(currentVal);
//					Increment the totalPrice if the quantity goes UP
					if(currentVal > oldVal) {
					cart.updatePrice(product.getPrice());
					}
//					Otherwise, decrement the quantity
					else {
						cart.updatePriceDec(product.getPrice());
					}
					price.setText("Price: €" + product.getPrice() * product.getQty());
					totalPrice.setText("Total Price: €" + (float)cart.getTotalPrice());
					
//					Update oldVal with the currentVal, so that if user changes mind, price updates accordingly
					oldVal = currentVal;
					product.setQty(currentVal);
				}
			}
		});
						
//			Container for the image AND the text
			productPanel.add(imgLabel);
			infoPanel.add(name, BorderLayout.NORTH);
			infoPanel.add(qtySpinner, BorderLayout.CENTER);
			infoPanel.add(price, BorderLayout.SOUTH);
			productPanel.add(infoPanel);
			cartPanel.add(productPanel);		
		}
		
		add(cartPanel, BorderLayout.CENTER);


		}
	
	/**
	 * Create a button method, encapsulate common code
	 * 
	 * @param name the the displayed on the button
	 * @param GREEN the colour used
	 * @param cart the cart containing the list of products to be displayed
	 * @return the JButton created
	 */
	public JButton createButton(String name, Font ARIAL, Color GREEN) {
		JButton btn = new JButton(name);
//		Set the imageIcon
		btn.setForeground(Color.WHITE);
		btn.setFont(ARIAL);
		btn.setBackground(GREEN);
		btn.setBorderPainted(false);
		
		return btn;
	}
	
	}
	