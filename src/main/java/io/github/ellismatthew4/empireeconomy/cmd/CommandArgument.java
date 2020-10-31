package io.github.ellismatthew4.empireeconomy.cmd;

import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getPlayer;

public class CommandArgument {
    public String arg;

    public CommandArgument(String arg) {
        this.arg = arg;
    }

    public Integer asInt() {
        return (arg == null) ? null : Integer.parseInt(arg);
    }

    public Double asDouble() {
        return (arg == null) ? null : Double.parseDouble(arg);
    }

    public Player asPlayer() {
        return (arg == null) ? null : getPlayer(arg);
    }
}
