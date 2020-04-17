package station.service.car.dao;

import station.service.car.model.Customer;
import station.service.car.model.Employee;
import station.service.car.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao {
    private static final String CREAT_CUSTOMER_QUERY = "INSERT INTO customer (first_name, last_name, email, address) VALUES (?,?,?,?);";
    private static final String READ_ALL_CUSTOMERS_QUERY = "SELECT * FROM customer;";
    private static final String READ_CUSTOMER_BY_ID_QUERY = "SELECT * FROM customer where id = ?;";
    private static final String DELETE_CUSTOMER_BY_ID_QUERY = "DELETE FROM customer where id = ?;";
    private static final String UPDATE_CUSTOMER_QUERY = "UPDATE customer SET first_name = ? , last_name = ?, email = ?, address = ? WHERE id = ?;";

    /**
     * Create customer
     *
     * @param customer
     * @return
     */
    public Customer create(Customer customer) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement insertStm = connection.prepareStatement(CREAT_CUSTOMER_QUERY,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            insertStm.setString(1, customer.getFirstName());
            insertStm.setString(2, customer.getLastName());
            insertStm.setString(3, customer.getEmail());
            insertStm.setString(4, customer.getAddress());
            int result = insertStm.executeUpdate();

            if (result != 1) {
                throw new RuntimeException("Execute update returned " + result);
            }
            try (ResultSet generatedKeys = insertStm.getGeneratedKeys()) {
                if (generatedKeys.first()) {
                    customer.setId(generatedKeys.getInt(1));
                    return customer;
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
     * Return all customers
     *
     * @return
     */
    public List<Customer> findAll() {
        List<Customer> employeeList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_ALL_CUSTOMERS_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Customer customerToAdd = new Customer();
                customerToAdd.setId(resultSet.getInt("id"));
                customerToAdd.setFirstName(resultSet.getString("first_name"));
                customerToAdd.setLastName(resultSet.getString("last_name"));
                customerToAdd.setEmail(resultSet.getString("email"));
                customerToAdd.setAddress(resultSet.getString("address"));
                employeeList.add(customerToAdd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeeList;
    }

    /**
     * Get customer by id
     *
     * @param id
     * @return
     */
    public Customer readById(int id) {
        Customer customer = new Customer();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_CUSTOMER_BY_ID_QUERY)
        ) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    customer.setId(resultSet.getInt("id"));
                    customer.setFirstName(resultSet.getString("first_name"));
                    customer.setLastName(resultSet.getString("last_name"));
                    customer.setEmail(resultSet.getString("email"));
                    customer.setAddress(resultSet.getString("address"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customer;
    }

    /**
     * Remove customer by id
     *
     * @param id
     */
    public void deleteById(int id) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_CUSTOMER_BY_ID_QUERY)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Update customer
     *
     * @param customer
     */
    public void update(Customer customer) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_CUSTOMER_QUERY)) {
            statement.setInt(5, customer.getId());
            statement.setString(1, customer.getFirstName());
            statement.setString(2, customer.getLastName());
            statement.setString(3, customer.getEmail());
            statement.setString(4, customer.getAddress());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
