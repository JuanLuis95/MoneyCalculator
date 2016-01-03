package moneycalculator.persistence;

import java.util.Date;
import moneycalculator.model.Currency;
import moneycalculator.model.ExchangeRate;

public interface ExchangeRateReader {
    ExchangeRate get(Date date, Currency from, Currency to);
}