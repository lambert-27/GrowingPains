package view;
//GROWING PAINS - Mark Lambert - C00192497
//GrowingPains View class - Contains structure for main area of app

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controller.ErrorWriter;
import controller.UserNotLoggedInException;
import crud.Crud;
import model.Cart;
import model.Catalogue;
import model.Customer;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/** 
 * The GrowingPains class contians thee main structure for the application
 * 
 *  From here, the login panel is initally displayed and the login success then
 *  determines if the user gets access to broader features. All GUI components
 *  get initialised from here.
 *  
 *  It sets up the main JFrame (window), including the app size, logo and other window behaviours
 */
public class GrowingPains extends JFrame{
	//Class instance variables, note the static fields, which are commonly accessed
	//elements throughout the View portion of the application
	private static JButton browseBtn;
	private static JButton cartBtn;
	private static JButton remindersBtn;
	private static JButton exitBtn;
	private static JButton editAccountBtn;
	private static JButton ordersBtn;

	//Public static ErrorWriter variable, shared within view package, ensuring all contents 
	//get appended to the file
	public static ErrorWriter errorWriter;
//	Custom green colour 
	private static final Color GREEN = new Color(24, 65, 15);
//	Common font used for buttons and headings
	private static final Font ARIAL = new Font("Arial", Font.PLAIN, 20);
	private static CardLayout cardLayout;
	private static JPanel mainContent;
	private static CartPanel cartPanel;
	private static Cart cart;
	private static Customer customer;
	
	private static final long serialVersionUID = 1L;
	//	ImageIcon holds the path to the image for our icon
//	getClass -> retrieve a reference to the Class obj that represents the GrowingPains class
//	getResource() -> returns the location of the image as a URL
	private final ImageIcon ICON = new ImageIcon(getClass().getResource("images/growing_pains.png"));

	private TitlePanel titlePanel;
	//Declare new LoginPanel
	private LoginPanel login;
	private BrowsePanel browse;
	private ProductPanel productPanel;


	private EditAccountPanel edit = null;
	private final int WIDTH = 1400;
	private final int HEIGHT = 900;


	/**
	 * Constructs the GrowingPains application frame, setting the layout, panels and buttons necessary
	 * @throws SQLException Error for DB Connection 
	 */
	public GrowingPains() throws SQLException {
//		Invokes the superclass constructor (JFrame), passing in a String as the title
		super("Growing Pains");
		cart = new Cart();
		cardLayout = new CardLayout();
		mainContent = new JPanel(cardLayout);
		customer = new Customer();
		errorWriter = new ErrorWriter();
		errorWriter.openFile();
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WIDTH, HEIGHT);
//		Set the location of the window relative to the screen, null = centered
		setLocationRelativeTo(null);
//		Set the icon image by calling ICON.getImage as setIconImage takes in an Image as an argument
		setIconImage(ICON.getImage());
		
//		Declare and initialise a new Catalogue
		Catalogue catalogue = new Catalogue();
		productPanel = new ProductPanel("", "", 0.0, cart);
		login = new LoginPanel();

		browse = new BrowsePanel(catalogue, productPanel);

//		Methods to create respective panels for GUI
		mainContent();
		topBar();
		sideBar();
	}
	/**
	 * Sets all elements within the container to visibile, signifying
	 * to the user the beginning of the application
	 */
	public void run() {
//		Sets all elements within this container to visible
		setVisible(true);
	}
	/**
	 * Sets up the top bar of the application window
	 * Contains the title and event listener to navigate to the "Welcome" panel
	 */
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
		titleLbl.setIcon(new ImageIcon(getClass().getResource("images/growing pains_1.png")));
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

	/**
	 * Sets up the sidebar of the application window, including buttons 
	 * for navigation. The buttons are arranged vertically and contain
	 * event handling for user interaction
	 */
	public void sideBar() {
		JPanel sideBar = new JPanel();
//		Set sidebar to custom green
		sideBar.setBackground(GREEN);
//		Set the sideBar panel to the WEST of the BorderLayout
		add(sideBar, BorderLayout.WEST);
//		Set the layout within this panel to be a BOX layout and stack the items along the Y-AXIS (vertical stack)
		sideBar.setLayout(new BoxLayout(sideBar, BoxLayout.Y_AXIS));

//		BROWSE BUTTON
		browseBtn = createButton("Browse", "images/pot.png");
		sideBar.add(browseBtn);
		browseBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					checkLoggedIn("Browse");
					cardLayout.show(mainContent, "Browse");
				} catch (UserNotLoggedInException e1) {
					handleError(e1);
				}
			}
		});

