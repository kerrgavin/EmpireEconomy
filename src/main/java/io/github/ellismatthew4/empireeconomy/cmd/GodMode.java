package io.github.ellismatthew4.empireeconomy.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GodMode extends PluginCommand {

    public GodMode() {
        super("godmode");
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            player.setInvulnerable(!player.isInvulnerable());
            player.sendMessage("God mode has been set to " + player.isInvulnerable());
        }
        return true;
    }
}
