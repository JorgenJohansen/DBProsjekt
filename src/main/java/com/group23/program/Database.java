package com.group23.program;

import java.sql.*;
import java.lang.reflect.*;

/**
 * Class containing domain-agnostic database-operations.
 */
public class Database {

    private String db_URI;
    private String db_user;
    private String db_pw;

    /**
     * Constructor for database object
     * @param address is the URI to the database
     * @param username is username with CRUD-access
     * @param password is password for user to database
     */
    public Database(String address, String username, String password) {

        //try {
        //    Class.forName("com.mysql.jdbc.Driver");
        //} catch (Exception e) {
        //    e.printStackTrace();
        //}


        db_URI = address;
        db_user = username;
        db_pw = password;

        try (Connection connection = getConnection()) {
            System.out.println("Connection established!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create a connection to the database
     * @return a new connection to the database
     * @throws SQLException if driver is missing
     */
    protected Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(db_URI, db_user, db_pw);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * Creates a table on the database
     * @param table is an object containing a row in a database
     * @throws SQLException if the query is malformed
     */
    public int create(DatabaseTable table) throws SQLException {

        // Retrieve information from the class to generate query
        String className = table.getClass().getSimpleName();
        Field[] fields = table.getClass().getDeclaredFields();

        // Build string for prepared statement
        String queryString = "INSERT INTO ";
        queryString += className;
        queryString += "(";
        for (Field f : fields) {
            queryString += f.getName() + ",";
        }
        queryString = Util.stripTrailingComma(queryString);
        queryString += ") VALUES(";
        for (Field f : fields) {
            queryString += "?,";
        }
        queryString = Util.stripTrailingComma(queryString);
        queryString += ")";

        System.out.println("QUERY: " +queryString);

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(queryString, Statement.RETURN_GENERATED_KEYS)) {

            // Iterate over all fields of the class
            for (int i = 0; i < fields.length; i++) {

                // We need this to access private fields
                fields[i].setAccessible(true);

                try {
                    switch (fields[i].getType().getSimpleName()) {
                        case "int":
                            preparedStatement.setInt(i+1, (int)fields[i].get(table));
                            break;
                        case "double":
                            preparedStatement.setDouble(i+1, (double)fields[i].get(table));
                            break;
                        case "String":
                            preparedStatement.setString(i+1, (String)fields[i].get(table));
                            break;
                        default:
                            throw new IllegalAccessException("Unknown field type: " + fields[i].getType().getSimpleName() + " " + fields[i].get(table));

                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                System.out.println("Insertion complete.");
                return generatedKeys.getInt(1);
            }
            else {
                throw new SQLException("Unable to insert into table");
            }


        } catch (SQLException e) {
            throw e;
        }

    }

    /**
     * Updates a table in the database
     * @param table is an object containing a row in a database
     * @throws SQLException if the query is malformed
     */
    public void update(DatabaseTable table) throws SQLException {

        // Retrieve information from the class to generate query
        String className = table.getClass().getSimpleName();
        Field[] fields = table.getClass().getDeclaredFields();

        // Build string for prepared statement
        String queryString = "UPDATE ";//Person SET Navn = ? WHERE Pnr = ?";
        queryString += className;
        queryString += " SET";
        queryString += "(";
        for (Field f : fields) {
            queryString += f.getName() + ",";
        }
        queryString = Util.stripTrailingComma(queryString);
        queryString += ") VALUES(";
        for (Field f : fields) {
            queryString += "?,";
        }
        queryString = Util.stripTrailingComma(queryString);
        queryString += ") ";
        queryString += " WHERE id = ?";

        System.out.println("QUERY: " +queryString);

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(queryString)) {

            // Iterate over all fields of the class
            for (int i = 0; i < fields.length; i++) {

                // We need this to access private fields
                fields[i].setAccessible(true);

                try {
                    switch (fields[i].getType().getSimpleName()) {
                        case "int":
                            preparedStatement.setInt(i+1, (int)fields[i].get(table));
                            break;
                        case "double":
                            preparedStatement.setDouble(i+1, (double)fields[i].get(table));
                            break;
                        case "String":
                            preparedStatement.setString(i+1, (String)fields[i].get(table));
                            break;
                        default:
                            throw new IllegalAccessException("Unknown field type");

                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            preparedStatement.setInt(fields.length, table.getId());

            preparedStatement.executeUpdate();
            System.out.println("Update complete.");
        } catch (SQLException e) {
            throw e;
        }

    }

    /**
     * Deletes a row from the database
     * @param table is an object containing a row in a database
     * @throws SQLException if the query is malformed
     */
    public void delete(DatabaseTable table) throws SQLException {

        // Retrieve information from the class to generate query
        String className = table.getClass().getSimpleName();
        Field[] fields = table.getClass().getDeclaredFields();

        String queryString = "DELETE FROM ";
        queryString += className;
        queryString += " WHERE id = ?";


        // We connect to the database
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(queryString)) {

            preparedStatement.setInt(1, table.getId());

            preparedStatement.executeUpdate();
            System.out.println("Delete complete.");
        } catch (SQLException e) {
            throw e;
        }
    }

}
