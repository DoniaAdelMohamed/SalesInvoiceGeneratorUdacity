package View;

import File.LoadDataFromFile;
import File.IntializeFiles;
import File.SaveCSV;
import Model.Invoice;
import Model.Item;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.text.ParseException;

public class MainFrame extends JFrame implements ActionListener{
    public InvoicePanel leftPanel = new InvoicePanel();
    private ItemPanel rightPanel = new ItemPanel();

    Dialog dialogObject = new Dialog(this,"Invoice");
    Dialog2 itemDialogObject = new Dialog2(this,"Item");

    public IntializeFiles rw = new IntializeFiles();
    LoadDataFromFile loadFileObject = new LoadDataFromFile(this);
    SaveCSV saveObject = new SaveCSV(this);

    JMenuBar menuBar;
    JMenuItem loadFileItem;

    JMenuItem saveFileItem;
    

    public MainFrame() {

        setSize(1000, 1000);
        setLayout(new GridLayout(1, 2));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        menuBar= new JMenuBar();
        JMenu fileMenu= new JMenu("File");
        loadFileItem=new JMenuItem("Load");

        loadFileItem.addActionListener(this);
        fileMenu.add(loadFileItem);

        saveFileItem= new JMenuItem("save");
        saveFileItem.addActionListener(this);
        fileMenu.add(saveFileItem);

        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        add(leftPanel.mainLeftPanel);
        add(rightPanel.mainRightPanel);


        try {
            rw.readInvoiceCSV();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        leftPanel.addRows(rw.invoices);

        dialogObject.getButtonOK().addActionListener(this);
        itemDialogObject.getButtonOK().addActionListener(this);

        leftPanel.getCreateNewInvoiceButton().addActionListener(this);
        leftPanel.getDeleteInvoiceButton().addActionListener(this);

        rightPanel.getSaveButton().addActionListener(this);
        rightPanel.getDeleteButton().addActionListener(this);

        leftPanel.getInvoiceTable().getSelectionModel().addListSelectionListener(leftPanel);

    }


    public void showDialog()
    {
        dialogObject.setSize(500,500);
        dialogObject.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(dialogObject.getButtonOK())) {
            try {
                Invoice invoiceObject = new Invoice();
                invoiceObject = dialogObject.onOK();
                leftPanel.addOneRow(invoiceObject);

            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
        } else if (e.getSource().equals(leftPanel.getCreateNewInvoiceButton())) {
            dialogObject.setSize(500, 500);
            dialogObject.setVisible(true);
        } else if (e.getSource().equals(leftPanel.getDeleteInvoiceButton())) {
            int selRow = leftPanel.getInvoiceTable().getSelectedRow();

            if (leftPanel.getInvoiceTable().getSelectedRow() != -1) {

                DefaultTableModel model = (DefaultTableModel) leftPanel.getInvoiceTable().getModel();
                model.removeRow(leftPanel.getInvoiceTable().getSelectedRow());
                rw.invoices.remove(selRow);
                rightPanel.getItemeTableModel().setRowCount(0);
                rightPanel.getInvoiceNo().setText("");
                rightPanel.getCustomerName().setText("");
                rightPanel.getInvoiceDate().setText("");
                rightPanel.getInvoiceTotal().setText("");

            }

        }
        else if (e.getSource().equals(rightPanel.getSaveButton()))
        {
            itemDialogObject.setSize(500, 500);
            itemDialogObject.setVisible(true);
        }
        else if (e.getSource().equals(rightPanel.getDeleteButton()))
        {
            int selRow = rightPanel.getItemTable().getSelectedRow();
            if (rightPanel.getItemTable().getSelectedRow() != -1) {
                DefaultTableModel model = (DefaultTableModel) rightPanel.getItemTable().getModel();
                model.removeRow(rightPanel.getItemTable().getSelectedRow());
                rw.items.remove(selRow);
            }
        } else if (e.getSource().equals(itemDialogObject.getButtonOK())) {

            int invoiceNumber=0;
            int index = leftPanel.getInvoiceTable().getSelectedRow();
            if (index != -1) {
                DefaultTableModel model = (DefaultTableModel) leftPanel.getInvoiceTable().getModel();
                invoiceNumber= Integer.parseInt(model.getValueAt(index, 0).toString());
            }
            Item itemObject = new Item();
            itemObject = itemDialogObject.onOK();
            itemObject.setInvNum(invoiceNumber);

            rw.items.add(itemObject);
            double total =0.0;
            for(int i = 0 ;i< rw.invoices.size();i++)
            {
                if(rw.invoices.get(i).getInvoiceNo()==invoiceNumber)
                {
                    rw.invoices.get(i).getLines().add(itemObject);
                    total = rw.invoices.get(i).getTotal();


                }
            }
           rightPanel.addNewItem(itemObject);
            rightPanel.getInvoiceTotal().setText(String.valueOf(total));
            leftPanel.getInvoiceTable().setValueAt(total,index,3);


        } else if (e.getSource().equals(loadFileItem)) {
            loadFileObject.loadFileMenuBar();
        }
        else if (e.getSource().equals(saveFileItem))
        {
            saveObject.saveFileMenuBar();
        }

    }
}
