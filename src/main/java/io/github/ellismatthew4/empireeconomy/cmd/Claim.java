package io.github.ellismatthew4.empireeconomy.cmd;

import io.github.ellismatthew4.empireeconomy.cmd.conversations.ZoneCreationPrompt;
import io.github.ellismatthew4.empireeconomy.utils.CommandValidationHelper;
import io.github.ellismatthew4.empireeconomy.utils.Zone;
import io.github.ellismatthew4.empireeconomy.utils.ZoneHandler;
import io.github.ellismatthew4.empireeconomy.utils.ZoningCache;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationFactory;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Claim extends PluginCommand {
    private ZoningCache cache;
    private JavaPlugin plugin;
    private ZoneHandler zoneHandler;
    private ConfigurationSection data;
    private ConfigurationSection currency;

    public Claim(JavaPlugin plugin, ZoneHandler zoneHandler, ConfigurationSection data, ConfigurationSection currency) {
        super("claim");
        this.plugin = plugin;
        this.zoneHandler = zoneHandler;
        this.cache = ZoningCache.getInstance();
        this.data = data;
        this.currency = currency;
    }

    @Override
    public boolean onCommand(SenderContainer senderContainer, CommandCall commandCall) {
        Player p = senderContainer.getPlayer();
        Location[] ls = cache.get(p);
        Zone z = new Zone(ls[0], ls[1], p.getDisplayName(), commandCall.getArg(0).arg);
        int zoningCost = data.getInt("zoningRate") * z.area();
        if (currency.getInt(p.getDisplayName()) > zoningCost) {
            ConversationFactory cf = new ConversationFactory(plugin);
            cf.withFirstPrompt(new ZoneCreationPrompt(zoningCost))
                    .withTimeout(5)
                    .addConversationAbandonedListener((event) -> {
                        boolean success = event.gracefulExit();
                        if (zoneHandler.addZone(p, z) && success) {
                            currency.set(p.getDisplayName(), currency.getInt(p.getDisplayName()) - zoningCost);
                            p.sendMessage("Claim successful!");
                        } else if (!success) {
                            p.sendMessage("Claim timed out.");
                        } else {
                            p.sendMessage("Claim failed. Is this area or name taken already?");
                        }
                    });
            Conversation convo = cf.buildConversation(p);
            convo.begin();
        } else {
            p.sendMessage("You cannot afford to zone this much land. It costs $" + zoningCost + ".");
        }
        return true;
    }

    public boolean validate(SenderContainer senderContainer, CommandCall commandCall) {
        CommandValidationHelper validationHelper = new CommandValidationHelper(this, senderContainer, commandCall);
        return validationHelper.isSenderPlayer() && validationHelper.isValidArgCount(1);
    }
}
