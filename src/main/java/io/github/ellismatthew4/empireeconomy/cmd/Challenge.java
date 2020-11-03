package io.github.ellismatthew4.empireeconomy.cmd;

import io.github.ellismatthew4.empireeconomy.EmpireEconomy;
import io.github.ellismatthew4.empireeconomy.utils.CommandValidationHelper;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Challenge extends PluginCommand {
    private YamlConfiguration data;
    private EmpireEconomy plugin;

    public Challenge(EmpireEconomy plugin, YamlConfiguration data) {
        super("challenge");
        this.data = data;
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(SenderContainer senderContainer, CommandCall commandCall) {
        Player p = senderContainer.getPlayer();
        Player e = plugin.getEmperor();
        if (e == null) {
            p.sendMessage("Emperor is offline.");
            return true;
        }
        if (data.getKeys(false).contains("challenger")) {
            p.sendMessage("Someone has challenged the Emperor in the past 90 minutes. Please try again later.");
            return true;
        }
        data.set("challenger", p.getDisplayName());
        e.sendMessage("You have been challenged by " + p.getDisplayName() + "!");
        data.set("challengeActive", true);
        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            data.set("challenger", null);
            e.sendMessage("You are now vulnerable to challenges again.");
        }, 108000);
        return true;
    }

    @Override
    public boolean validate(SenderContainer senderContainer, CommandCall commandCall) {
        CommandValidationHelper validationHelper = new CommandValidationHelper(this, senderContainer, commandCall);
        return validationHelper.isSenderPlayer() && validationHelper.isValidArgCount(0);
    }
}
