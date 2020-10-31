package io.github.ellismatthew4.empireeconomy.utils;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.logging.Logger;

public class PluginIO {
    private Logger logger;

    public PluginIO(Logger logger) {
        this.logger = logger;
    }

    public void writeYml(String path, YamlConfiguration yamlConfiguration) {
        try {
            PrintWriter out = new PrintWriter(path);
            out.println(yamlConfiguration.saveToString());
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            logger.warning("Could not save yml file: " + path);
        }
    }

    public YamlConfiguration readYml(String path) {
        logger.info("Reading YML file: " + path);
        return YamlConfiguration.loadConfiguration(new File(path));
    }
}