//		CART BUTTON
		cartBtn = createButton("Cart", "images/checkout.png");
		sideBar.add(cartBtn);
		cartBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				Before we go to the cart panel, check the status of the user to see if they are logged in
//				doing so, then updates the Customer object in this class to have all info about the customer 
//				to make an order
				try {
					checkLoggedIn("Cart");
//					Instantiate a new CartPanel so that it updates Dynamically when the user inserts new items
					cartPanel = new CartPanel(customer, cart);
					mainContent.add(cartPanel, "Cart");
					cardLayout.show(mainContent,  "Cart");
				} catch (UserNotLoggedInException e1) {
					handleError(e1);
				}
			}
		});
		
//		REMINDER BUTTON
		remindersBtn = createButton("Reminders", "images/reminder.png");
		sideBar.add(remindersBtn);
		remindersBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					checkLoggedIn("Reminders");
					cardLayout.show(mainContent, "Reminder");
				} catch (UserNotLoggedInException e1) {
					handleError(e1);
				}
			}
		});
		
		//ORDERS Button
		ordersBtn = createButton("Orders", "images/orders.png");
		sideBar.add(ordersBtn);
		ordersBtn.addActionListener((new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					checkLoggedIn("Orders");
					mainContent.add(new OrdersPanel(customer.getCustomerID()), "Orders");
					cardLayout.show(mainContent, "Orders");
				} catch (UserNotLoggedInException e1) {
					handleError(e1);
				}
			}
		}));
		
//		EDIT ACCOUNT Button
		editAccountBtn = createButton("Account","images/account.png");
		sideBar.add(editAccountBtn);
		editAccountBtn.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            checkLoggedIn("Edit Account");
		            //customer = login.getLoggedInCustomer(); // Get current customer
		            
		            // Create edit panel with current customer
		            edit = new EditAccountPanel(customer);
		            mainContent.add(edit, "Edit Account");
		            cardLayout.show(mainContent, "Edit Account");
		            
		            // No need to get updated customer here - it will be updated in the panel
		        } catch (UserNotLoggedInException e1) {
		            handleError(e1);
		        } catch (SQLException e1) {
		            e1.printStackTrace();
		        }
		    }
		});
		
		//Glue the Exit down to the bottom of the side bar for workflow priority
		//preventing the user from misclicking and terminating the program
		sideBar.add(Box.createVerticalGlue());
		exitBtn = createButton("Exit","images/exit.png");
		sideBar.add(exitBtn);
		exitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//Close the connection to the DB when user presses the exit button
					Crud.closeConnection();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//Exit system
				System.exit(0);
			}
		});
	}
	
	/**
	 *	Method that encapsulates all common code for creating a button, takes in 2 paramaters
	*one for the name, the other containing the name of the ImageIcon
	*@param name the the displayed on the button
	*@param icon the image for the button
	*@return the JButton created
	*/
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
	/**
	 * Sets up the main content area of the app, using a CardLayout
	 * Adds different panels to the "deck" of cards for display
	 */
	public void mainContent() {
		
//		mainContent establish the main container for this content area, with a CARDLayout
//		This allows us to have a series of JPanels stacked ontop of one another like a deck of cards
		add(mainContent, BorderLayout.CENTER);
//		Add the panels into the "deck", note the names for the panels, allowing us to switch between them by a unique, easily identifiable name
		mainContent.add(login, "Login");
		mainContent.add(welcomePanel(), "Welcome");
		mainContent.add(browse, "Browse");
		mainContent.add(reminderPanel(), "Reminder");
		mainContent.add(productPanel, "Product");
	}
	
