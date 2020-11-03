package io.github.ellismatthew4.empireeconomy.cmd;

import io.github.ellismatthew4.empireeconomy.utils.CommandValidationHelper;
import org.bukkit.entity.Player;

public class GodMode extends PluginCommand {

    public GodMode() {
        super("godmode");
    }

    @Override
    public boolean onCommand(SenderContainer senderContainer, CommandCall commandCall) {
        Player player = senderContainer.getPlayer();
        player.setInvulnerable(!player.isInvulnerable());
        player.sendMessage("God mode has been set to " + player.isInvulnerable());
        return true;
    }

    @Override
    public boolean validate(SenderContainer senderContainer, CommandCall commandCall) {
        CommandValidationHelper validationHelper = new CommandValidationHelper(this, senderContainer, commandCall);
        return validationHelper.isSenderPlayer() && validationHelper.isValidArgCount(0);
    }
}
