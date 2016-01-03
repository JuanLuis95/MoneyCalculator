package moneycalculator;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import javax.swing.*;
import moneycalculator.control.*;
import moneycalculator.model.Currency;
import moneycalculator.persistence.SQLiteCurrencyLoader;

public class Application extends JFrame{
    private Command exchangeCommand;
    private FromMoneyPanel fromMoneyPanel;
    private ToMoneyPanel toMoneyPanel;
    private ToCurrencyPanel toCurrencyPanel;
    
    public static void main(String[] args) {
        new Application().setVisible(true);
    }

    public Application() {
        deployUI();
        createCommands();
    }

    private void deployUI() {
        this.setTitle("MoneyCalculator");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.getContentPane().add(components());
        this.setPreferredSize(new Dimension(300, 100));
        this.pack();
        this.setResizable(false);
    }

    private void createCommands() {
        this.exchangeCommand = new ExchangeCommand(fromMoneyPanel,toMoneyPanel,toCurrencyPanel);
    }

    private JPanel components() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        ArrayList<Currency> currencyList = new SQLiteCurrencyLoader().get();
        panel.add(fromComponents(currencyList));
        panel.add(toComponents(currencyList));
        return panel;
    }

    private JPanel fromComponents(ArrayList<Currency> currencyList) {
        JTextField textField = new JTextField("0");
        textField.addKeyListener(doCommandOnType("Exchange"));
        this.fromMoneyPanel = new FromMoneyPanel(fromOptions(currencyList),textField);
        return fromMoneyPanel;
    }

    private JPanel toComponents(ArrayList<Currency> currencyList) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        this.toMoneyPanel = new ToMoneyPanel();
        panel.add(toMoneyPanel);
        panel.add(toOptions(currencyList));
        return panel;
    }

    private JComboBox fromOptions(ArrayList<Currency> currencyList) {
        JComboBox comboBox = new JComboBox();
        for (Currency currency : currencyList) 
            comboBox.addItem(currency.getCode());
        comboBox.addActionListener(doCommandOnComboBox("Exchange"));
        return comboBox;
    }

    private ToCurrencyPanel toOptions(ArrayList<Currency> currencyList) {
        JComboBox comboBox = new JComboBox();
        for (Currency currency : currencyList) 
            comboBox.addItem(currency.getCode());
        toCurrencyPanel = new ToCurrencyPanel(comboBox);
        comboBox.addActionListener(doCommandOnComboBox("Exchange"));
        return toCurrencyPanel;
    }

    private KeyListener doCommandOnType(final String command) {
        return new KeyListener() {
            @Override
            public void keyReleased(KeyEvent e) {
                if(!fromMoneyPanel.getTextField().getText().equals(""))
                    exchangeCommand.execute();
                else 
                    toMoneyPanel.getTextField().setText("");
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

        };
    }

    private ActionListener doCommandOnComboBox(final String command) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!fromMoneyPanel.getTextField().getText().equals(""))
                    exchangeCommand.execute();
                 else 
                    toMoneyPanel.getTextField().setText("");
            }
        };
    }
}