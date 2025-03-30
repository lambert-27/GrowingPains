package view;
//GROWING PAINS - Mark Lambert - C00192497
//GrowingPains CartPanel class - Contains structure for CartPanel area of app
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import controller.EmptyCartException;
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
	private JButton updateCartBtn;
	JLabel totalPrice;
	JPanel checkoutPanel;
	private List<OrderItem> cartItems;
	private Cart cart;
	//List of spinners for each product
	private List<JSpinner> spinners;
	
	

	/**
	 * Constructs a new CartPanel, initialising the layout, title and the products in the cart
	 * 
	 * @param cart the cart containing the list of products to be displayed
	 * @param customer the Customer currently logged in
	 */
	public CartPanel(Customer customer, Cart cart) {
		setLayout(new BorderLayout());
		//Set the CartPanel Cart to the Customers Cart
		this.cart = cart;
		this.cartItems = cart.getCart();
		spinners = new ArrayList<JSpinner>();
		
//		Add the title to the NORTH 
		add(new TitlePanel("Cart"), BorderLayout.NORTH);
		getProducts(cart);
		
//		Buttons
		checkoutBtn = createButton("Checkout");
		updateCartBtn = createButton("Update Cart");
		
//		Event Handling for buttons
		checkoutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Try block, checks if the cartItems list is empty, if it is, throw EmptyCartException
				try {
				if(!cartItems.isEmpty()) {
//					Create a new order object for insertion
					Order order = new Order(customer.getCustomerID(), customer.getAddress(), cart.getTotalPrice());
					GrowingPains.getMainContent().add(new PaymentPanel(order, cart), "Payment");
					GrowingPains.getCardLayout().show(GrowingPains.getMainContent(), "Payment");
				}else
				{
					throw new EmptyCartException("Cannt proceed to checkout with an empty cart");
				}
			}catch(EmptyCartException e1) {
				//Write the error to log
				GrowingPains.errorWriter.logError("Empty Cart Error: ", e1.getMessage());
			}

			}
		});
		
		updateCartBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateCartValues();
			}
		});
		
		
//		Panel that holds the total price andd the checkout button, pushed to the RIGHT BOTTOM of screen
		checkoutPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		totalPrice = new JLabel("Total Price: €" + (float)cart.getTotalPrice());
		checkoutPanel.add(checkoutBtn);
		checkoutPanel.add(updateCartBtn);
		checkoutPanel.add(totalPrice);
//		Code to push to South
		add(checkoutPanel, BorderLayout.SOUTH);
	}
	
	/**
	 * Updates the cart values based on the spinner value for each cart item
	 */
	public void updateCartValues() {
		double total = 0;
		int index = 0;
		//For each loop ot iterate through spinners
		for(JSpinner spin: spinners) {
			//Get the spinner @ current index
			spin = spinners.get(index);
			//Get product @ current index
			OrderItem product = cartItems.get(index);
			
			//New qty of items 
			int newQty = (int) spin.getValue();
			//Calculate total price
			total += newQty * product.getPrice();
			//Set the new qty of items
			cartItems.get(index).setQty(newQty);
			//Increment index
			index++;

		}
		cart.setTotalPrice(total);
		totalPrice.setText("Total Price: €" + (float)cart.getTotalPrice());
	}
	
	/**
	 * Retrieves a list of all products and adds them to the panel for display
	 * 
	 * @param cart the cart containing the list of products to be displayed
	 */
	public void getProducts(Cart cart) {
//Panel to hold all cart details
		JPanel cartPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
//		For each Item in the List of Items
		for (OrderItem product : cartItems){
			//Place each product into its own container, as we want to display the image, 
			//product name and price
			JPanel productPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			JPanel infoPanel = new JPanel(new BorderLayout());
			
			JLabel name = new JLabel("Name: " + product.getItem().getItemName());
			name.setFont(GrowingPains.getArialFont());
//			Starts at the amount currently in cart, min is 0, max is the amount in stock and a step of 1
			JSpinner qtySpinner = new JSpinner(new SpinnerNumberModel(product.getQty(), 0, product.getQtyInStock(), 1));
			JLabel price = new JLabel("Price: €" + product.getPrice() * product.getQty());
			price.setFont(GrowingPains.getArialFont());
			String image_path = product.getImgPath();
			ImageIcon icon = new ImageIcon(getClass().getResource(image_path));
			JLabel imgLabel = new JLabel(icon);
			
			//Add each spinner to the list of spinners
			spinners.add(qtySpinner);

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
	 * @return the JButton created
	 */
	public JButton createButton(String name) {
		JButton btn = new JButton(name);
//		Set the imageIcon
		btn.setForeground(Color.WHITE);
		btn.setFont(GrowingPains.getArialFont());
		btn.setBackground(GrowingPains.getColor());
		btn.setBorderPainted(false);
		
		return btn;
	}
	
	}
	