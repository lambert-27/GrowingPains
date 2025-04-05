package view;
//GROWING PAINS - Mark Lambert - C00192497
//GrowingPains CartPanel class - Contains structure for CartPanel area of app
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import controller.CartControl;
import model.Cart;
import model.Customer;

import model.OrderItem;

/**
 * The CartPanel class represents the Cart Panel in the GrowingPains application
 * It displays the list of items in the Cart, allowing the user to alter quantities of
 * each item, see the total price and proceed to checkout
 * 
 */
public class CartPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton checkoutBtn;
	private JButton updateCartBtn;
	JLabel totalPrice;
	JPanel checkoutPanel;
	private List<OrderItem> cartItems;
	//List of spinners for each product
	private List<JSpinner> spinners;	
	private final CartControl CONTROL = new CartControl();

	/**
	 * Constructs a new CartPanel, initialising the layout, title and the products in the cart
	 * 
	 * @param cart the cart containing the list of products to be displayed
	 * @param customer the Customer currently logged in
	 */
	public CartPanel(Customer customer, Cart cart) {
		setLayout(new BorderLayout());
		this.cartItems = cart.getCart();
		spinners = new ArrayList<JSpinner>();
		
//		Add the title to the NORTH 
		add(new TitlePanel("Cart"), BorderLayout.NORTH);
		getProducts(cart);
		
//		Buttons
		checkoutBtn = GrowingButton.createButton("Checkout");
		updateCartBtn = GrowingButton.createButton("Update Cart");
		
//		Event Handling for checkout button
		checkoutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					CONTROL.submitOrder(customer, cart, cartItems);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
//		Event Handling for updateCart button
		updateCartBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CONTROL.updateCartValues(cart, cartItems, spinners);
				//Set the totalPrice label to the updated price of the cart
				totalPrice.setText("Total Price: €" + (float)cart.getTotalPrice());
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
	 * Retrieves a list of all products and adds them to the panel for display
	 * 
	 * @param cart the cart containing the list of products to be displayed
	 */
	public void getProducts(Cart cart) {
		//Container panel for adding products into a list format by using BoxLayout.Y_AXIS
		JPanel cartPanel = new JPanel();
		cartPanel.setLayout(new BoxLayout(cartPanel, BoxLayout.Y_AXIS));

//		For each Item in the List of Items
		for (OrderItem product : cartItems){
			//Place each product into its own container, as we want to display the image, product name and price
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
			//Scale image
			Image img = icon.getImage().getScaledInstance(200,  200,  Image.SCALE_SMOOTH);
			JLabel imgLabel = new JLabel(new ImageIcon(img));
			
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
		
//		Allows the cart to be a scrollable page, displays the contents of the cartPanel in the scroll pane
		JScrollPane scrollPane = new JScrollPane(cartPanel);
//		Sets the increment value for when scrolling w/ mouse to a custom amount (Enables faster scrolling higher the number)
		scrollPane.getVerticalScrollBar().setUnitIncrement(8);
		add(scrollPane, BorderLayout.CENTER);
	
	}
	
	}
	