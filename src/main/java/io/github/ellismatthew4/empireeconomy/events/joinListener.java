package io.github.ellismatthew4.empireeconomy.events;

import io.github.ellismatthew4.empireeconomy.EmpireEconomy;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class joinListener implements Listener{
    private ConfigurationSection currency;
    private EmpireEconomy plugin;
    public joinListener(EmpireEconomy plugin, ConfigurationSection currency) {
        this.plugin = plugin;
        this.currency = currency;
    }

    @EventHandler
    public void onPlayerDeath(PlayerJoinEvent e) {
        if (!currency.getKeys(false).contains(e.getPlayer().getDisplayName())) {
            currency.set(e.getPlayer().getDisplayName(), 0);
        }
        if (plugin.isEmperor(e.getPlayer().getDisplayName())) {
            plugin.setEmperor(e.getPlayer().getDisplayName());
        }
    }
}
