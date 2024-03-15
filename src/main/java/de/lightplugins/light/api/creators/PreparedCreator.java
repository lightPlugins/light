package de.lightplugins.light.api.creators;

import de.lightplugins.light.Light;
import de.lightplugins.light.api.util.SupportedDataType;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PreparedCreator {

    /**
     * Creates a prepared statement for the given query with the provided parameters
     *
     * @param query the SQL query string
     * @param params the parameters to be set in the query
     * @return the prepared statement with the parameters set
     * @throws SQLException if there is an error in creating the prepared statement or setting the parameters
     */
    public PreparedStatement preparedStatement(String query, Object... params) throws SQLException {

        // Create a prepared statement for the given query
        PreparedStatement ps = Light.api.getConnection().prepareStatement(query);

        // Count the number of parameters in the query string
        int count = query.length() - query.replaceAll(String.valueOf('?'), "").length();

        // Check if the number of provided parameters matches the number of placeholders in the query
        if(params.length != count) {
            throw new SQLException(
                    "Provided " + params.length + " Objects, but expected "
                            + count + " for query: " + query
            );
        }

        // Set the parameters in the prepared statement
        if(params.length != 0) {
            for (int i = 0; i < params.length; i++) {
                // Check if the data type of the parameter is supported
                if(!SupportedDataType.isSupported(params[i])) {
                    throw new SQLException(
                            "Unsupported data type for: " + params[i].getClass().getSimpleName()
                                    + " in query: " + query
                    );
                }
                ps.setObject(i + 1, params[i]);
            }
        }
        return ps;
    }
}
