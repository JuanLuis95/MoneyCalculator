package moneycalculator.control;

import java.util.Date;
import moneycalculator.model.Currency;
import moneycalculator.model.ExchangeRate;
import moneycalculator.model.Money;
import moneycalculator.persistence.SQLiteExchangeRateLoader;
import moneycalculator.process.Exchanger;
import moneycalculator.ui.CurrencyDialog;
import moneycalculator.ui.MoneyDialog;
import moneycalculator.ui.MoneyDisplay;

public class ExchangeCommand implements Command {
    private MoneyDialog fromMoneyDialog;
    private MoneyDisplay toMoneyDisplay;
    private CurrencyDialog toCurrencyDialog;

    public ExchangeCommand(MoneyDialog fromMoneyDialog, MoneyDisplay toMoneyDisplay, CurrencyDialog toCurrencyDialog) {
        this.fromMoneyDialog = fromMoneyDialog;
        this.toMoneyDisplay = toMoneyDisplay;
        this.toCurrencyDialog = toCurrencyDialog;
    }
    
    @Override
    public void execute() {
        Money money = fromMoneyDialog.get();
        Currency currency = toCurrencyDialog.get();
        ExchangeRate exchangeRate = new SQLiteExchangeRateLoader().get(new Date(),money.getCurrency(), currency);
        Money result = new Exchanger().exchange(money, exchangeRate);
        toMoneyDisplay.show(result);
    }
    
    
    
}