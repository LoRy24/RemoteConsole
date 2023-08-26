package com.github.lory24.remoteconsole.velocity.config;

import com.github.lory24.remoteconsole.velocity.RemC;
import lombok.Getter;
import lombok.SneakyThrows;

import java.io.File;
import java.io.FileWriter;
import java.util.Objects;

/**
 * The plugin configuration object contains all the stuff related to the plugin configuration system, such as the config.yml
 * file and the Yaml object.
 *
 * @author LoRy24
 */
@Getter
public class PluginConfiguration {

    /**
     * The folder of the plugin
     */
    private final File dataFolder = new File("./plugins/remoteconsolevelocity/");

    /**
     * The config file
     */
    private final File configFile;

    /**
     * The YamlConfiguration object. Created by LoRy24
     */
    private YamlConfiguration yamlConfig;

    /**
     * Initializes this object. If the data folder doesn't exist, this constructor will create it.
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public PluginConfiguration() {
        // Create the data folder if it doesn't exist
        if (!this.dataFolder.exists())
            this.dataFolder.mkdir();

        // Set a value to the config file
        this.configFile = new File(this.dataFolder, "config.yml");
    }

    /**
     * Loads the configuration of the plugin. Returns the updated PluginConfiguration object.
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    @SneakyThrows
    public PluginConfiguration loadConfiguration() {

        // This block is designed to create and load data into the config file
        configFileCreationAndLoading: {
            // Create the config.yml file
            if (!this.configFile.exists()) this.configFile.createNewFile();
            else break configFileCreationAndLoading;

            // Load the data into the config file
            FileWriter configFileWriter = new FileWriter(this.configFile);
            configFileWriter.write(new String(Objects.requireNonNull(RemC.class.getClassLoader().getResourceAsStream("config.yml")).readAllBytes()));
            configFileWriter.flush();
            configFileWriter.close();
        }

        // Load the config into an object
        this.yamlConfig = new YamlConfiguration().loadConfig(this.configFile);

        // Return the updated obj
        return this;
    }
}
