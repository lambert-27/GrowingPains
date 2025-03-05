package view;
//GROWING PAINS - Mark Lambert - C00192497
//GrowingPains View class - Contains structure for main area of app

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.DisplayItem;
import model.Item;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class GrowingPains extends JFrame{
//	ImageIcon holds the path to the image for our icon
//	getClass -> retrieve a reference to the Class obj that represents the GrowingPains class
//	getResource() -> returns the location of the image as a URL
	private final ImageIcon ICON = new ImageIcon(getClass().getResource("growing_pains.png"));
//	Custom green colour 
	private final Color GREEN = new Color(24, 65, 15);
//	Common font used for buttons and headings
	private final Font ARIAL = new Font("Arial", Font.PLAIN, 20);
	CardLayout cardLayout = new CardLayout ();
	JPanel mainContent = new JPanel(cardLayout);
//	Grid for products, with a horizontal gap between each image of 10px
	JPanel gridPanel = new JPanel(new GridLayout(0, 4, 10, 10));
	
	//Declare new LoginPanel
	LoginPanel login;
	//TODO Add an EXIT button, which is pushed to the bottom of the sidebar
	public GrowingPains(List<DisplayItem> products) {
//		Invokes the superclass constructor (JFrame), passing in a String as the title
		super("Growing Pains");
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1700, 900);
//		Set the location of the window relative to the screen, null = centered
		setLocationRelativeTo(null);
//		Set the icon image by calling ICON.getImage as setIconImage takes in an Image as an argument
		setIconImage(ICON.getImage());
		login = new LoginPanel(ARIAL, GREEN);
//		Methods to create respective panels for GUI
		mainContent(products);
		topBar();
		sideBar();

//		Sets all elements within this container to visible
		setVisible(true);
	}
//	TopBar Panel
	public void topBar() {
		JPanel topBar = new JPanel();
//		Sets the background to a white
		topBar.setBackground(Color.WHITE);
//		Places the panel at the NORTH (TOP) of the BorderLayout
		add(topBar, BorderLayout.NORTH);
//		The layout being used within this panel is a FLOW LAYOUT, aligning the items to the LEFT
		topBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		JLabel titleLbl = new JLabel("Growing Pains");
//		Give an icon to the label
		titleLbl.setIcon(new ImageIcon(getClass().getResource("growing pains_1.png")));
//		Set font and fontsize
		titleLbl.setFont(new Font("Arial", Font.PLAIN, 33));
//		Event handling for a label, which listens for a mouse click to happen
		titleLbl.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				cardLayout.show(mainContent, "Welcome");
			}
		});
		topBar.add(titleLbl);
	}
//	SideBar Panel
	public void sideBar() {
		JPanel sideBar = new JPanel();
//		Set sidebar to custom green
		sideBar.setBackground(GREEN);
//		Set the sideBar panel to the WEST of the BorderLayout
		add(sideBar, BorderLayout.WEST);
//		Set the layout within this panel to be a BOX layout and stack the items along the Y-AXIS (vertical stack)
		sideBar.setLayout(new BoxLayout(sideBar, BoxLayout.Y_AXIS));

//		BROWSE BUTTON
		JButton browseBtn = createButton("Browse", "pot.png");
		sideBar.add(browseBtn);
		browseBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(mainContent, "Browse");
			}
		});

//		CART BUTTON
		JButton cartBtn = createButton("Cart", "checkout.png");
		sideBar.add(cartBtn);
		cartBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(mainContent,  "Cart");
			}
		});
		
//		REMINDER BUTTON
		JButton remindersBtn = createButton("Reminders", "reminder.png");
		sideBar.add(remindersBtn);
		remindersBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(mainContent, "Reminder");
			}
			
		});
		
		//Glue the Exit down to the bottom of the side bar for workflow priority
		//preventing the user from misclicking and terminating the program
		sideBar.add(Box.createVerticalGlue());
		JButton exitBtn = createButton("Exit","exit.png");
		sideBar.add(exitBtn);
		exitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

	}
	
//	Method that encapsulates all common code for creating a button, takes in 2 paramaters
//	one for the name, the other containing the name of the ImageIcon
	public JButton createButton(String name, String icon) {
		
		JButton btn = new JButton(name);
//		Set the imageIcon
		btn.setIcon(new ImageIcon(getClass().getResource(icon)));
		btn.setForeground(Color.WHITE);
		btn.setFont(ARIAL);
//		Remove border and set bg for transparent effect
		btn.setOpaque(false);
		btn.setBorderPainted(false);
		btn.setFocusPainted(false);
		btn.setContentAreaFilled(false);

		return btn;
	}
