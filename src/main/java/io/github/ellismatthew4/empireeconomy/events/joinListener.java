package io.github.ellismatthew4.empireeconomy.events;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class joinListener implements Listener{
    private YamlConfiguration currency;
    public joinListener(YamlConfiguration currency) {
        this.currency = currency;
    }

    @EventHandler
    public void onPlayerDeath(PlayerJoinEvent e) {
        if (!e.getPlayer().hasPlayedBefore()) {
            currency.set(e.getPlayer().getDisplayName(), 0);
        }
    }
}
