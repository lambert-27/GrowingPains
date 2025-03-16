package view;
//GROWING PAINS - Mark Lambert - C00192497
//GrowingPains ProductPanel class - Contains structure for the individual select product area 
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import model.Cart;
import model.Item;

/**
 * ProductPanel extends JPanel, holds all information related to each individual product when selected
 * 
 * Makes use of a boxLayout and allows the user to interact with the product information and add the
 * product to their cart
 */
public class ProductPanel extends JPanel {
	private TitlePanel titlePanel;
	private JPanel boxPanel;
	private JLabel imgLbl;
	private JTextArea description;
	private JLabel priceLbl;
	private JButton addToCart;
	private Item item;

	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructs a new ProductPanel and initialises components and the customers Cart
	 * 
	 * @param ARIAL the font to be used 
	 * @param GREEN the custom colour to be used
	 * @param title the title of the page (the product name)
	 * @param imagePath the path to the image of the product
	 * @param price the price of the product
	 * @param cart hte shopping cart which the product will be added to
	 * 
	 */
	public ProductPanel(Font ARIAL, Color GREEN, String title, String imagePath, double price, Cart cart) {
//		gbc = new GridBagConstraints();
//		gbc.insets = new Insets(0,0,5,5);
		setLayout(new BorderLayout());
		
//		Title panel for product name
		titlePanel = new TitlePanel(title, ARIAL, GREEN);
		add(titlePanel, BorderLayout.NORTH);
		
		//Panel with BoxLayout
		boxPanel = new JPanel();
		boxPanel.setLayout(new BoxLayout(boxPanel, BoxLayout.Y_AXIS));
			
//		Components for item fields
		imgLbl = new JLabel();
		description = new JTextArea();
		priceLbl = new JLabel();
		

		setImage(imagePath);

		addToCart = new JButton("Add to Cart");
		addToCart.setFont(new Font("Arial", Font.PLAIN, 18));
		addToCart.setBackground(GREEN);
		addToCart.setForeground(Color.WHITE);

		addToCart.setBorderPainted(false);
		addToCart.setFocusPainted(false);
		
		addToCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				When user clicks add to cart button, add the item to cart, with a quantity amount
				cart.addItem(item, 1);
			}
		});

		JPanel addToCartPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		
		addToCartPanel.add(addToCart);
		
		add(addToCartPanel, BorderLayout.SOUTH);
		add(boxPanel, BorderLayout.CENTER);
	}
		
	/**
	 * Calls the titlePanel's setTitle method
	 * @param title the title of the product (the product name)
	 */
	public void setTitle(String title) {
		titlePanel.setTitle(title);
	}
	
	/**
	 * 	Set the location of the image and location of imagePath
	 */
	public void setImage(String imagePath) {
		imgLbl.setIcon(new ImageIcon(getClass().getResource(imagePath)));
		
		imgLbl.setAlignmentX(CENTER_ALIGNMENT);
//		Add padding to the image so that it isn't stuck to the top by the title
		imgLbl.setBorder(new EmptyBorder(100,0,0,0));
		
		boxPanel.add(imgLbl);
	}
	
	/**
	 * 	Set the location of the description textField and the contents of the description
	 * @param desc the description of the product 
	 */
	public void setDescription(String desc) {
		description.setEditable(false);
		description.setFont(new Font("Arial", Font.PLAIN, 15));
		description.setLineWrap(true);
		description.setBackground(SystemColor.menu);
		description.setText(desc);

		boxPanel.add(description);

	}
	
	/**
	 * 	Method which sets the position for the price label as well as display the price of the Item
	 * @param price the price of the product
	 */
	public void setPrice(double price) {
		priceLbl.setText("Price: €" + price);
		priceLbl.setFont(new Font("Arial", Font.PLAIN, 18));
		priceLbl.setAlignmentX(CENTER_ALIGNMENT);
		boxPanel.add(priceLbl);
	}
	
	/**
	 * Sets the item to be displayed on the screen (the item the user has selected)
	 * @param item the item to be displayed
	 */
	public void setItem(Item item) {
		this.item = item;
	}
}
