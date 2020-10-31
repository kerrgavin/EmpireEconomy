package io.github.ellismatthew4.empireeconomy;

import io.github.ellismatthew4.empireeconomy.cmd.*;
import io.github.ellismatthew4.empireeconomy.events.EventLoader;
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
        new CommandLoader()
                .withCommand(new CreateMoney())
                .withCommand(new Balance())
                .withCommand(new Emperor())
                .withCommand(new GodMode())
                .withCommand(new Pay())
                .load(this);
        new EventLoader()
                .withEvent(new deathListener())
                .withEvent(new joinListener())
                .load(this);
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
