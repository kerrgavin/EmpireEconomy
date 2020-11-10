package io.github.ellismatthew4.empireeconomy.events;

import io.github.ellismatthew4.empireeconomy.EmpireEconomy;
import io.github.ellismatthew4.empireeconomy.permissions.EmperorService;
import io.github.ellismatthew4.empireeconomy.utils.DataStoreService;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class joinListener implements Listener{
    private final DataStoreService dataStoreService = DataStoreService.getInstance();
    private final EmperorService emperorService = EmperorService.getInstance();
    private EmpireEconomy plugin;
    public joinListener(EmpireEconomy plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        if (dataStoreService.data.currency.get(e.getPlayer().getDisplayName()) == null) {
            dataStoreService.data.currency.put(e.getPlayer().getDisplayName(), 0);
        }
        if (emperorService.isEmperor(e.getPlayer().getDisplayName())) {
            emperorService.setEmperor(e.getPlayer().getDisplayName());
        }
    }
}
