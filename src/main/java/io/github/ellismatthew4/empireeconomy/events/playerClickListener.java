package io.github.ellismatthew4.empireeconomy.events;

import io.github.ellismatthew4.empireeconomy.utils.ZoningCache;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class playerClickListener implements Listener {
    private ZoningCache cache;

    public playerClickListener() {
        cache = ZoningCache.getInstance();
    }
    @EventHandler
    public void onPlayerClicks(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        Action a = e.getAction();
        ItemStack mainhand = p.getInventory().getItemInMainHand();

        if (a.equals(Action.RIGHT_CLICK_BLOCK)) {
            if (mainhand.getType() == Material.STICK &&
                    mainhand.getItemMeta().getDisplayName().equals("Zoning Wand")) {
                cache.add(p);
            }
        }
    }
}
