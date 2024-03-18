package io.lightplugins.light.api;

import io.lightplugins.light.Light;
import io.lightplugins.light.api.creators.FutureCreator;
import io.lightplugins.light.api.creators.PreparedCreator;
import io.lightplugins.light.api.files.FileManager;
import io.lightplugins.light.api.files.MultiFileManager;
import io.lightplugins.light.api.util.DebugPrinting;
import io.lightplugins.light.api.creators.StatementCreator;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;
import java.sql.Connection;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LightAPI {

    private final Light light;

    public LightAPI(Light light) {
        this.light = light;
    }

    /**
     * Creates a new FileManager with the specified fileName and loadDefaultsOneReload flag.
     *
     * @param  fileName               the name of the file to create
     * @param  loadDefaultsOneReload  flag indicating whether to load defaults on reload
     * @return                       the newly created FileManager
     */
    @NotNull
    public FileManager createNewFile(String fileName, boolean loadDefaultsOneReload) {
        return new FileManager(light, fileName, loadDefaultsOneReload);
    }

    /**
     * Creates a MultiFileManager object to read multiple files from the specified directory path.
     *
     * @param directoryPath The path of the directory containing the files to be read.
     * @return A MultiFileManager object for reading multiple files.
     * @throws IOException If an I/O error occurs.
     */
    @NotNull
    public MultiFileManager readMultiFiles(String directoryPath) throws IOException {
        return new MultiFileManager(directoryPath);
    }

    /**
     * Retrieves a list of files from a MultiFileManager and adds them to the existing files list.
     *
     * @param path the path to the MultiFileManager containing the files to retrieve
     * @return the updated list of files after adding the files from the MultiFileManager.
     * @throws IOException if an I/O error occurs
     */
    @NotNull
    public List<File> getMultiFiles(String path) throws IOException {
        // Add files from the MultiFileManager to the existing files list
        return new ArrayList<>(readMultiFiles(path).getYamlFiles());
    }

    /**
     * Retrieves a list of FileConfigurations from a MultiFileManager and adds them to the existing fileConfigs list.
     *
     * @param path the path to the MultiFileManager containing the FileConfigurations to retrieve
     * @return the updated list of FileConfigurations after adding the FileConfigurations from the MultiFileManager.
     * @throws IOException if an I/O error occurs
     */
    @NotNull
    public List<FileConfiguration> getFileConfigs(String path) throws IOException {

        final List<FileConfiguration> fileConfigs = new ArrayList<>();

        getMultiFiles(path).forEach(singleFile -> {
            FileConfiguration cfg = YamlConfiguration.loadConfiguration(singleFile);
            fileConfigs.add(cfg);
        });

        return fileConfigs;
    }

    public DebugPrinting getDebugPrinting() {
        return new DebugPrinting();
    }

    /**
     * Retrieves a connection from the datasource.
     *
     * @return         	returns a Connection object
     * @throws SQLException	if an SQL exception occurs
     */
    public Connection getConnection() throws SQLException {
        // Check if the datasource is closed, return null if closed, otherwise return a connection.
        return light.ds.getConnection();
    }

    public StatementCreator getStatementCreator() {
        return new StatementCreator();
    }

    public PreparedCreator getPreparedCreator() {
        return new PreparedCreator();
    }

    public FutureCreator getFutureCreator () {
        return new FutureCreator();
    }


}
