package io.github.ellismatthew4.empireeconomy;

import io.github.ellismatthew4.empireeconomy.cmd.GodMode;
import io.github.ellismatthew4.empireeconomy.cmd.Emperor;
import io.github.ellismatthew4.empireeconomy.events.deathListener;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public final class EmpireEconomy extends JavaPlugin {
    private static EmpireEconomy instance;

    @Override
    public void onEnable() {
        instance = this;
        getLogger().info("Activating gamer mode...");
        getCommand("godmode").setExecutor(new GodMode());
        getCommand("emperor").setExecutor(new Emperor());
        getServer().getPluginManager().registerEvents(new deathListener(), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("Deactivating gamer mode...");
    }

    public static JavaPlugin getPlugin() {
        return instance;
    }
}
