package io.github.ellismatthew4.empireeconomy.cmd;

import io.github.ellismatthew4.empireeconomy.utils.CommandValidationHelper;
import io.github.ellismatthew4.empireeconomy.EmpireEconomy;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

public class CreateMoney extends PluginCommand {
    private ConfigurationSection currency;

    public CreateMoney(ConfigurationSection currency) {
        super("createmoney");
        this.currency = currency;
    }

    @Override
    public boolean onCommand(SenderContainer senderContainer, CommandCall commandCall) {
        Player p = senderContainer.getPlayer();
        int amount = commandCall.getArg(0).asInt();
        int balance = currency.getInt(p.getDisplayName());
        currency.set(p.getDisplayName(), balance + amount);
        p.sendMessage("$" + amount + " created.");
        return true;
    }

    public boolean validate(SenderContainer senderContainer, CommandCall commandCall) {
        CommandValidationHelper validationHelper = new CommandValidationHelper(this, senderContainer, commandCall);
        return validationHelper.isSenderPlayer() && validationHelper.isValidArgCount(1);
    }
}
