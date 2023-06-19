package com.yearup.dealership.db;

import com.yearup.dealership.models.LeaseContract;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class LeaseDao {
    private DataSource dataSource;

    public LeaseDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addLeaseContract(LeaseContract leaseContract) {
        // TODO: Implement the logic to add a lease contract
        String query = "insert into lease_contract (VIN, leaseStart, leaseEnd, monthlyPayment) values (?,?,?,?);";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);) {

            preparedStatement.setString(1, leaseContract.getVin());
            preparedStatement.setDate(2, leaseContract.getLeaseStart());
            preparedStatement.setDate(3, leaseContract.getLeaseEnd());
            preparedStatement.setDouble(4, leaseContract.getMonthlyPayment());

            int rows = preparedStatement.executeUpdate();
            System.out.printf("Rows updated %d\n", rows);

        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }
}
