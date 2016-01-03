package moneycalculator;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import moneycalculator.model.Currency;
import moneycalculator.ui.CurrencyDialog;

public class ToCurrencyPanel extends JPanel implements CurrencyDialog{
    private JComboBox to;

    public ToCurrencyPanel(JComboBox to) {
        this.to = to;
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.to.setSelectedIndex(2);
        this.add(to);
    }

    @Override
    public Currency get() {
        return new Currency(to.getSelectedItem().toString());
    }
}