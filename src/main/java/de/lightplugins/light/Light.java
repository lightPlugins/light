package de.lightplugins.light;

import com.zaxxer.hikari.HikariDataSource;
import de.lightplugins.light.api.LightAPI;
import de.lightplugins.light.api.database.Connection;
import de.lightplugins.light.api.files.FileManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

public class Light extends JavaPlugin {

    public static String consolePrefix = "[Light] ";
    public static FileManager database;
    public static Light getInstance;
    public static LightAPI api;

    public HikariDataSource ds;


    @Override
    public void onLoad() {
        getInstance = this;

        api = new LightAPI(this);
        database = api.createNewFile("database.yml", true);

        Connection connection = new Connection(this);

        if(database.getConfig().getBoolean("mysql.enable")) {
            connection.connectMariaDB();
        } else {
            connection.connectSQLite();
        }

        if(ds.isClosed()) {
            api.getDebugPrinting().print("§4Could not connect to the database. Please check your database.yml");
            api.getDebugPrinting().print("§4Light is shutting down... ");
            onDisable();
        }

    }

    public void onEnable() {


    }

    public void onDisable() {
        super.onDisable();
    }

    private CompletableFuture<Objects> createTable(String query) {


        return null;
    }
}