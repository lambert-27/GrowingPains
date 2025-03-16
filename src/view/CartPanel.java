package view;
//GROWING PAINS - Mark Lambert - C00192497
//GrowingPains CartPanel class - Contains structure for CartPanel area of app
import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.Cart;
import model.CartItem;

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
	private int oldVal;
	

	/**
	 * Constructs a new CartPanel, initialising the layout, title and the products in the cart
	 * 
	 * @param ARIAL the font used
	 * @param GREEN the colour used
	 * @param cart the cart containing the list of products to be displayed
	 */
	public CartPanel(Font ARIAL, Color GREEN, Cart cart) {
		setLayout(new BorderLayout());
//		Add the title to the NORTH 
		add(new TitlePanel("Cart", ARIAL, GREEN), BorderLayout.NORTH);
		getProducts(ARIAL, GREEN, cart);
	}
	
	/**
	 * Retrieves a list of all products and adds them to the panel for display
	 * 
	 * @param ARIAL the font used
	 * @param GREEN the colour used
	 * @param cart the cart containing the list of products to be displayed
	 */
	public void getProducts(Font ARIAL, Color GREEN, Cart cart) {
		List<CartItem> cartItems = cart.getCart();

		JPanel cartPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		checkoutBtn = createButton("Checkout", ARIAL, GREEN);
		JPanel checkoutBtnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JLabel totalPrice = new JLabel("Total Price: €" + (float)cart.getTotalPrice());
		checkoutBtnPanel.add(checkoutBtn);


//		For each Item in the List of Items
		for (CartItem product : cartItems){
			//Place each product into its own container, as we want to display the image, 
			//product name and price
			JPanel productPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			JPanel infoPanel = new JPanel(new BorderLayout());
			
			JLabel name = new JLabel("Name: " + product.getItem().getItemName());
			name.setFont(ARIAL);
//			Starts at the amount currently in cart, min is 0, max is the amount in stock and a step of 1
			JSpinner qtySpinner = new JSpinner(new SpinnerNumberModel(product.getQty(), 0, product.getQtyInStock(), 1));
//			JLabel qty = new JLabel("Quantity: " + qtySpinner);
//			qty.setFont(ARIAL);
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
				}
			}
		});
						
//			Container for the image AND the text
			productPanel.add(imgLabel);
			infoPanel.add(name, BorderLayout.NORTH);
			infoPanel.add(qtySpinner, BorderLayout.CENTER);
			infoPanel.add(price, BorderLayout.SOUTH);
			productPanel.add(infoPanel);
			checkoutBtnPanel.add(totalPrice);
			cartPanel.add(productPanel);		
		}
		
		add(cartPanel, BorderLayout.CENTER);
		add(checkoutBtnPanel, BorderLayout.SOUTH);

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
	