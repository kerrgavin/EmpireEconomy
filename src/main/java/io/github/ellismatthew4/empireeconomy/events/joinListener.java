package io.github.ellismatthew4.empireeconomy.events;

import io.github.ellismatthew4.empireeconomy.EmpireEconomy;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class joinListener implements Listener{
    @EventHandler
    public void onPlayerDeath(PlayerJoinEvent e) {
        YamlConfiguration currency = EmpireEconomy.getCurrencyObject();
        if (!e.getPlayer().hasPlayedBefore()) {
            currency.set(e.getPlayer().getDisplayName(), 0);
        }
    }
}
