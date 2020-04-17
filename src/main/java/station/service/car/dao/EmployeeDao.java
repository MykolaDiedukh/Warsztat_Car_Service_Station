package station.service.car.dao;

import station.service.car.model.Employee;
import station.service.car.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {

    private static final String CREAT_EMPLOYEE_QUERY = "INSERT INTO employees (first_name, last_name, email, password, super_admin, address, phone_number, notes, hourly_rate, quantity_hours) VALUES (?,?,?,?,?,?,?,?,?,?);";
    private static final String READ_ALL_EMPLOYEES_QUERY = "SELECT * FROM employees;";
    private static final String READ_EMPLOYEE_BY_ID_QUERY = "SELECT * FROM employees where id = ?;";
    private static final String DELETE_EMPLOYEE_BY_ID_QUERY = "DELETE FROM employees where id = ?;";
    private static final String UPDATE_EMPLOYEE_QUERY = "UPDATE employees SET first_name = ? , last_name = ?, email = ?, password = ?, super_admin = ?, address = ?, phone_number = ?, notes = ?, hourly_rate = ?, quantity_hours = ? WHERE id = ?;";

    /**
     * Create employee
     *
     * @param employee
     * @return
     */
    public Employee create(Employee employee) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement insertStm = connection.prepareStatement(CREAT_EMPLOYEE_QUERY,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            insertStm.setString(1, employee.getFirstName());
            insertStm.setString(2, employee.getLastName());
            insertStm.setString(3, employee.getEmail());
            insertStm.setString(4, employee.getPassword());
            insertStm.setInt(5, employee.getSuperAdmin());
            insertStm.setString(6, employee.getAddress());
            insertStm.setInt(7, employee.getPhoneNumber());
            insertStm.setString(8, employee.getNotes());
            insertStm.setDouble(9, employee.getHourlyRate());
            insertStm.setDouble(10, employee.getQuantityHours());
            int result = insertStm.executeUpdate();

            if (result != 1) {
                throw new RuntimeException("Execute update returned " + result);
            }
            try (ResultSet generatedKeys = insertStm.getGeneratedKeys()) {
                if (generatedKeys.first()) {
                    employee.setId(generatedKeys.getInt(1));
                    return employee;
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
     * Return all employees
     *
     * @return
     */
    public List<Employee> readALL() {
        List<Employee> employeeList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_ALL_EMPLOYEES_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Employee employeeToAdd = new Employee();
                employeeToAdd.setId(resultSet.getInt("id"));
                employeeToAdd.setFirstName(resultSet.getString("first_name"));
                employeeToAdd.setLastName(resultSet.getString("last_name"));
                employeeToAdd.setEmail(resultSet.getString("email"));
                employeeToAdd.setPassword(resultSet.getString("password"));
                employeeToAdd.setSuperAdmin(resultSet.getInt("super_admin"));
                employeeToAdd.setAddress(resultSet.getString("address"));
                employeeToAdd.setPhoneNumber(resultSet.getInt("phone_number"));
                employeeList.add(employeeToAdd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeeList;
    }

    /**
     * Get employee by id
     *
     * @param id
     * @return
     */
    public Employee readById(int id) {
        Employee employee = new Employee();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_EMPLOYEE_BY_ID_QUERY)
        ) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    employee.setId(resultSet.getInt("id"));
                    employee.setFirstName(resultSet.getString("first_name"));
                    employee.setLastName(resultSet.getString("last_name"));
                    employee.setEmail(resultSet.getString("email"));
                    employee.setPassword(resultSet.getString("password"));
                    employee.setSuperAdmin(resultSet.getInt("super_admin"));
                    employee.setAddress(resultSet.getString("address"));
                    employee.setPhoneNumber(resultSet.getInt("phone_number"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employee;
    }

    /**
     * Remove employee by id
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
     * Update employee
     *
     * @param employee
     */
    public void update(Employee employee) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_EMPLOYEE_QUERY)) {
            statement.setInt(11, employee.getId());
            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());
            statement.setString(3, employee.getEmail());
            statement.setString(4, employee.getPassword());
            statement.setInt(5, employee.getSuperAdmin());
            statement.setString(6, employee.getAddress());
            statement.setInt(7, employee.getPhoneNumber());
            statement.setString(8, employee.getNotes());
            statement.setDouble(9, employee.getHourlyRate());
            statement.setDouble(10, employee.getQuantityHours());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
