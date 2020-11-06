package io.github.ellismatthew4.empireeconomy.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.util.logging.Logger;

public class Zone {
    public Location loc1;
    public Location loc2;
    public String name;
    private String owner;
    public String msg = "";

    public Zone(Location loc1, Location loc2, String owner, String name) {
        this.loc1 = new Location(
                loc1.getWorld(),
                Math.floor(loc1.getX()),
                Math.floor(loc1.getY()),
                Math.floor(loc1.getZ())
        );
        this.loc2 = new Location(
                loc2.getWorld(),
                Math.floor(loc2.getX()),
                Math.floor(loc2.getY()),
                Math.floor(loc2.getZ())
                );
        this.owner = owner;
        this.name = name;
    }

    public Zone(Logger log, ConfigurationSection zone) {
        this.owner = zone.getString("owner");
        this.name = zone.getString("name");
        this.msg = zone.getString("msg");
        this.loc1 = new Location(
                Bukkit.getWorld(zone.getString("world")),
                zone.getDouble("loc1X"),
                zone.getDouble("loc1Y"),
                zone.getDouble("loc1Z")
        );
        this.loc2 = new Location(
                Bukkit.getWorld(zone.getString("world")),
                zone.getDouble("loc2X"),
                zone.getDouble("loc2Y"),
                zone.getDouble("loc2Z")
        );
        log.info("Finished loading zone " + name);
    }

    public YamlConfiguration save() {
        YamlConfiguration res = new YamlConfiguration();
        res.set("world", loc1.getWorld().getName());
        res.set("owner", owner);
        res.set("name", name);
        res.set("msg", msg);
        res.set("loc1X", loc1.getX());
        res.set("loc2X", loc2.getX());
        res.set("loc1Y", loc1.getY());
        res.set("loc2Y", loc2.getY());
        res.set("loc1Z", loc1.getZ());
        res.set("loc2Z", loc2.getZ());
        return res;
    }

    public boolean inside(Location l) {
        return
                (l.getX() > Math.min(loc1.getX(), loc2.getX())) &&
                (l.getX() < Math.max(loc1.getX(), loc2.getX())) &&
                (l.getZ() > Math.min(loc1.getZ(), loc2.getZ())) &&
                (l.getZ() < Math.max(loc1.getZ(), loc2.getZ()));

    }

    public double area() {
        return Math.abs(loc1.getX() - loc2.getX()) * Math.abs(loc1.getZ() - loc2.getZ());
    }
}
