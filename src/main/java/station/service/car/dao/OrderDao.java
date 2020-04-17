package station.service.car.dao;

import station.service.car.model.Order;
import station.service.car.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {

    private static final String CREAT_EMPLOYEE_QUERY = "INSERT INTO orders (accepted_date, planted_date, start_date, description_problems, description_repair, repair_cost, parts_cost, hourly_rate, repair_hours, employee_id, customer_id, vehicle_id) VALUES (?,?,?,?,?,?,?,?,?,?,?,?);";
    private static final String READ_ALL_EMPLOYEES_QUERY = "SELECT * FROM orders;";
    private static final String READ_EMPLOYEE_BY_ID_QUERY = "SELECT * FROM orders where id = ?;";
    private static final String DELETE_EMPLOYEE_BY_ID_QUERY = "DELETE FROM orders where id = ?;";
    private static final String UPDATE_EMPLOYEE_QUERY = "UPDATE orders SET accepted_date = ? , planted_date = ?, start_date = ?, description_problems = ?, description_repair = ?, repair_cost = ?, parts_cost = ?, hourly_rate = ?, repair_hours = ?, employee_id = ?, customer_id = ?, vehicle_id = ? WHERE id = ?;";

    /**
     * Create order
     *
     * @param order
     * @return
     */
    public Order create(Order order) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement insertStm = connection.prepareStatement(CREAT_EMPLOYEE_QUERY,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            insertStm.setString(1, order.getAccepted().toString());
            insertStm.setString(2, order.getPlanted().toString());
            insertStm.setString(3, order.getStart().toString());
            insertStm.setString(4, order.getDescProblem());
            insertStm.setString(5, order.getDescRepair());
            insertStm.setDouble(6, order.getRepairCost());
            insertStm.setDouble(7, order.getPartsCost());
            insertStm.setInt(8, order.getHourlyRate());
            insertStm.setDouble(9, order.getRepairHours());
            insertStm.setInt(10, order.getEmployeeId());
            insertStm.setInt(11, order.getCustomerId());
            insertStm.setInt(12, order.getVehicleId());
            int result = insertStm.executeUpdate();

            if (result != 1) {
                throw new RuntimeException("Execute update returned " + result);
            }
            try (ResultSet generatedKeys = insertStm.getGeneratedKeys()) {
                if (generatedKeys.first()) {
                    order.setId(generatedKeys.getInt(1));
                    return order;
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
     * Return all orders
     *
     * @return
     */
    public List<Order> readALL() {
        List<Order> employeeList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_ALL_EMPLOYEES_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Order orderToAdd = new Order();
                orderToAdd.setId(resultSet.getInt("id"));
                orderToAdd.setAccepted(resultSet.getTimestamp("accepted_date"));
                orderToAdd.setPlanted(resultSet.getTimestamp("planted_date"));
                orderToAdd.setStart(resultSet.getTimestamp("start_date"));
                orderToAdd.setDescProblem(resultSet.getString("description_problems"));
                orderToAdd.setDescRepair(resultSet.getString("description_repair"));
                orderToAdd.setRepairCost(resultSet.getDouble("repair_cost"));
                orderToAdd.setPartsCost(resultSet.getDouble("parts_cost"));
                orderToAdd.setHourlyRate(resultSet.getInt("hourly_rate"));
                orderToAdd.setRepairHours(resultSet.getDouble("repair_hours"));
                orderToAdd.setEmployeeId(resultSet.getInt("employee_id"));
                orderToAdd.setCustomerId(resultSet.getInt("customer_id"));
                orderToAdd.setVehicleId(resultSet.getInt("vehicle_id"));
                employeeList.add(orderToAdd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeeList;
    }

    /**
     * Get order by id
     *
     * @param id
     * @return
     */
    public Order readById(int id) {
        Order order = new Order();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_EMPLOYEE_BY_ID_QUERY)
        ) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    order.setId(resultSet.getInt("id"));
                    order.setAccepted(resultSet.getTimestamp("accepted_date"));
                    order.setPlanted(resultSet.getTimestamp("planted_date"));
                    order.setStart(resultSet.getTimestamp("start_date"));
                    order.setDescProblem(resultSet.getString("description_problems"));
                    order.setDescRepair(resultSet.getString("description_repair"));
                    order.setRepairCost(resultSet.getDouble("repair_cost"));
                    order.setPartsCost(resultSet.getDouble("parts_cost"));
                    order.setHourlyRate(resultSet.getInt("hourly_rate"));
                    order.setRepairHours(resultSet.getDouble("repair_hours"));
                    order.setEmployeeId(resultSet.getInt("employee_id"));
                    order.setCustomerId(resultSet.getInt("customer_id"));
                    order.setVehicleId(resultSet.getInt("vehicle_id"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return order;
    }

    /**
     * Remove order by id
     *
     * @param id
     */
    public void deleteById(int id) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_EMPLOYEE_BY_ID_QUERY)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Update order
     *
     * @param order
     */
    public void update(Order order) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_EMPLOYEE_QUERY)) {
            statement.setString(1, order.getAccepted().toString());
            statement.setString(2, order.getPlanted().toString());
            statement.setString(3, order.getStart().toString());
            statement.setString(4, order.getDescProblem());
            statement.setString(5, order.getDescRepair());
            statement.setDouble(6, order.getRepairCost());
            statement.setDouble(7, order.getPartsCost());
            statement.setInt(8, order.getHourlyRate());
            statement.setDouble(9, order.getRepairHours());
            statement.setInt(10, order.getEmployeeId());
            statement.setInt(11, order.getCustomerId());
            statement.setInt(12, order.getVehicleId());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
