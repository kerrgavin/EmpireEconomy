package io.github.ellismatthew4.empireeconomy;

import io.github.ellismatthew4.empireeconomy.cmd.*;
import io.github.ellismatthew4.empireeconomy.events.playerClickListener;
import io.github.ellismatthew4.empireeconomy.events.zoneEntryListener;
import io.github.ellismatthew4.empireeconomy.utils.*;
import io.github.ellismatthew4.empireeconomy.events.deathListener;
import io.github.ellismatthew4.empireeconomy.events.joinListener;
import org.bukkit.Bukkit;
import org.bukkit.configuration.MemorySection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.logging.Logger;

public final class EmpireEconomy extends JavaPlugin {
    private final Logger LOGGER = getLogger();
    private final PluginIO pluginIO = new PluginIO(LOGGER);
    private final String DATA_YML_PATH = "data.yml";
    private static MemorySection currency;
    private static YamlConfiguration data;
    private EmperorPermissions emperorPermissions;
    private ZoneHandler zoneHandler;
    private zoneEntryListener zel;

    @Override
    public void onEnable() {
        LOGGER.info("Activating gamer mode...");
        emperorPermissions = new EmperorPermissions(this);
        data = pluginIO.readYml(DATA_YML_PATH);
        zoneHandler = new ZoneHandler();
        if (data.getKeys(false).contains("currency")) {
            currency = (MemorySection) data.getConfigurationSection("currency");
        } else {
            LOGGER.info("No currency data found! Creating now...");
            currency = new YamlConfiguration();
            data.set("currency", currency);
        }
        if (!data.getKeys(false).contains("challengeActive")) {
            LOGGER.info("No challenge data found! Creating now...");
            data.set("challengeActive", false);
        }
        if (data.getKeys(false).contains("zones")) {
            LOGGER.info("Loading zones...");
            MemorySection zoneData = (MemorySection) data.getConfigurationSection("zones");
            for (String i : zoneData.getKeys(false)) {
                zoneHandler.load(LOGGER, zoneData.getConfigurationSection(i));
            }
        } else {
            LOGGER.info("Zone configuration is empty, is this correct?");
        }
        zel = new zoneEntryListener(this, zoneHandler);
        new CommandLoader()
                .withCommand(new CreateMoney(this, currency))
                .withCommand(new Balance(currency))
                .withCommand(new Emperor(this))
                .withCommand(new GodMode())
                .withCommand(new Pay(currency))
                .withCommand(new FindEmperor(this))
                .withCommand(new Challenge(this, data))
                .withCommand(new Wand())
                .withCommand(new Claim(this, zoneHandler))
                .withLogger(LOGGER)
                .load(this);
        new EventLoader()
                .withEvent(new deathListener(this, data))
                .withEvent(new joinListener(this, currency))
                .withEvent(new playerClickListener(this))
                .load(this);
    }

    @Override
    public void onDisable() {
        LOGGER.info("Deactivating gamer mode...");
        data.set("currency", currency);
        data.set("zones", zoneHandler.compileZoneData());
        pluginIO.writeYml(DATA_YML_PATH, data);
    }

    public boolean isEmperor (String playerName) {
        if (data.getKeys(false).contains("emperor")) {
            return playerName.equals(data.getString("emperor"));
        }
        return false;
    }

    public void setEmperor (String playerName) {
        data.set("emperor", playerName);
        emperorPermissions.setEmperor(playerName);
    }

    public Player getEmperor() {
        return data.getKeys(false).contains("emperor") ? Bukkit.getPlayer(data.getString("emperor")) : null;
    }

    public boolean isChallengeActive() {
        return data.getBoolean("challengeActive");
    }
}

class EmperorPermissions {
    private String empName;
    private PermissionAttachment permissionAttachment;
    private JavaPlugin plugin;

    public EmperorPermissions(JavaPlugin plugin) {
        this.plugin = plugin;
        this.empName = "";
    }

    public void setEmperor(String playerName) {
        if (!empName.isEmpty()) {
            Bukkit.getPlayer(empName).removeAttachment(permissionAttachment);
        }
        Player target = Bukkit.getPlayer(playerName);
        this.empName = target.getDisplayName();
        this.permissionAttachment = target.addAttachment(plugin);
        this.permissionAttachment.setPermission("ee.emperor", true);
    }

    public String getEmpName() {
        return empName;
    }
}