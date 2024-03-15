package de.lightplugins.light.api.creators;

public class StatementCreator {

    /**
     * Generates a SQL CREATE TABLE statement based on the provided table name, primary key, and columns.
     *
     * @param  tableName   the name of the table to be created
     * @param  primaryKey   the primary key of the table
     * @param  columns     variable number of columns to be included in the table
     * @return             the SQL CREATE TABLE statement as a String
     */
    public String createTableStatement(String tableName, String primaryKey, String... columns) {
        // Initialize a StringBuilder to build the SQL statement
        StringBuilder statement = new StringBuilder();

        // Append the initial part of the SQL statement
        statement.append("CREATE TABLE IF NOT EXISTS ").append(tableName).append(" (");

        // Add each column to the SQL statement with comma separation
        for (int i = 0; i < columns.length; i++) {
            statement.append(columns[i]);
            if (i < columns.length - 1) {
                statement.append(", ");
            }
        }

        // Append the primary key to the SQL statement
        statement.append(", ").append(primaryKey).append(" PRIMARY KEY");

        // Close the SQL statement
        statement.append(")");

        // Return the completed SQL statement as a String
        return statement.toString();
    }

    /**
     * Generates an SQL UPDATE statement for a given table with specified set values and condition.
     *
     * @param tableName the name of the table to update
     * @param setValues a comma-separated string of column=value pairs for the SET clause.
     * @param condition the condition for the WHERE clause
     * @return the generated SQL UPDATE statement
     */
    public static String createUpdateStatement(String tableName, String setValues, String condition) {
        // Initialize the statement
        StringBuilder statement = new StringBuilder();
        statement.append("UPDATE ").append(tableName).append(" SET ");

        // Split setValues into columns
        String[] columns = setValues.split(", ");
        for (String column : columns) {
            // Append each column=value pair
            statement.append(column).append(" = ?, ");
        }

        // Remove the last comma and space
        statement.delete(statement.length() - 2, statement.length());

        // Append the condition for the WHERE clause
        statement.append(" WHERE ").append(condition).append(" = ?");

        return statement.toString();
    }

    /**
     * Create an SQL INSERT statement for the specified table and columns.
     * @param tableName the name of the table
     * @param columns the columns to insert into
     * @return the SQL INSERT statement
     */
    public String createInsertStatement(String tableName, String... columns) {
        // Build the INSERT INTO statement.
        StringBuilder statement = new StringBuilder();
        statement.append("INSERT INTO ").append(tableName).append(" (");

        // Append the columns to the statement
        for (int i = 0; i < columns.length; i++) {
            statement.append(columns[i]);
            if (i < columns.length - 1) {
                statement.append(", ");
            }
        }

        // Append the VALUES placeholders to the statement
        statement.append(") VALUES (");
        for (int i = 0; i < columns.length; i++) {
            statement.append("?");
            if (i < columns.length - 1) {
                statement.append(", ");
            }
        }
        statement.append(")");

        return statement.toString();
    }

    /**
     * Creates a select SQL statement for the specified table with optional conditions.
     *
     * @param tableName the name of the table to select from
     * @param conditions optional conditions for the select statement
     * @return the generated select SQL statement as a String
     */
    public String createSelectStatement(String tableName, String... conditions) {
        // Initialize a StringBuilder to build the SQL statement
        StringBuilder statement = new StringBuilder();

        // Append the initial part of the SQL statement
        statement.append("SELECT * FROM ").append(tableName);

        // Add the WHERE clause with the selection conditions
        if (conditions != null && conditions.length > 0) {
            statement.append(" WHERE ");
            for (int i = 0; i < conditions.length; i++) {
                statement.append(conditions[i]).append(" = ?");
                if (i < conditions.length - 1) {
                    statement.append(" AND ");
                }
            }
        }

        // Return the completed SQL statement as a String
        return statement.toString();
    }

    /**
     * Creates a delete SQL statement for the specified table with optional conditions.
     *
     * @param tableName the name of the table to delete from
     * @param conditions optional conditions for the delete statement
     * @return the generated delete SQL statement as a String
     */
    public String createDeleteStatement(String tableName, String... conditions) {
        // Initialize a StringBuilder to build the SQL statement
        StringBuilder statement = new StringBuilder();

        // Append the initial part of the SQL statement
        statement.append("DELETE FROM ").append(tableName);

        // Add the WHERE clause with the deletion conditions
        if (conditions != null && conditions.length > 0) {
            statement.append(" WHERE ");
            for (int i = 0; i < conditions.length; i++) {
                statement.append(conditions[i]).append(" = ?");
                if (i < conditions.length - 1) {
                    statement.append(" AND ");
                }
            }
        }

        // Return the completed SQL statement as a String
        return statement.toString();
    }
}
