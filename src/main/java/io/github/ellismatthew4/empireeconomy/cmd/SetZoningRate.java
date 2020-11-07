package io.github.ellismatthew4.empireeconomy.cmd;

import io.github.ellismatthew4.empireeconomy.EmpireEconomy;
import io.github.ellismatthew4.empireeconomy.utils.CommandValidationHelper;
import org.bukkit.configuration.ConfigurationSection;

public class SetZoningRate extends PluginCommand {
    private ConfigurationSection data;
    private EmpireEconomy plugin;

    public SetZoningRate(EmpireEconomy plugin, ConfigurationSection data) {
        super("setzoningrate");
        this.plugin = plugin;
        this.data = data;
    }

    @Override
    public boolean onCommand(SenderContainer senderContainer, CommandCall commandCall) {
        data.set("zoningRate", commandCall.getArg(0).asInt());
        senderContainer.getPlayer().sendMessage("Zoning rate set to " + commandCall.getArg(0).arg);
        return true;
    }

    public boolean validate(SenderContainer senderContainer, CommandCall commandCall) {
        CommandValidationHelper validationHelper = new CommandValidationHelper(this, senderContainer, commandCall);
        return validationHelper.isChallengeInactive(plugin) && validationHelper.isSenderPlayer() && validationHelper.isValidArgCount(1);
    }
}
