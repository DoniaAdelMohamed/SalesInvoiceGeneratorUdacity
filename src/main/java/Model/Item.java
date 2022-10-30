package Model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Item {
    private String itemName;
    private double itemPrice;
    private int invoiceNumber;
    private int itemCount;
    private Invoice invoiceObject;

    public Item()
    {

    }

    public Item(String itemName, double itemPrice, int itemCount, int invoiceNumber){
        this.itemName = itemName;
        this.itemCount = itemCount;
        this.itemPrice = itemPrice;
        this.invoiceNumber = invoiceNumber;
       // this.invoiceObject=invoiceObject;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }
    public int getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvNum(int invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }
    public double itemTotal(){

        return itemPrice*itemCount;
    }

    public String getDataCSV() {
        return "" + getInvoiceNumber() + "," + getItemName() + "," + getItemPrice() + "," + getItemCount() ;
    }
}
