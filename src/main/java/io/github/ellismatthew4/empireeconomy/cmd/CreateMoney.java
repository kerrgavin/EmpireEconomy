package io.github.ellismatthew4.empireeconomy.cmd;

import io.github.ellismatthew4.empireeconomy.EmpireEconomy;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class CreateMoney extends PluginCommand {
    private YamlConfiguration currency;

    public CreateMoney(YamlConfiguration currency) {
        super("createmoney");
        this.currency = currency;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            String p = ((Player) commandSender).getDisplayName();
            int amount = Integer.parseInt(strings[0]);
            int balance = currency.getInt(p);
            currency.set(p, balance + amount);
            return true;
        }
        return false;
    }
}
