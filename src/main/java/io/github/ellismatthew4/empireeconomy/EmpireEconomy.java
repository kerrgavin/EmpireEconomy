package io.github.ellismatthew4.empireeconomy;

import io.github.ellismatthew4.empireeconomy.cmd.*;
import io.github.ellismatthew4.empireeconomy.permissions.EmperorService;
import io.github.ellismatthew4.empireeconomy.utils.*;
import io.github.ellismatthew4.empireeconomy.events.deathListener;
import io.github.ellismatthew4.empireeconomy.events.joinListener;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.MemorySection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.logging.Logger;

public final class EmpireEconomy extends JavaPlugin {
    private final LoggerService logger = LoggerService.getInstance(getLogger());
    private final DataStoreService dataStoreService = DataStoreService.getInstance();
    private final EmperorService emperorService = EmperorService.getInstance(this);

    @Override
    public void onEnable() {
        logger.info("Activating gamer mode...");
        new CommandLoader()
                .withCommand(new CreateMoney())
                .withCommand(new Balance())
                .withCommand(new Emperor())
                .withCommand(new GodMode())
                .withCommand(new Pay())
                .withCommand(new FindEmperor())
                .withCommand(new Challenge(this))
                .load(this);
        new EventLoader()
                .withEvent(new deathListener(this))
                .withEvent(new joinListener(this))
                .load(this);
    }

    @Override
    public void onDisable() {
        logger.info("Deactivating gamer mode...");
        dataStoreService.writeData();
    }
}