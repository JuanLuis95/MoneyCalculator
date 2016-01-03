package moneycalculator;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import moneycalculator.model.Money;
import moneycalculator.ui.MoneyDisplay;

public class ToMoneyPanel extends JPanel implements  MoneyDisplay{
    private JTextField textField;

    public ToMoneyPanel() {
        this.textField = new JTextField("0");
        this.textField.setEditable(false);
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.textField.setHorizontalAlignment(JTextField.RIGHT);
        this.add(textField);
    }

    @Override
    public void show(Money money) {
        this.textField.setText(String.format("%.2f",money.getAmount()));
    }

    public JTextField getTextField() {
        return textField;
    }
}