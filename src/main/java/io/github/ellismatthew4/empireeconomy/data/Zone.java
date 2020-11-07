package io.github.ellismatthew4.empireeconomy.data;

import org.bukkit.Location;

public class Zone {
    public Location loc1;
    public Location loc2;
    public String name;
    public String owner;
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

    public boolean inside(Location l) {
        return
                (l.getX() >= Math.min(loc1.getX(), loc2.getX())) &&
                (l.getX() <= Math.max(loc1.getX(), loc2.getX())) &&
                (l.getZ() >= Math.min(loc1.getZ(), loc2.getZ())) &&
                (l.getZ() <= Math.max(loc1.getZ(), loc2.getZ()));

    }

    public int area() {
        return (int) (Math.abs(loc1.getX() - loc2.getX()) * Math.abs(loc1.getZ() - loc2.getZ()));
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
