package de.lightplugins.light.api.creators;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.CompletableFuture;

public class FutureCreator {

    /**
     * Asynchronously executes the given prepared statement and returns a CompletableFuture with the result set.
     *
     * @param ps the prepared statement to be executed
     * @return a CompletableFuture that will hold the result set
     */
    public CompletableFuture<ResultSet> executeQuery(PreparedStatement ps) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                // return the result set
                return ps.executeQuery();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * Asynchronously executes an update query using the provided PreparedStatement.
     *
     * @param ps the PreparedStatement object containing the update query
     * @return a CompletableFuture<Integer> representing the number of rows updated
     */
    public CompletableFuture<Integer> executeUpdate(PreparedStatement ps) {

        return CompletableFuture.supplyAsync(() -> {

            try {
                // return the number of updated rows (int) > 0 = success
                return ps.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
