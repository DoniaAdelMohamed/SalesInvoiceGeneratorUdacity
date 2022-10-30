package View;
import File.IntializeFiles;
import Model.Item;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;


public class ItemPanel {
    public JPanel mainRightPanel = new JPanel();
    private static final String[] itemTablecolumns = {
            "No.", "Item Name", "Item Price", "Count", "Total"
    };

    private JButton SaveButton;
    private JButton deleteButton;
    private static JLabel invoiceNo;
    private static JLabel invoiceDate;
    private static JLabel customerName;
    private static JLabel invoiceTotal;
    private static DefaultTableModel itemeTableModel;
    private static JTable itemTable;
    private static IntializeFiles readObject = new IntializeFiles();

    public ItemPanel() {
        JPanel invoiceNumberPanel = new JPanel(new FlowLayout());
        invoiceNumberPanel.add(new JLabel("Invoice Number"));
        invoiceNo = new JLabel(" 0  ");
        invoiceNumberPanel.add(invoiceNo);
        JPanel invoiceDatePanel = new JPanel(new FlowLayout());
        invoiceDatePanel.add(new JLabel("Invoice Date"));
        invoiceDate = new JLabel();
        invoiceDatePanel.add(invoiceDate);
        JPanel customerNamePanel = new JPanel(new FlowLayout());
        customerNamePanel.add(new JLabel("Customer Name"));
        customerName = new JLabel();
        customerNamePanel.add(customerName);
        JPanel totalInvoicePanel = new JPanel(new FlowLayout());
        totalInvoicePanel.add(new JLabel("Total Invoice"));
        invoiceTotal = new JLabel("");
        totalInvoicePanel.add(invoiceTotal);
        JPanel textPanel = new JPanel(new FlowLayout());
        textPanel.add(invoiceNumberPanel, BorderLayout.BEFORE_FIRST_LINE);
        textPanel.add(invoiceDatePanel, BorderLayout.NORTH);
        textPanel.add(customerNamePanel, BorderLayout.CENTER);
        textPanel.add(totalInvoicePanel, BorderLayout.SOUTH);

        itemeTableModel = new DefaultTableModel(itemTablecolumns, 0);
        itemTable = new JTable(itemeTableModel);

        JPanel buttonPanel = new JPanel();
        SaveButton = new JButton("Create Item");
        buttonPanel.add(SaveButton);
        deleteButton = new JButton("Delete");
        buttonPanel.add(deleteButton);

        mainRightPanel.add(textPanel, BorderLayout.NORTH);
        mainRightPanel.add(new JScrollPane(itemTable), BorderLayout.CENTER);
        //mainRightPanel.add(new JScrollPane(itemTable),BorderLayout.CENTER);
        mainRightPanel.add(buttonPanel, BorderLayout.SOUTH);
    }


    public JButton getSaveButton() {
        return SaveButton;
    }

    public void setSaveButton(JButton saveButton) {
        SaveButton = saveButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public void setDeleteButton(JButton deleteButton) {
        this.deleteButton = deleteButton;
    }

    public JLabel getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(JLabel invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public JLabel getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(JLabel invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public JLabel getCustomerName() {
        return customerName;
    }

    public void setCustomerName(JLabel customerName) {
        this.customerName = customerName;
    }

    public JLabel getInvoiceTotal() {
        return invoiceTotal;
    }

    public void setInvoiceTotal(JLabel invoiceTotal) {
        this.invoiceTotal = invoiceTotal;
    }

    public DefaultTableModel getItemeTableModel() {
        return itemeTableModel;
    }

    public void setItemeTableModel(DefaultTableModel itemeTableModel) {
        this.itemeTableModel = itemeTableModel;
    }

    public JTable getItemTable() {
        return itemTable;
    }

    public void setItemTable(JTable itemTable) {
        this.itemTable = itemTable;
    }
    public void showItems(int invoiceNumber) throws FileNotFoundException {


        ArrayList<Item> readItem = new ArrayList<>();
         ArrayList<Item> items = readObject.readItemsCSV(readItem);

        DefaultTableModel tableModel = (DefaultTableModel) itemTable.getModel();
        tableModel.setRowCount(0);

        for (int i = 0; i < items.size(); i++) {
            Object rowData[] = new Object[5];
            if (invoiceNumber == items.get(i).getInvoiceNumber()) {
                rowData[0] = items.get(i).getInvoiceNumber();
                rowData[1] = items.get(i).getItemName();
                rowData[2] = items.get(i).getItemPrice();
                rowData[3] = items.get(i).getItemCount();
                rowData[4] = items.get(i).itemTotal();
                tableModel.addRow(rowData);
            }
        }
        invoiceNo.setText(Integer.toString(invoiceNumber));
    }

    public void addNewItem(Item itemobject)
    {
        int invNumber = itemobject.getInvoiceNumber();

        DefaultTableModel tableModel = (DefaultTableModel) itemTable.getModel();

        tableModel.setRowCount(0);

        for (int i = 0; i < readObject.items.size(); i++) {
            Object rowData[] = new Object[5];
            if (invNumber == readObject.items.get(i).getInvoiceNumber()) {
                rowData[0] = readObject.items.get(i).getInvoiceNumber();
                rowData[1] = readObject.items.get(i).getItemName();
                rowData[2] = readObject.items.get(i).getItemPrice();
                rowData[3] = readObject.items.get(i).getItemCount();
                rowData[4] = readObject.items.get(i).itemTotal();
                tableModel.addRow(rowData);
            }
        }

        invoiceNo.setText(String.valueOf(invNumber));


    }


}