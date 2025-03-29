package view;
//GROWING PAINS - Mark Lambert - C00192497
//GrowingPains OrdersPanel class - Contains structure for OrdersPanel area of app
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
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
    private DefaultTableModel tableModel;

    public OrdersPanel(int customerID) {
        setLayout(new BorderLayout(0, 0));
        
        // Create table model with column names
        tableModel = new DefaultTableModel(new Object[]{"Order ID", "Date", "Time", "Address", "Total Price"}, 0);
        table = new JTable(tableModel);
        
        // Add scroll pane for the table
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        
        // Load orders for the customer
        loadCustomerOrders(customerID);
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