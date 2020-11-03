package io.github.ellismatthew4.empireeconomy.cmd;

import io.github.ellismatthew4.empireeconomy.EmpireEconomy;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.java.JavaPlugin;

public class Emperor extends PluginCommand {
    EmpireEconomy plugin;

    public Emperor(EmpireEconomy plugin) {
        super("emperor");
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
            if (strings.length != 1) {
                return false;
            }
            if (commandSender.hasPermission("ee.*")) {
                Player target = Bukkit.getPlayer(strings[0]);
                plugin.setEmperor(target.getDisplayName());
                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "title @a subtitle \"has been crowned Emperor!\"");
                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "title @a title \"§6" + target.getDisplayName() + "\"");
            }
        return true;
    }
}
