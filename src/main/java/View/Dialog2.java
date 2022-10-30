package View;

import Model.Item;
import javax.swing.*;
import java.awt.event.*;

public class Dialog2 extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;

    public Dialog2(MainFrame frame, String title) {
       super(frame,title);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

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

    public Item onOK() {

        String itemName = getTextField1().getText().toString();
        String itemPrice = getTextField2(). getText().toString();
        String itemCount = getTextField3(). getText().toString();

        Item itemObject = new Item();
        itemObject.setItemName(itemName);
        itemObject.setItemPrice(Double.parseDouble(itemPrice));
        itemObject.setItemCount(Integer.parseInt(itemCount));

        dispose();

        return itemObject;
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
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

    public void setTextField3(JTextField textField3) {
        this.textField3 = textField3;
    }

    public JButton getButtonOK() {
        return buttonOK;
    }

    public JButton getButtonCancel() {
        return buttonCancel;
    }

    public JTextField getTextField1() {
        return textField1;
    }

    public JTextField getTextField2() {
        return textField2;
    }

    public JTextField getTextField3() {
        return textField3;
    }

}
