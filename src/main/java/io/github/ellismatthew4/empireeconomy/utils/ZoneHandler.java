package io.github.ellismatthew4.empireeconomy.utils;

import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.logging.Logger;

public class ZoneHandler {
    private ArrayList<Zone> zones;

    public ZoneHandler() {
        zones = new ArrayList<Zone>();
    }
    public YamlConfiguration compileZoneData() {
        YamlConfiguration res = new YamlConfiguration();
        for (int i = 0; i < zones.size(); i++) {
            res.set(String.valueOf(i), zones.get(i).save());
        }
        return res;
    }

    public boolean addZone(Player p, Zone z) {
        if (zoneNotExists(z)) {
            zones.add(z);
        } else {
            return false;
        }
        return true;
    }

    public boolean deleteZone(String name, String player) {
        for (int i = 0; i < zones.size(); i++) {
            if (zones.get(i).name.equals(name) && zones.get(i).owner.equals(player)) {
                zones.remove(i);
                return true;
            }
        }
        return false;
    }

    private boolean zoneNotExists(Zone z) {
        for (int i = 0; i < zones.size(); i++) {
            Zone ez = zones.get(i);
            if (ez.inside(z.loc1) || ez.inside(z.loc2) || ez.name.equals(z.name)) {
                return false;
            }
        }
        return true;
    }

    public boolean zoneExists(String name) {
        for (Zone z : zones) {
            if (z.name.equals(name))
                return true;
        }
        return false;
    }

    public void load(Logger LOGGER, ConfigurationSection c) {
        zones.add(new Zone(LOGGER, c));
    }

    public Zone getZone(Location l) {
        for (int i = 0; i < zones.size(); i++) {
            Zone z = zones.get(i);
            if (z.inside(l)) {
                return z;
            }
        }
        return null;
    }

    public int getZone(String name) {
        for (int i = 0; i < zones.size(); i++) {
            Zone z = zones.get(i);
            if (z.name.equals(name))
                return i;
        }
        return -1;
    }

    public Zone getZone(int i) {
        return zones.get(i);
    }

    public void setZoneMessage(int i, String msg) {
        zones.get(i).setMsg(msg);
    }
}
