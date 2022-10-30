package File;
import Model.Invoice;
import Model.Item;
import View.MainFrame;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class LoadDataFromFile {
    private MainFrame frame;
    private IntializeFiles readObject = new IntializeFiles();
    private DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public LoadDataFromFile(MainFrame frame){
        this.frame = frame;
    }
    public void loadFileMenuBar() {

        readObject.invoices.clear();
        readObject.items.clear();


        JFileChooser openFileChooser = new JFileChooser();

        JOptionPane.showMessageDialog(this.frame, "select Invoices  file!", "InvoiceHeader File", 1);
        int resultInvoice = openFileChooser.showOpenDialog(this.frame);
        File InvoicesheaderFile = openFileChooser.getSelectedFile();

        JOptionPane.showMessageDialog(this.frame, "Please, select Items File!", "items File", 1);
        int resultItem = openFileChooser.showOpenDialog(this.frame);
        File lineFile = openFileChooser.getSelectedFile();

        if (resultInvoice == 0) {
            try {
                if (resultItem == 0) {

                    BufferedReader linebr = new BufferedReader(new FileReader(lineFile));
                    String headerLines = "";
                    String lineLines;

                    while((lineLines = linebr.readLine()) != null) {
                        String[] splitLineLinesParts = lineLines.split(",");
                        String ItemNumberString = splitLineLinesParts[0];
                        String itemNameString = splitLineLinesParts[1];
                        String itemPriceString = splitLineLinesParts[2];
                        String itemCountString = splitLineLinesParts[3];
                        int itemNumberInt = Integer.parseInt(ItemNumberString);
                        int itemCountInt = Integer.parseInt(itemCountString);
                        double itemPriceDouble = Double.parseDouble(itemPriceString);
                        Item invoiceLine = new Item(itemNameString,itemPriceDouble,itemCountInt,itemNumberInt);
                        readObject.items.add(invoiceLine);
                    }

                BufferedReader br = new BufferedReader(new FileReader(InvoicesheaderFile));

                while((headerLines = br.readLine()) != null) {
                    String[] splitHeaderLines = headerLines.split(",");
                    String invoiceNumberString = splitHeaderLines[0];
                    lineLines = splitHeaderLines[1];
                    String invoiceCustomerName = splitHeaderLines[2];
                    int invoiceNumberInt = Integer.parseInt(invoiceNumberString);
                    Date invoiceDate = dateFormat.parse(lineLines);
                    Invoice invoice = new Invoice(invoiceNumberInt, invoiceDate,invoiceCustomerName);

                    ArrayList<Item> tempItemList = new ArrayList<>();

                    for(int i=0;i<readObject.items.size();i++)
                    {
                        if(readObject.items.get(i).getInvoiceNumber() == invoiceNumberInt)
                        {
                            tempItemList.add(readObject.items.get(i));

                        }
                    }
                    invoice.setLines(tempItemList);
                    readObject.invoices.add(invoice);

                }




                    DefaultTableModel model = (DefaultTableModel) this.frame.leftPanel.getInvoiceTable().getModel();
                    model.setRowCount(0);
                    for (int i=0 ;i < readObject.invoices.size();i++)
                    {
                        Object rowData [] = new Object[4];
                            rowData[0] = readObject.invoices.get(i).getInvoiceNo();
                            rowData[1] = readObject.invoices.get(i).getInvoiceDate();
                            rowData[2] = readObject.invoices.get(i).getName();
                            rowData[3] = readObject.invoices.get(i).getTotal();
                            model.addRow(rowData);
                    }
                    this.frame.leftPanel.getInvoiceTable().validate();
                }
            } catch (Exception var20) {
                var20.printStackTrace();
            }
        }
    }

}