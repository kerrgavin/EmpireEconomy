package io.github.ellismatthew4.empireeconomy.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Balance extends PluginCommand {
    private YamlConfiguration currency;

    public Balance(YamlConfiguration currency) {
        super("balance");
        this.currency = currency;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player p = (Player) commandSender;
            p.sendMessage("You currently have $" + currency.getString(p.getDisplayName()));
        }
        return true;
    }
}