/**
 * 	Creates and returns the welcomePanel splash screen
 * @return a JPanel representing the welcome panel
 */
	public JPanel welcomePanel() {
		JPanel welcomePanel = new JPanel(new BorderLayout());
		titlePanel = new TitlePanel(" ");
//		Home screen has a background - acting like a splash screen
		JLabel bckgrndLbl = new JLabel((new ImageIcon(getClass().getResource("images/bg.png"))));
//		
		bckgrndLbl.setHorizontalAlignment(SwingConstants.CENTER);
//		Add the backgroundLbl to the center, the titleLabel to the NORTH
		welcomePanel.add(bckgrndLbl, BorderLayout.CENTER);
		welcomePanel.add(titlePanel, BorderLayout.NORTH);
		
		return welcomePanel;
	}
	
	/**
	 * 	Creates the welcomePanel splash screen
	 * @return a JPanel representing the welcome panel
	 */
	public JPanel cartPanel() {
		JPanel cartPanel = new JPanel(new BorderLayout());
//		Add the title to the NORTH 
		cartPanel.add(new TitlePanel("Cart"), BorderLayout.NORTH);
		
		return cartPanel;
	}
	
	/**
	 * 	Creates the reminderPanel
	 * @return a JPanel representing the reminder panel
	 */
	public JPanel reminderPanel() {
		JPanel reminderPanel = new JPanel(new BorderLayout());
//		Add the title to the NORTH 
		reminderPanel.add(new TitlePanel("Reminders"), BorderLayout.NORTH);
	
		return reminderPanel;
	}
	
	/**
	 * 	Creates the productPanel 
	 * @return a JPanel representing the product panel
	 */
	public JPanel productPanel() {
		productPanel.add(new TitlePanel("Product"), BorderLayout.NORTH);
		return productPanel;
	}
	
	/**
	 * Creates a customer object and if the Customer is logged in, returns the details of the customer
	 * @param exceptionMsg Passes the location to which the user is trying to access into the error message
	 * @throws UserNotLoggedInException Error for if user is not logged in
	 */	 
	 public void checkLoggedIn(String exceptionMsg) throws UserNotLoggedInException {
		    // First check if we have an edited customer
		    if (edit != null && edit.getUpdatedCustomer(customer) != null) {
		        customer = edit.getUpdatedCustomer(customer);
		    }else {
		    
			    // Then check normal login
			    Customer loggedInCustomer = login.handleLogin();
			    if (loggedInCustomer != null) {
			        customer = loggedInCustomer;
			        customer.setLoggedIn();
			    } else {
			        throw new UserNotLoggedInException(exceptionMsg);
			    }
		    }
	 }
	 
		/**
		 * Helper method used to display error messages to the user via a JOptionPane and write the error to an error file
		 * @param errorType The type of error that occured
		 * @param e The Exception occured
		 */
		private void handleError(Exception e) {
			try {
				//Open the file
				//errorWriter.openFile();
				
			    errorWriter.logError("Login Error: ", e.getMessage());
			    //Finally block to close file once operation is complete
			} finally {
				//errorWriter.closeFile();
			}

		}
		
		/**
		 * Static method to return the colour used throughout the app
		 * @return A green colour green
		 */
		public static Color getColor() {
			return GREEN;
		}
		
		/**
		 * Static method to return the font used throughout the app
		 * @return An Arial Font object
		 */
		public static Font getArialFont() {
			return ARIAL;
		}
		
		/**
		 * Static method to return the font cardLayout manager used throughout the app
		 * @return The main card layout manager used
		 */
		public static CardLayout getCardLayout() {
			return cardLayout;
		}
		
		/**
		 * Static method to return the main panel for the app
		 * @return A JPanel representing the main content area
		 */
		public static JPanel getMainContent() {
			return mainContent;
		}
		
		public static void showCart() {
			cartPanel = new CartPanel(customer, cart);
			mainContent.add(cartPanel, "Cart");
			cardLayout.show(mainContent,  "Cart");
		}
		
}