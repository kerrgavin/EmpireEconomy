package io.github.ellismatthew4.empireeconomy;

import io.github.ellismatthew4.empireeconomy.cmd.*;
import io.github.ellismatthew4.empireeconomy.utils.CommandLoader;
import io.github.ellismatthew4.empireeconomy.utils.EventLoader;
import io.github.ellismatthew4.empireeconomy.events.deathListener;
import io.github.ellismatthew4.empireeconomy.events.joinListener;
import io.github.ellismatthew4.empireeconomy.utils.PluginIO;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class EmpireEconomy extends JavaPlugin {
    private final Logger LOGGER = getLogger();
    private final PluginIO pluginIO = new PluginIO(LOGGER);
    private final String CURRENCY_YML_PATH = "currencydata.yml";
    private static YamlConfiguration currency;

    @Override
    public void onEnable() {
        LOGGER.info("Activating gamer mode...");
        currency = pluginIO.readYml(CURRENCY_YML_PATH);
        new CommandLoader()
                .withCommand(new CreateMoney(currency))
                .withCommand(new Balance(currency))
                .withCommand(new Emperor())
                .withCommand(new GodMode())
                .withCommand(new Pay(currency))
                .load(this);
        new EventLoader()
                .withEvent(new deathListener(this))
                .withEvent(new joinListener(currency))
                .load(this);
    }

    @Override
    public void onDisable() {
        LOGGER.info("Deactivating gamer mode...");
        pluginIO.writeYml(CURRENCY_YML_PATH, currency);
    }
}
