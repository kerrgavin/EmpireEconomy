package io.github.ellismatthew4.empireeconomy.cmd;

import io.github.ellismatthew4.empireeconomy.EmpireEconomy;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Pay extends PluginCommand {
    private YamlConfiguration currency;

    public Pay(YamlConfiguration currency) {
        super("pay");
        this.currency = currency;
    }

    // /pay <target> <amount>
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            if (strings.length != 2) {
                return false;
            }
            Player p = (Player) commandSender;
            Player target = Bukkit.getPlayer(strings[0]);
            int amountToPay;
            try {
                amountToPay = Integer.parseInt(strings[1]);
                if (amountToPay < 0) {
                    p.sendMessage("Don't pay people negative money. Have them pay you.");
                    return false;
                }
            } catch(Exception e) {
                p.sendMessage("Don't do that.");
                return false;
            }
            int balance = currency.getInt(p.getDisplayName());
            if (balance < amountToPay) {
                p.sendMessage("You do not have enough money to do that.");
                return false;
            }
            int targetBalance = currency.getInt(target.getDisplayName());
            currency.set(p.getDisplayName(), balance - amountToPay);
            currency.set(target.getDisplayName(), currency.getInt(target.getDisplayName()) + amountToPay);
            p.sendMessage("You paid $" + amountToPay + " to " + target.getDisplayName());
        }
        return true;
    }
}
