package io.github.ellismatthew4.empireeconomy.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.logging.Logger;

public abstract class PluginCommand implements CommandExecutor {
    public final String command;
    public Logger logger;

    public PluginCommand(String command) {
        this.command = command;
    }
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String alias, String[] args) {
        SenderContainer senderContainer = new SenderContainer(commandSender);
        CommandCall commandCall = new CommandCall(command, alias, args);
        if (validate(senderContainer, commandCall)) {
            return onCommand(senderContainer, commandCall);
        }
        return false;
    }

    public abstract boolean onCommand(SenderContainer senderContainer, CommandCall commandCall);

    public abstract boolean validate(SenderContainer senderContainer, CommandCall commandCall);


}
