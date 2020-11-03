package io.github.ellismatthew4.empireeconomy.cmd;

import io.github.ellismatthew4.empireeconomy.EmpireEconomy;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class CreateMoney extends PluginCommand {
    private ConfigurationSection currency;

    public CreateMoney(ConfigurationSection currency) {
        super("createmoney");
        this.currency = currency;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player && strings.length == 1) {
            Player p = (Player) commandSender;
            int amount = Integer.parseInt(strings[0]);
            int balance = currency.getInt(p.getDisplayName());
            currency.set(p.getDisplayName(), balance + amount);
            p.sendMessage("$" + amount + " created.");
            return true;
        }
        return false;
    }
}
