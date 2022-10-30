package View;

import File.IntializeFiles;
import Model.Invoice;
import javax.swing.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Dialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField1;
    private JTextField textField2;

    public JTextField getTextField1() {
        return textField1;
    }

    public JButton getButtonOK() {
        return buttonOK;
    }

    public void setButtonOK(JButton buttonOK) {
        this.buttonOK = buttonOK;
    }

    public void setButtonCancel(JButton buttonCancel) {
        this.buttonCancel = buttonCancel;
    }

    public void setTextField1(JTextField textField1) {
        this.textField1 = textField1;
    }

    public void setTextField2(JTextField textField2) {
        this.textField2 = textField2;
    }

    public JButton getButtonCancel() {
        return buttonCancel;
    }
    public JTextField getTextField2() {
        return textField2;
    }
    public Dialog(MainFrame frame, String title) {
        super(frame,title);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setLocationRelativeTo(frame);

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    public Invoice onOK() throws ParseException {
        String Name = getTextField1().getText().toString();
        String Date = getTextField2(). getText().toString();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        IntializeFiles obj = new IntializeFiles();
        int Number = obj.invoices.size();
        Number+=1;
        Invoice invoiceObject = new Invoice();
        invoiceObject.setInvoiceNo(Number);
        invoiceObject.setName(Name);

       try {

           invoiceObject.setInvoiceDate(sdf.parse(Date));

        }catch (Exception ex)
        {
            JOptionPane.showMessageDialog(this, "You enterd wrong date format, Please eneter it in this format dd-MM-yyyy", "Invoice", 1);
        }

        dispose();
        return invoiceObject;

    }
    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

}
