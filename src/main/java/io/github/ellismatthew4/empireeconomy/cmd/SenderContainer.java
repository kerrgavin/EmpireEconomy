package io.github.ellismatthew4.empireeconomy.cmd;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SenderContainer {
    public final CommandSender commandSender;

    public SenderContainer(CommandSender commandSender) {
        this.commandSender = commandSender;
    }

    public boolean isPlayer() {
        return commandSender instanceof Player;
    }

    public Player getPlayer() {
        return (isPlayer()) ? null : (Player) commandSender;
    }
}
