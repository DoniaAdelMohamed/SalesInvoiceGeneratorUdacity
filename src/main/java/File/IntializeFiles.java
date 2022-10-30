package File;
import Model.Invoice;
import Model.Item;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class IntializeFiles {
    public  static ArrayList<Invoice> invoices= new ArrayList<>();
    public static ArrayList<Item> items = new ArrayList<>();
    public ArrayList<Item> readItemsCSV (ArrayList<Item> items) throws FileNotFoundException {
        String line = "";
        BufferedReader br = new BufferedReader(new FileReader("InvoiceLine.csv"));
        while (true) {
            try {
                if (!((line = br.readLine()) != null)) break;
                Item itemObject = new Item();

                String[] itemList = line.split(",");

                itemObject.setInvNum(Integer.parseInt(itemList[0]));
                itemObject.setItemName(itemList[1]);
                itemObject.setItemPrice(Double.parseDouble(itemList[2]));
                itemObject.setItemCount(Integer.parseInt(itemList[3]));

                //Adding Item Object to the list
                items.add(itemObject);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return items;
    }
    public ArrayList<Invoice> readInvoiceCSV() throws FileNotFoundException {
        //read lines
        readItemsCSV(items);

        try {
            String line = "";
            BufferedReader br = new BufferedReader(new FileReader("InvoiceHeader.csv"));
            while ((line = br.readLine()) != null) {
                String [] invoiceList = line.split(",");
                Invoice invoiceObject = new Invoice();
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                invoiceObject.setInvoiceNo(Integer.parseInt(invoiceList[0]));
                try {
                    invoiceObject.setInvoiceDate(sdf.parse(invoiceList[1]));
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                invoiceObject.setName(invoiceList[2]);
                ArrayList<Item> tempItemList=new ArrayList<>();
                for(int i = 0;i<items.size();i++)
                {
                    if(invoiceObject.getInvoiceNo()==items.get(i).getInvoiceNumber())
                    {
                        tempItemList.add(items.get(i));
                    }
                    }
                invoiceObject.setLines(tempItemList);

                //adding one invoice to the list
                invoices.add(invoiceObject);
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return invoices;
    }

}
