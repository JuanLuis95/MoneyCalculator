package moneycalculator.ui;

import moneycalculator.model.Money;

public interface MoneyDisplay<T> {
    void show(Money money);
}
