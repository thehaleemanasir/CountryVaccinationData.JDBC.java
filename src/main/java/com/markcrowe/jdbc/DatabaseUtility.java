package com.markcrowe.jdbc;

import java.sql.*;

public class DatabaseUtility {
    private static final String URL = "jdbc:mysql://localhost:3306/country_vaccination_data_2021";
    private static final String USER = "root";
    private static final String PASSWORD = "Password1";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    /**
     * Get the new ID for an inserted record from the prepared statement.
     * Basically, this method is used to get the new Auto-Incremented Column value of the newly inserted record.
     * Your PreparedStatement must be created with the Statement.RETURN_GENERATED_KEYS flag.
     * Assuming the ID column is the first column in the ResultSet.
     * <p>
     * Example:
     * <pre>
     *         String query = "INSERT INTO table_name (column_name) VALUES (?)";
     *         try (Connection connection = DatabaseUtility.getConnection();) {
     *             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
     *             preparedStatement.setString(1, "value");
     *             preparedStatement.executeUpdate();
     *             int newId = DatabaseUtility.getNewId(preparedStatement);
     *             System.out.println("New ID: " + newId);
     *         }
     *         </pre>
     * </p>
     *
     * @param preparedStatement The PreparedStatement used to insert the record.
     * @return The new ID of the inserted record.
     * @throws SQLException If the ID could not be obtained.
     **/
    public static Integer getNewId(PreparedStatement preparedStatement) throws SQLException {
        return getNewId(preparedStatement, 1);
    }

    /**
     * Get the new ID for an inserted record from the prepared statement.
     * Basically, this method is used to get the new Auto-Incremented Column value of the newly inserted record.
     * Your PreparedStatement must be created with the Statement.RETURN_GENERATED_KEYS flag.
     * <p>
     * Example:
     * <pre>
     *         String query = "INSERT INTO table_name (column_name) VALUES (?)";
     *         try (Connection connection = DatabaseUtility.getConnection();) {
     *             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
     *             preparedStatement.setString(1, "value");
     *             preparedStatement.executeUpdate();
     *             int newId = DatabaseUtility.getNewId(preparedStatement);
     *             System.out.println("New ID: " + newId);
     *         }
     *         </pre>
     * </p>
     *
     * @param preparedStatement The PreparedStatement used to insert the record.
     * @param columnIndex       The index of the column in the ResultSet.
     * @return The new ID of the inserted record.
     * @throws SQLException If the ID could not be obtained.
     */
    public static Integer getNewId(PreparedStatement preparedStatement, int columnIndex) throws SQLException {
        try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                return generatedKeys.getInt(columnIndex);
            }
        }
        throw new SQLException("Creating property failed, no ID obtained.");
    }

    private DatabaseUtility() {
        throw new IllegalStateException("Utility class");
    }
}
