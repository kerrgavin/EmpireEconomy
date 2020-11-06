package io.github.ellismatthew4.empireeconomy.cmd;

import io.github.ellismatthew4.empireeconomy.utils.CommandValidationHelper;
import io.github.ellismatthew4.empireeconomy.utils.Zone;
import io.github.ellismatthew4.empireeconomy.utils.ZoneHandler;
import io.github.ellismatthew4.empireeconomy.utils.ZoningCache;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Claim extends PluginCommand {
    private ZoningCache cache;
    private JavaPlugin plugin;
    private ZoneHandler zoneHandler;

    public Claim(JavaPlugin plugin, ZoneHandler zoneHandler) {
        super("claim");
        this.plugin = plugin;
        this.zoneHandler = zoneHandler;
        this.cache = ZoningCache.getInstance();
    }

    @Override
    public boolean onCommand(SenderContainer senderContainer, CommandCall commandCall) {
        Player p = senderContainer.getPlayer();
        Location[] ls = cache.get(p);
        Zone z = new Zone(ls[0], ls[1], p.getDisplayName(), commandCall.getArg(0).arg);
        boolean success = zoneHandler.addZone(p, z);
        if (success) {
            p.sendMessage("Claim successful!");
            return true;
        }
        p.sendMessage("Claim failed.");
        return false;
    }

    public boolean validate(SenderContainer senderContainer, CommandCall commandCall) {
        CommandValidationHelper validationHelper = new CommandValidationHelper(this, senderContainer, commandCall);
        return validationHelper.isSenderPlayer() && validationHelper.isValidArgCount(1);
    }
}
