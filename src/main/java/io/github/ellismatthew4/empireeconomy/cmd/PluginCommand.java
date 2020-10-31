package io.github.ellismatthew4.empireeconomy.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class PluginCommand implements CommandExecutor {
    public final String command;

    public PluginCommand(String command) {
        this.command = command;
    }
    @Override
    public abstract boolean onCommand(CommandSender commandSender, Command command, String s, String[] Strings);
}