//	MainContent Panel
	public void mainContent(List<DisplayItem> products) {
//		mainContent establish the main container for this content area, with a CARDLayout
//		This allows us to have a series of JPanels stacked ontop of one another like a deck of cards

		getContentPane().add(mainContent, BorderLayout.CENTER);
		
		mainContent.add(login);
		mainContent.add(welcomePanel(), "Welcome");
		mainContent.add(browsePanel(products), "Browse");
		mainContent.add(cartPanel(), "Cart");
		mainContent.add(reminderPanel(), "Reminder");

	}
	
//	Title Panel shows the user what page they have currently clicked on 
	public JPanel titlePanel(String title) {
//		Title has a BorderLayout so that we can push thte label to the left (west)
		JPanel titlePanel = new JPanel(new BorderLayout());
		titlePanel.setBackground(GREEN);
		JLabel titleLbl = new JLabel(title);
		titleLbl.setForeground(Color.WHITE);
		titleLbl.setFont(ARIAL);
//		Align the title text to the left
		titlePanel.add(titleLbl, BorderLayout.WEST);
		
		return titlePanel;
	}
	
//	Creates the welcomePanel splash screen
	public JPanel welcomePanel() {
		JPanel welcomePanel = new JPanel(new BorderLayout());
		JPanel titlePanel = titlePanel(" ");
//		Home screen has a background - acting like a splash screen
		JLabel bckgrndLbl = new JLabel((new ImageIcon(getClass().getResource("bg.png"))));
//		
		bckgrndLbl.setHorizontalAlignment(SwingConstants.CENTER);
//		Add the backgroundLbl to the center, the titleLabel to the NORTH
		welcomePanel.add(bckgrndLbl, BorderLayout.CENTER);
		welcomePanel.add(titlePanel, BorderLayout.NORTH);
		
		return welcomePanel;
	}
	
//	Creates and returns the browsePanel
	public JPanel browsePanel(List<DisplayItem> products) {
		JPanel browsePanel = new JPanel(new BorderLayout());
		
//		Add the title to the NORTH 
		browsePanel.add(titlePanel("Browse"), BorderLayout.NORTH);
		
		getProducts(products);
		
		browsePanel.add(gridPanel, BorderLayout.CENTER);
				
		return browsePanel;
	}
	
//	Method to get and display all products
	public void getProducts(List<DisplayItem> products) {
		Font productFont = new Font("Arial", Font.PLAIN, 24);
//		For each Item in the List of Items
		for (Item product : products){
			String image_path = product.getImgPath();
			ImageIcon icon = new ImageIcon(getClass().getResource(image_path));
			JLabel imgLabel = new JLabel(icon);
			JLabel nameLabel = new JLabel(product.getItemName(), SwingConstants.CENTER);
			JLabel priceLabel = new JLabel("€" + product.getPrice(), SwingConstants.CENTER);
			priceLabel.setFont(productFont);
			nameLabel.setFont(new Font("Arial", Font.BOLD, 24));
			//Place each product into its own container, as we want to display the image, 
			//product name and price
			JPanel productPanel = new JPanel(new BorderLayout());
			JPanel infoPanel = new JPanel(new BorderLayout());

//			Container just for the text info
			infoPanel.add(nameLabel, BorderLayout.NORTH);
			infoPanel.add(priceLabel, BorderLayout.CENTER);
			
//			Container for the image AND the text
			productPanel.add(imgLabel, BorderLayout.NORTH);
			productPanel.add(infoPanel, BorderLayout.CENTER);
			
			gridPanel.add(productPanel);
		}
		

	}
	
//	Creates and returns the browsePanel
	public JPanel cartPanel() {
		JPanel cartPanel = new JPanel(new BorderLayout());
		
//		Add the title to the NORTH 
		cartPanel.add(titlePanel("Cart"), BorderLayout.NORTH);
		
		return cartPanel;
	}
	
//	Creates and returns the browsePanel
	public JPanel reminderPanel() {
		JPanel reminderPanel = new JPanel(new BorderLayout());

		
//		Add the title to the NORTH 
		reminderPanel.add(titlePanel("Reminders"), BorderLayout.NORTH);
		
		return reminderPanel;
	}
}