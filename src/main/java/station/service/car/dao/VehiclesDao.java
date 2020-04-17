package station.service.car.dao;

import station.service.car.model.Vehicle;
import station.service.car.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehiclesDao {
    private static final String CREAT_VEHICLE_QUERY = "INSERT INTO vehicles (brand, engine, color, production_year, gear_box, registration_number, model, next_technical_review) VALUES (?,?,?,?,?,?,?,?);";
    private static final String READ_ALL_VEHICLES_QUERY = "SELECT * FROM vehicles;";
    private static final String READ_VEHICLE_BY_ID_QUERY = "SELECT * FROM vehicles where id = ?;";
    private static final String DELETE_VEHICLE_BY_ID_QUERY = "DELETE FROM vehicles where id = ?;";
    private static final String UPDATE_VEHICLE_QUERY = "UPDATE vehicles SET brand = ? , engine = ?, color = ?, production_year = ?, gear_box = ?, registration_number = ?, model = ?, next_technical_review = ? WHERE id = ?;";

    /**
     * Create employee
     *
     * @param vehicle
     * @return
     */
    public Vehicle create(Vehicle vehicle) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement insertStm = connection.prepareStatement(CREAT_VEHICLE_QUERY,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            insertStm.setString(1, vehicle.getBrand());
            insertStm.setString(2, vehicle.getEngine());
            insertStm.setString(3, vehicle.getColor());
            insertStm.setString(4, vehicle.getProductionYear().toString());
            insertStm.setString(5, vehicle.getGearBox());
            insertStm.setString(6, vehicle.getRegistrationNumber());
            insertStm.setString(7, vehicle.getModel());
            insertStm.setString(8, vehicle.getReview().toString());
            int result = insertStm.executeUpdate();

            if (result != 1) {
                throw new RuntimeException("Execute update returned " + result);
            }
            try (ResultSet generatedKeys = insertStm.getGeneratedKeys()) {
                if (generatedKeys.first()) {
                    vehicle.setId(generatedKeys.getInt(1));
                    return vehicle;
                } else {
                    throw new RuntimeException("Generated key was not found");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Return all vehicle
     *
     * @return
     */
    public List<Vehicle> findAll() {
        List<Vehicle> vehicleList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_ALL_VEHICLES_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Vehicle vehicleToAdd = new Vehicle();
                vehicleToAdd.setId(resultSet.getInt("id"));
                vehicleToAdd.setBrand(resultSet.getString("brand"));
                vehicleToAdd.setEngine(resultSet.getString("engine"));
                vehicleToAdd.setColor(resultSet.getString("color"));
                vehicleToAdd.setProductionYear(resultSet.getTimestamp("production_year"));
                vehicleToAdd.setGearBox(resultSet.getString("gear_box"));
                vehicleToAdd.setRegistrationNumber(resultSet.getString("registration_number"));
                vehicleToAdd.setModel(resultSet.getString("model"));
                vehicleToAdd.setReview(resultSet.getTimestamp("next_technical_review"));
                vehicleList.add(vehicleToAdd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicleList;
    }

    /**
     * Get employee by id
     *
     * @param id
     * @return
     */
    public Vehicle readById(int id) {
        Vehicle vehicle = new Vehicle();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_VEHICLE_BY_ID_QUERY)
        ) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    vehicle.setId(resultSet.getInt("id"));
                    vehicle.setBrand(resultSet.getString("brand"));
                    vehicle.setEngine(resultSet.getString("engine"));
                    vehicle.setColor(resultSet.getString("color"));
                    vehicle.setProductionYear(resultSet.getTimestamp("production_year"));
                    vehicle.setGearBox(resultSet.getString("gear_box"));
                    vehicle.setRegistrationNumber(resultSet.getString("registration_number"));
                    vehicle.setModel(resultSet.getString("model"));
                    vehicle.setReview(resultSet.getTimestamp("next_technical_review"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vehicle;
    }

    /**
     * Remove vehicle by id
     *
     * @param id
     */
    public void deleteById(int id) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_VEHICLE_BY_ID_QUERY)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Update vehicle
     *
     * @param vehicle
     */
    public void update(Vehicle vehicle) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_VEHICLE_QUERY)) {
            statement.setString(1, vehicle.getBrand());
            statement.setString(2, vehicle.getEngine());
            statement.setString(3, vehicle.getColor());
            statement.setString(4, vehicle.getProductionYear().toString());
            statement.setString(5, vehicle.getGearBox());
            statement.setString(6, vehicle.getRegistrationNumber());
            statement.setString(7, vehicle.getModel());
            statement.setString(8, vehicle.getReview().toString());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
