package view;
import javax.swing.JButton;
import javax.swing.JOptionPane;
//GROWING PAINS - Mark Lambert - C00192497
//GrowingPains OrdersPanel class - Contains structure for OrdersPanel area of app
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.EmptyOrderException;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import crud.OrderCrud;
import model.Order;
import java.sql.SQLException;

/**
 * OrdersPanel creates a Table which populates via an INNER JOIN on two tables (Customer and Orders) in the Database
 */
public class OrdersPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private JTable table;
    private JButton cancelOrderBtn;
    private DefaultTableModel tableModel;

    /**
     * Creates a new OrdersPanel which creates the headings for the JTable, adds a scroll pane to it and populates w/orders
     * @param customerID The logged in customer
     */
    public OrdersPanel(int customerID) {
        setLayout(new BorderLayout());
        
        // Create table model with column names
        tableModel = new DefaultTableModel(new Object[]{"Order ID", "Date", "Time", "Address", "Total Price"}, 0);
        table = new JTable(tableModel);


        //Set tale to not editable, but selectable
        table.setDefaultEditor(Object.class, null);
        
        // Add scroll pane for the table
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        
        // Load orders for the customer
        loadCustomerOrders(customerID);
        
        //Build a Cancel order Button
        cancelOrderBtn = GrowingButton.createButton("Cancel Order");
        
        cancelOrderBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
        		//Check if the user has selected a row to delete
        		if(table.getSelectedRow() != -1) {
        			//Get the orderID from the table, by calling the table.getSelectedRow and then 0 represents the first column, which is orderID
        			int orderID = (int)table.getValueAt(table.getSelectedRow(), 0);
        			
        			//areYouSure is assigned the OptionPage result after the user selects a response
        			int areYouSure = JOptionPane.showConfirmDialog(OrdersPanel.this,  "Are you sure you want to cancel your order?", "Choose an Option", JOptionPane.YES_NO_OPTION);
        			
        			//If the user selected the yes option, the proceed with the order cancellation by removing the record from the table and db
        			if(areYouSure == JOptionPane.YES_OPTION) {
            			//Now, declare a new crud object and delete the order
            			try {
    						OrderCrud crud = new OrderCrud();
    						crud.deleteOrder(orderID);
    					} catch (SQLException e1) {
    						// TODO Auto-generated catch block
    						e1.printStackTrace();
    					}
            			//Finally, remove the order from the table
            			tableModel.removeRow(table.getSelectedRow());
            		}
        		}
        		else {
        			throw new EmptyOrderException("Must select an order from the table before cancelling an order");
        		}
        	}catch(EmptyOrderException e1) {
        		GrowingPains.errorWriter.logError("Cancel Order Error: " , e1.getMessage());
        	}
        	}});
        
        // Create a panel for the button with FlowLayout, aligning it to the RIGHT, keeping with the design of the other pages
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); 
        buttonPanel.add(cancelOrderBtn);
        
        add(buttonPanel, BorderLayout.SOUTH);
    }

    /**
     * Populates the table based on the orders the Customer logged in has amde
     * @param customerID The ID of the logged in user
     */
    private void loadCustomerOrders(int customerID) {
        try {
            OrderCrud orderCrud = new OrderCrud();
            List<Order> orders = orderCrud.getAllOrders(customerID);
            
            // Clear existing data in table
            tableModel.setRowCount(0);
            
            // For each loop to iterate through the list of Orders
            for (Order order : orders) {
            	//New Object of Row Data
                Object[] rowData = 
                	{
                    order.getOrderID(),
                    order.getDate(),
                    order.getTime(),
                    order.getAddress(),
                    //Format the price to have a € and rounded to .2 decimals
                    String.format("€%.2f", order.getPrice())
                };
                tableModel.addRow(rowData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}