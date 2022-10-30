package File;
import Model.Invoice;
import Model.Item;
import View.MainFrame;
import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class SaveCSV {
    private MainFrame frame;
    private DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy"); // day - month - year
    IntializeFiles readObject = new IntializeFiles();
    public SaveCSV(MainFrame frame) {this.frame = frame;}

    public void saveFileMenuBar() {
        String headers = "";
        String lines = "";
        for (Invoice header : readObject.invoices) {
            headers += header.getDataCSV();
            headers += "\n";
            for (Item line : header.getLines()) {
                lines += line.getDataCSV();
                lines += "\n";
            }
        }

        JOptionPane.showMessageDialog(frame, "Please, select file to save Invoice data.", "Invoices File", JOptionPane.WARNING_MESSAGE);
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(frame);

        if (result == JFileChooser.APPROVE_OPTION) {
            File headerFile = fileChooser.getSelectedFile();
            try {
                FileWriter filewriterl = new FileWriter(headerFile);
                filewriterl.write(headers);
                filewriterl.flush();
                filewriterl.close();

                JOptionPane.showMessageDialog(frame, "Please, select file to save Item data.", "Items File", JOptionPane.WARNING_MESSAGE);
                result = fileChooser.showSaveDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File linesFile = fileChooser.getSelectedFile();
                    FileWriter filewriterh = new FileWriter(linesFile);
                    filewriterh.write(lines);
                    filewriterh.flush();
                    filewriterh.close();
                }
            } catch (Exception ex) {JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Error: ", JOptionPane.ERROR_MESSAGE);}
        }
        JOptionPane.showMessageDialog(frame, "Data saved successfully", "Saved", JOptionPane.INFORMATION_MESSAGE);
    }

}