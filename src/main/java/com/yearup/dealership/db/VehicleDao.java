package com.yearup.dealership.db;

import com.yearup.dealership.models.Vehicle;

import java.sql.ResultSet;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDao {
    private DataSource dataSource;

    public VehicleDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addVehicle(Vehicle vehicle) {
        // TODO: Implement the logic to add a vehicle
        String query = "insert into vehicles (VIN, make, model, year, SOLD, color, vehicleType, odometer, price) values (?, ?, ?, ?, ?, ?, ?, ?, ?);";


        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);) {

            preparedStatement.setString(1, vehicle.getVin());
            preparedStatement.setString(2, vehicle.getMake());
            preparedStatement.setString(3, vehicle.getModel());
            preparedStatement.setInt(4, vehicle.getYear());
            preparedStatement.setBoolean(5, vehicle.isSold());
            preparedStatement.setString(6, vehicle.getColor());
            preparedStatement.setString(7, vehicle.getVehicleType());
            preparedStatement.setInt(8, vehicle.getOdometer());
            preparedStatement.setDouble(9, vehicle.getPrice());

            int rows = preparedStatement.executeUpdate();
            System.out.printf("Rows updated %d\n", rows);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void removeVehicle(String VIN) {
        // TODO: Implement the logic to remove a vehicle
        String query = "REMOVE FROM vehicle WHERE VIN = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, VIN);

            int rows = preparedStatement.executeUpdate();
            System.out.println(rows + " rows affected!");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public List<Vehicle> searchByPriceRange(double minPrice, double maxPrice) {
        // TODO: Implement the logic to search vehicles by price range
        List<Vehicle> vehicles = new ArrayList<>();
        String query = "SELECT minPrice, maxPrice FROM Vehicle;";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setDouble(1, minPrice);
            preparedStatement.setDouble(2, maxPrice);

            try (ResultSet results = preparedStatement.executeQuery()) {

                while (results.next()) {
                    minPrice = results.getDouble("minPrice");
                    maxPrice = results.getDouble("maxPrice");

                    Vehicle vehicle = new Vehicle(minPrice, maxPrice);
                    vehicles.add(vehicle);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return vehicles;

    }

    public List<Vehicle> searchByMakeModel(String make, String model) {
        // TODO: Implement the logic to search vehicles by make and model
        List<Vehicle> vehicles = new ArrayList<>();
        String query = "SELECT * FROM vehicles WHERE make = ? and model = ?;";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, make);
            preparedStatement.setString(2, model);

            Vehicle vehicle = new Vehicle(make, model);
            vehicles.add(vehicle);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return vehicles;
    }

    public List<Vehicle> searchByYearRange(int minYear, int maxYear) {
        // TODO: Implement the logic to search vehicles by year range
        List<Vehicle> vehicles = new ArrayList<>();
        String query = "SELECT * FROM vehicles WHERE year between ? and ?;";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, minYear);
            preparedStatement.setInt(2, maxYear);

            Vehicle vehicle = new Vehicle(minYear, maxYear);
            vehicle.add(vehicle);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }


        return vehicles;
    }

    public List<Vehicle> searchByColor(String color) {
        // TODO: Implement the logic to search vehicles by color
        List<Vehicle> vehicles = new ArrayList<>();
        String query = "SELECT color FROM Vehicle;";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

             preparedStatement.setString(1, color);


            Vehicle vehicle = new Vehicle(color);
            vehicle.add(vehicle);



        } catch (SQLException ex) {
            ex.printStackTrace();
        }
            return vehicles;
        }


        public List<Vehicle> searchByMileageRange ( int minMileage, int maxMileage) {
            // TODO: Implement the logic to search vehicles by mileage range
            List<Vehicle> vehicles = new ArrayList<>();
            String query = "SELECT minMileage, maxMileage FROM Vehicle;";

            try (Connection connection = dataSource.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                preparedStatement.setInt(1, minMileage);
                preparedStatement.setInt(2, maxMileage);


                Vehicle vehicle = new Vehicle(minMileage, maxMileage);
                vehicle.add(vehicle);


            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return vehicles;
        }

            public List<Vehicle> searchByType (String type) {
                // TODO: Implement the logic to search vehicles by type
                List<Vehicle> vehicles = new ArrayList<>();
                String query = "SELECT type FROM Vehicle;";

                try (Connection connection = dataSource.getConnection();
                     PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                    preparedStatement.setString(1, type);


                } catch (SQLException e) {
                    return vehicles;
                }
                return vehicles;
            }
                private Vehicle createVehicleFromResultSet (ResultSet resultSet) throws SQLException {
                    Vehicle vehicle = new Vehicle();
                    vehicle.setVin(resultSet.getString("VIN"));
                    vehicle.setMake(resultSet.getString("make"));
                    vehicle.setModel(resultSet.getString("model"));
                    vehicle.setYear(resultSet.getInt("year"));
                    vehicle.setSold(resultSet.getBoolean("SOLD"));
                    vehicle.setColor(resultSet.getString("color"));
                    vehicle.setVehicleType(resultSet.getString("vehicleType"));
                    vehicle.setOdometer(resultSet.getInt("odometer"));
                    vehicle.setPrice(resultSet.getDouble("price"));
                    return vehicle;
                }
            }





