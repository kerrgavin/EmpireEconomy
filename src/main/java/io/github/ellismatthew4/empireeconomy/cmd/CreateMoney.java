package io.github.ellismatthew4.empireeconomy.cmd;

import io.github.ellismatthew4.empireeconomy.utils.CommandValidationHelper;
import org.bukkit.configuration.file.YamlConfiguration;

public class CreateMoney extends PluginCommand {
    private final YamlConfiguration currency;

    public CreateMoney(YamlConfiguration currency) {
        super("createmoney");
        this.currency = currency;
    }

    @Override
    public boolean onCommand(SenderContainer senderContainer, CommandCall commandCall) {
        String p = senderContainer.getPlayer().getDisplayName();
        int amount = commandCall.getArg(0).asInt();
        int balance = currency.getInt(p);
        currency.set(p, balance + amount);
        return true;
    }

    public boolean validate(SenderContainer senderContainer, CommandCall commandCall) {
        CommandValidationHelper validationHelper = new CommandValidationHelper(this, senderContainer, commandCall);
        return validationHelper.isSenderPlayer() && validationHelper.isValidArgCount(1);
    }
}
