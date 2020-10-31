package io.github.ellismatthew4.empireeconomy.cmd;

import io.github.ellismatthew4.empireeconomy.utils.CommandValidationHelper;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Pay extends PluginCommand {
    private final YamlConfiguration currency;

    public Pay(YamlConfiguration currency) {
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
        return true;
    }

    @Override
    public boolean validate(SenderContainer senderContainer, CommandCall commandCall) {
        CommandValidationHelper validationHelper = new CommandValidationHelper(this, senderContainer, commandCall);
        return validationHelper.isSenderPlayer() && validationHelper.isValidArgCount(2)
                && validationHelper.isValidPlayer(commandCall.getArg(0)) && validationHelper.isValidAmount(commandCall.getArg(1));
    }
}
