package org.projectrails;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.commons.io.IOUtils;

import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

public class RailConfig {
    private Configuration configuration = null;
    private File configFile;

    public RailConfig(String name) {
        this.configFile = new File(name);
    }

    public final Configuration getConfig() {
        return configuration;
    }

    public final boolean saveDefaultConfig() {
        if (!configFile.exists()) {
            InputStream def = getClass().getClassLoader().getResourceAsStream(configFile.getName());
            if (def != null) {
                try {
                    FileOutputStream write = new FileOutputStream(configFile);
                    
                    IOUtils.copy(def, write);

                    write.close();
                    def.close();
                    return true;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.err.println("Could not find " + configFile.getName() + " in jar.");
                return false;
            }
        }
        return false;
    }

    public final Configuration reloadConfig() {
        Configuration defaultConfig = null;
        InputStream defaultConfigStream = getClass().getClassLoader().getResourceAsStream(configFile.getName());

        if (defaultConfigStream != null)
            defaultConfig = ConfigurationProvider.getProvider(YamlConfiguration.class).load(defaultConfigStream);

        if (!configFile.exists()) return (configuration = defaultConfig);

        try {
            return (configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(configFile,
                    defaultConfig));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public final void saveConfig() {
        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(configuration, configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public final void addDefault(String path, Object value) {
        if (!configuration.contains(path)) {
            configuration.set(path, value);
            saveConfig();
        }
    }

    public final void addDefault(String path, int value) {
        if (!configuration.contains(path)) {
            configuration.set(path, value);
            saveConfig();
        }
    }

    public final void addDefault(String path, boolean value) {
        if (!configuration.contains(path)) {
            configuration.set(path, value);
            saveConfig();
        }
    }
}