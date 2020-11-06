package io.github.ellismatthew4.empireeconomy.utils;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class ZoningCache {
    private HashMap<String, Location[]> cache;
    private static ZoningCache instance;

    public ZoningCache() {
        cache = new HashMap<String, Location[]>();
    }

    public static ZoningCache getInstance() {
        if (instance == null) {
            instance = new ZoningCache();
        }
        return instance;
    }

    public boolean add(Player p) {
        try {
            Location[] temp = new Location[2];
            if (cache.containsKey(p.getDisplayName())) {
                temp = cache.get(p.getDisplayName());
            }
            if (temp[0] == null) {
                temp[0] = p.getTargetBlock(null, 5).getLocation();
                p.sendMessage("Target 1 set.");
            } else if (temp[1] == null) {
                temp[1] = p.getTargetBlock(null, 5).getLocation();
                p.sendMessage("Target 2 set.");
            } else {
                temp = new Location[2];
                temp[0] = p.getTargetBlock(null, 5).getLocation();
                p.sendMessage("Target 1 set.");
            }
            cache.put(p.getDisplayName(), temp);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
