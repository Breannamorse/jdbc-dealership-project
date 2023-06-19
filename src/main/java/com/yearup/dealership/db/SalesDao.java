package com.yearup.dealership.db;

import com.yearup.dealership.models.SalesContract;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class SalesDao {
    private DataSource dataSource;

    public SalesDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addSalesContract(SalesContract salesContract) {
        // TODO: Implement the logic to add a sales contract
        String query = "insert into sales_contract (VIN, saleDate, price) values (?,?,?);";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);) {

            preparedStatement.setString(1, salesContract.getVin());
            preparedStatement.setDate(2, salesContract.getSaleDate());
            preparedStatement.setDouble(3, salesContract.getPrice());

            int rows = preparedStatement.executeUpdate();
            System.out.printf("Rows updated %d\n", rows);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
