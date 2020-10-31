package io.github.ellismatthew4.empireeconomy.events;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;


public class deathListener implements Listener {
    private JavaPlugin plugin;

    public deathListener(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerDeath(EntityDeathEvent e) {
        if (e.getEntity() instanceof Player) {
            Player player = (Player) e.getEntity();
            Server server = Bukkit.getServer();
            if (player.getKiller() instanceof Player) {
                if (player.isOp()) {
                    Player killer = (Player) player.getKiller();
                    server.dispatchCommand(Bukkit.getServer().getConsoleSender(), "deop " + player.getDisplayName());
                    server.dispatchCommand(Bukkit.getServer().getConsoleSender(), "op " + killer.getDisplayName());

                    server.dispatchCommand(Bukkit.getServer().getConsoleSender(), "title @a subtitle \"has been crowned Emperor!\"");
                    server.dispatchCommand(Bukkit.getServer().getConsoleSender(), "title @a title \"§4"+ player.getDisplayName() + "\"");
                    Bukkit.getScheduler().runTaskLater(plugin, () -> {
                        server.dispatchCommand(Bukkit.getServer().getConsoleSender(), "title @a subtitle \"has been crowned Emperor!\"");
                        server.dispatchCommand(Bukkit.getServer().getConsoleSender(), "title @a title \"§6" + killer.getDisplayName() + "\"");
                    }, 40);
                }
            }
        }
    }
}