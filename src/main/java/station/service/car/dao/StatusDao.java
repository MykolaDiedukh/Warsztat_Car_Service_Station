package station.service.car.dao;

import station.service.car.model.Status;
import station.service.car.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StatusDao {
    private static final String CREAT_CUSTOMER_QUERY = "INSERT INTO status (status, order_id) VALUES (?,?);";
    private static final String READ_ALL_CUSTOMERS_QUERY = "SELECT * FROM status;";
    private static final String READ_CUSTOMER_BY_ID_QUERY = "SELECT * FROM status where id = ?;";
    private static final String DELETE_CUSTOMER_BY_ID_QUERY = "DELETE FROM status where id = ?;";
    private static final String UPDATE_CUSTOMER_QUERY = "UPDATE status SET status = ? , order_id = ? WHERE id = ?;";

    /**
     * Create status
     *
     * @param status
     * @return
     */
    public Status create(Status status) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement insertStm = connection.prepareStatement(CREAT_CUSTOMER_QUERY,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            insertStm.setInt(1, status.getStatus());
            insertStm.setInt(2, status.getOrder_id());
            int result = insertStm.executeUpdate();

            if (result != 1) {
                throw new RuntimeException("Execute update returned " + result);
            }
            try (ResultSet generatedKeys = insertStm.getGeneratedKeys()) {
                if (generatedKeys.first()) {
                    status.setId(generatedKeys.getInt(1));
                    return status;
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
     * Return all status
     *
     * @return
     */
    public List<Status> readALL() {
        List<Status> statusList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_ALL_CUSTOMERS_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Status statusToAdd = new Status();
                statusToAdd.setId(resultSet.getInt("id"));
                statusToAdd.setStatus(resultSet.getInt("status"));
                statusToAdd.setOrder_id(resultSet.getInt("order_id"));
                statusList.add(statusToAdd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statusList;
    }

    /**
     * Get status by id
     *
     * @param id
     * @return
     */
    public Status readById(int id) {
        Status status = new Status();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_CUSTOMER_BY_ID_QUERY)
        ) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    status.setId(resultSet.getInt("id"));
                    status.setStatus(resultSet.getInt("status"));
                    status.setOrder_id(resultSet.getInt("order_id"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    /**
     * Remove status by id
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
     * Update status
     *
     * @param status
     */
    public void update(Status status) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_CUSTOMER_QUERY)) {
            statement.setInt(3, status.getId());
            statement.setInt(1, status.getStatus());
            statement.setInt(2, status.getOrder_id());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
