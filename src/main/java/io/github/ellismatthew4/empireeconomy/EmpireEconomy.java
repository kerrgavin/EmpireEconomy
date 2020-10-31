package io.github.ellismatthew4.empireeconomy;

import io.github.ellismatthew4.empireeconomy.cmd.*;
import io.github.ellismatthew4.empireeconomy.events.deathListener;
import io.github.ellismatthew4.empireeconomy.events.joinListener;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public final class EmpireEconomy extends JavaPlugin {
    private static EmpireEconomy instance;
    private static YamlConfiguration currency;

    @Override
    public void onEnable() {
        getLogger().info("Activating gamer mode...");
        instance = this;
        currency = YamlConfiguration.loadConfiguration(new File("currencydata.yml"));

        getCommand("godmode").setExecutor(new GodMode());
        getCommand("emperor").setExecutor(new Emperor());
        getCommand("balance").setExecutor(new Balance());
        getCommand("pay").setExecutor(new Pay());
        getCommand("createmoney").setExecutor(new CreateMoney());
        getServer().getPluginManager().registerEvents(new deathListener(), this);
        getServer().getPluginManager().registerEvents(new joinListener(), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("Deactivating gamer mode...");
        PrintWriter out = null;
        try {
            out = new PrintWriter("currencydata.yml");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        out.println(currency.saveToString());
        out.close();
    }

    public static JavaPlugin getPlugin() {
        return instance;
    }

    public static YamlConfiguration getCurrencyObject() {
        return currency;
    }
}
