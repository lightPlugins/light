package io.lightplugins.light.api.creators;

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

            try (ResultSet rs = ps.executeQuery()) {
                return rs;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * Asynchronously executes the given prepared statement and returns a CompletableFuture with the result as boolean.
     *
     * @param ps the prepared statement to be executed
     * @return a CompletableFuture that will hold the boolean result
     */
    public CompletableFuture<Boolean> execute(PreparedStatement ps) {
        return CompletableFuture.supplyAsync(() -> {

            try {
                // return true if successful
                return ps.execute();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * Synchronously executes an update query using the provided PreparedStatement.
     *
     * @param ps the PreparedStatement object containing the update query
     * @return an Integer representing the number of rows updated
     */
    public Integer executeUpdate(PreparedStatement ps) throws SQLException { return ps.executeUpdate(); }
}
