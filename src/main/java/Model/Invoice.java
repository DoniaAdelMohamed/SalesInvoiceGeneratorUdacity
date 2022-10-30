package Model;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Invoice {
    private  int invoiceNo ;
    private Date invoiceDate;
    private String name;
    private ArrayList<Item> lines;
    public Invoice() {

    }
    public Invoice(int invoiceNo , Date invoiceDate, String name)
    {
        this.invoiceDate = invoiceDate;
        this.name = name;
        this.invoiceNo = invoiceNo;

    }

    public double getTotal(){
        double total =0;

            for (int i = 0; i < lines.size(); i++) {
                Item line = lines.get(i);
                total += line.itemTotal();
            }

        return total;
    }
    public int getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(int invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public ArrayList<Item> getLines(){
        return  lines;
    }

    public void setLines(ArrayList<Item> lines) {
        this.lines = lines;
    }

    public String toString(){
        return "Invoices[" + "num=" + invoiceNo + ", date";
    }

    public String getDataCSV() {
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        return "" + getInvoiceNo() + "," + df.format(getInvoiceDate()) + "," + getName(); }


}
