package io.github.ellismatthew4.empireeconomy.cmd;

import io.github.ellismatthew4.empireeconomy.utils.CommandValidationHelper;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Balance extends PluginCommand {
    private ConfigurationSection currency;

    public Balance(ConfigurationSection currency) {
        super("balance");
        this.currency = currency;
    }

    @Override
    public boolean onCommand(SenderContainer senderContainer, CommandCall commandCall) {
        Player p = senderContainer.getPlayer();
        p.sendMessage("You currently have $" + currency.getString(p.getDisplayName()));
        return true;
    }

    @Override
    public boolean validate(SenderContainer senderContainer, CommandCall commandCall) {
        CommandValidationHelper validationHelper = new CommandValidationHelper(this, senderContainer, commandCall);
        return validationHelper.isSenderPlayer() && validationHelper.isValidArgCount(0);
    }
}
