package io.github.ellismatthew4.empireeconomy.utils;

import io.github.ellismatthew4.empireeconomy.cmd.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class CommandLoader {
    private Map<String, PluginCommand> commandMap;
    private Logger logger;

    public CommandLoader() {
        commandMap = new HashMap<>();
    }

    public CommandLoader withCommand(PluginCommand pluginCommand) {
        this.commandMap.put(pluginCommand.command, pluginCommand);
        return this;
    }

    public CommandLoader removeCommand(String command) {
        this.commandMap.remove(command);
        return this;
    }

    public CommandLoader withLogger(Logger logger) {
        this.logger = logger;
        return this;
    }

    public void load(JavaPlugin javaPlugin) {
        if (this.logger != null) {
            for (String key : commandMap.keySet()) {
                commandMap.get(key).logger = this.logger;
                javaPlugin.getCommand(key).setExecutor(commandMap.get(key));
            }
        }
        throw new RuntimeException("No logger provided for commands");
    }
}
