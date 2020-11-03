package io.github.ellismatthew4.empireeconomy.cmd;

import io.github.ellismatthew4.empireeconomy.utils.CommandValidationHelper;
import io.github.ellismatthew4.empireeconomy.EmpireEconomy;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Pay extends PluginCommand {
    private ConfigurationSection currency;

    public Pay(ConfigurationSection currency) {
        super("pay");
        this.currency = currency;
    }

    // /pay <target> <amount>
    @Override
    public boolean onCommand(SenderContainer senderContainer, CommandCall commandCall) {
        Player p = senderContainer.getPlayer();
        Player target = commandCall.getArg(0).asPlayer();
        int amountToPay = commandCall.getArg(1).asInt();
        int balance = currency.getInt(p.getDisplayName());
        if (balance < amountToPay) {
            p.sendMessage("You do not have enough money to do that.");
            return false;
        }
        currency.set(p.getDisplayName(), balance - amountToPay);
        currency.set(target.getDisplayName(), currency.getInt(target.getDisplayName()) + amountToPay);
        p.sendMessage("You paid $" + amountToPay + " to " + target.getDisplayName());
        target.sendMessage("You have been paid $" + amountToPay + " by " + p.getDisplayName());
        return true;
    }

    @Override
    public boolean validate(SenderContainer senderContainer, CommandCall commandCall) {
        CommandValidationHelper validationHelper = new CommandValidationHelper(this, senderContainer, commandCall);
        return validationHelper.isSenderPlayer() && validationHelper.isValidArgCount(2)
                && validationHelper.isValidPlayer(commandCall.getArg(0)) && validationHelper.isValidAmount(commandCall.getArg(1));
    }
}
