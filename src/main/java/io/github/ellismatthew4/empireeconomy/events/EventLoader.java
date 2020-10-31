package io.github.ellismatthew4.empireeconomy.events;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Set;

public class EventLoader {
    private Set<Listener> eventSet;

    public EventLoader() {
        this.eventSet = new HashSet<>();
    }

    public EventLoader withEvent(Listener listener) {
        this.eventSet.add(listener);
        return this;
    }

    public EventLoader removeEvent(Listener listener) {
        this.eventSet.remove(eventSet);
        return this;
    }

    public void load(JavaPlugin javaPlugin) {
        for(Listener listener: eventSet) {
            javaPlugin.getServer().getPluginManager().registerEvents(listener, javaPlugin);
        }
    }
}
