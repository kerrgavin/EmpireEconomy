package io.github.ellismatthew4.empireeconomy.cmd;

import io.github.ellismatthew4.empireeconomy.permissions.EmperorService;
import io.github.ellismatthew4.empireeconomy.utils.DataStoreService;
import io.github.ellismatthew4.empireeconomy.utils.LoggerService;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public abstract class PluginCommand implements CommandExecutor {
    public final String command;
    public final LoggerService loggerService = LoggerService.getInstance();
    public final DataStoreService dataStoreService = DataStoreService.getInstance();
    public final EmperorService emperorService = EmperorService.getInstance();

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
