package view;
//GROWING PAINS - Mark Lambert - C00192497
//GrowingPains ProductPanel class - Contains structure for the individual select product area 
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.SystemColor;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ProductPanel extends JPanel {
	private TitlePanel titlePanel;
	private JPanel gridPanel;
	private JLabel imgLbl;
	private JTextArea description;
	private JLabel priceLbl;
	private JButton addToCart;
	private GridBagConstraints gbc;

	private static final long serialVersionUID = 1L;
	public ProductPanel(Font ARIAL, Color GREEN, String title, String imagePath, Double price) {
		gbc = new GridBagConstraints();
		gbc.insets = new Insets(0,0,5,5);
		setLayout(new BorderLayout());
		imgLbl = new JLabel();
		description = new JTextArea();
		priceLbl = new JLabel();
		titlePanel = new TitlePanel("", ARIAL, GREEN);
		setTitle(title);
		add(titlePanel, BorderLayout.NORTH);
		
		GridBagLayout gbl = new GridBagLayout();
		gridPanel = new JPanel(gbl);
		
		setImage(imagePath);

		addToCart = new JButton("Add to Cart");
		addToCart.setFont(new Font("Arial", Font.PLAIN, 18));
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.gridx = 12;
		gbc.gridy = 7;
		gridPanel.add(addToCart, gbc);

		add(gridPanel, BorderLayout.CENTER);
	}
		
	//Calls the titlePanel's setTitle method
	public void setTitle(String title) {
		titlePanel.setTitle(title);
	}
	
//	Set the location of the image and location of imagePath
	public void setImage(String imagePath) {
		imgLbl.setIcon(new ImageIcon(getClass().getResource(imagePath)));
		

		gbc.gridx = 4;
		gbc.gridy = 5;
		gridPanel.add(imgLbl, gbc);
		gridPanel.revalidate();
		gridPanel.repaint();
	}
	
//	Set the location of the description textField and the contents of the description
	public void setDescription(String desc) {
		description.setEditable(false);
		description.setFont(new Font("Arial", Font.PLAIN, 15));
		description.setLineWrap(true);
		description.setBackground(SystemColor.menu);
		description.setText(desc);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = 8;

		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 8;
		gbc.gridy = 5;
		gridPanel.add(description, gbc);
		gridPanel.revalidate();
		gridPanel.repaint();
	}
	
//	Method which sets the position for the price label as well as display the price of the Item
	public void setPrice(Double price) {
		priceLbl.setText("Price: €" + price);
		priceLbl.setFont(new Font("Arial", Font.PLAIN, 18));
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.gridx = 8;
		gbc.gridy = 7;
		gridPanel.add(priceLbl, gbc);
	}
}
