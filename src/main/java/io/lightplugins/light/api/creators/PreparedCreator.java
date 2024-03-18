package io.lightplugins.light.api.creators;

import io.lightplugins.light.Light;
import io.lightplugins.light.api.util.SupportedDataType;
import org.bukkit.Bukkit;

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

        // Check if the number of provided parameters matches the number of placeholders in the query
        if(query.contains("?") && params.length > 0) {
            if(query.chars().filter(ch -> ch == '?').count() != params.length) {
                throw new SQLException(
                        "Provided " + params.length + " Objects, but expected "
                                + query.chars().filter(ch -> ch == '?').count() + " placeholders in query: " + query
                );
            }
        }

        // Set the parameters in the prepared statement
        if(params != null) {
            for (int i = 0; i < params.length; i++) {
                // Check if the data type of the parameter is supported
                if(!SupportedDataType.isSupported(params[i]) && params[i] != null) {
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
