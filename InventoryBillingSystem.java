import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class Item {
    private String name;
    private double price;
    private int quantity;

    public Item(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public double getTotal() { return price * quantity; }
}

public class InventoryBillingSystemGUI extends JFrame {
    private ArrayList<Item> inventory = new ArrayList<>();
    private DefaultTableModel tableModel;

    public InventoryBillingSystemGUI() {
        setTitle("Inventory Billing System");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Table setup
        String[] columns = {"Item Name", "Price", "Quantity", "Total"};
        tableModel = new DefaultTableModel(columns, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Buttons
        JButton addButton = new JButton("Add Item");
        JButton removeButton = new JButton("Remove Item");
        JButton billButton = new JButton("Generate Bill");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(billButton);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Button actions
        addButton.addActionListener(e -> addItem());
        removeButton.addActionListener(e -> removeItem(table));
        billButton.addActionListener(e -> generateBill());

        setVisible(true);
    }

    private void addItem() {
        JTextField nameField = new JTextField();
        JTextField priceField = new JTextField();
        JTextField quantityField = new JTextField();

        Object[] message = {
            "Item Name:", nameField,
            "Price:", priceField,
            "Quantity:", quantityField
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Add New Item", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                String name = nameField.getText();
                double price = Double.parseDouble(priceField.getText());
                int quantity = Integer.parseInt(quantityField.getText());

                Item item = new Item(name, price, quantity);
                inventory.add(item);
                tableModel.addRow(new Object[]{name, price, quantity, item.getTotal()});
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid input! Price and Quantity must be numbers.");
            }
        }
    }

    private void removeItem(JTable table) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            inventory.remove(selectedRow);
            tableModel.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(null, "Please select a row to remove.");
        }
    }

    private void generateBill() {
        double totalAmount = 0;
        for (Item item : inventory) {
            totalAmount += item.getTotal();
        }
        JOptionPane.showMessageDialog(null, "Total Bill: $" + String.format("%.2f", totalAmount));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(InventoryBillingSystemGUI::new);
    }
}
