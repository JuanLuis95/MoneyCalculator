package moneycalculator.process;

import moneycalculator.model.ExchangeRate;
import moneycalculator.model.Money;

public class Exchanger {
    public Money exchange(Money money, ExchangeRate exchangeRate) {
        return new Money(money.getAmount() * exchangeRate.getRate(), exchangeRate.getTo());
    }
    
}