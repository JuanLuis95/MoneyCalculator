package moneycalculator.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import moneycalculator.model.Currency;

public class SQLiteCurrencyLoader implements CurrencyReader {

    /**
     *
     * @return
     */
    @Override
    public ArrayList<Currency> get() {
        ArrayList<Currency> list = new ArrayList<>();
        try {
            Class.forName("org.sqlite.JDBC");
            Connection connect = DriverManager.getConnection("jdbc:sqlite:RATES");
            Statement statement = connect.createStatement();
            String query = "SELECT CURRENCY FROM RATES";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) 
                list.add(new Currency(rs.getString(1)));
            rs.close();
            rs.close();
            statement.close();
            connect.close();
            return list;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error al conectarse con la Base de Datos");
        }
        return null;
    }
}