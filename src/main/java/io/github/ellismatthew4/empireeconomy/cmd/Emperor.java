package io.github.ellismatthew4.empireeconomy.cmd;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Emperor extends PluginCommand {

    public Emperor() {
        super("emperor");
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (player.isOp()) {
                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "title @a subtitle \"has been crowned Emperor!\"");
                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "title @a title \"§6"+ player.getName() + "\"");
            }
        }
        return true;
    }
}
