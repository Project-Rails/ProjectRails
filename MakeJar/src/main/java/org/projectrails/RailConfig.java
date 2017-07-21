package org.projectrails;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

public class RailConfig {
    private Configuration configuration = null;
    public File configFile;

    public RailConfig(String name) {
        this.configFile = new File(name);
    }

    public RailConfig(File f) {
        this.configFile = f;
    }

    public final Configuration getConfig() {
        return this.configuration;
    }

    public final boolean saveDefaultConfig() {
        if (!configFile.exists()) {
            InputStream def = getClass().getClassLoader().getResourceAsStream(configFile.getName());
            if (def != null) {
                try (FileOutputStream write = new FileOutputStream(configFile)) {
                    org.apache.commons.io.IOUtils.copy(def, write);
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

        if (defaultConfigStream != null) {
            defaultConfig = ConfigurationProvider.getProvider(YamlConfiguration.class).load(defaultConfigStream);
        }

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
        if (configuration == null) reloadConfig();

        if (!configuration.contains(path)) {
            configuration.set(path, value);
            saveConfig();
        }
    }
}