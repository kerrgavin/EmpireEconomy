package io.github.ellismatthew4.empireeconomy.cmd;

import io.github.ellismatthew4.empireeconomy.utils.CommandValidationHelper;
import io.github.ellismatthew4.empireeconomy.utils.Zone;
import io.github.ellismatthew4.empireeconomy.utils.ZoneHandler;
import org.bukkit.entity.Player;

public class SetMessage extends PluginCommand{
    private ZoneHandler zoneHandler;

    public SetMessage(ZoneHandler zoneHandler) {
        super("setmessage");
        this.zoneHandler = zoneHandler;
    }

    @Override
    public boolean onCommand(SenderContainer senderContainer, CommandCall commandCall) {
        Player p = senderContainer.getPlayer();
        int z = zoneHandler.getZone(commandCall.getArg(0).arg);
        if (z != -1 && zoneHandler.getZone(z).owner.equals(p.getDisplayName())) {
            zoneHandler.setZoneMessage(z, commandCall.getArg(1).arg);
            p.sendMessage("Changed message");
            return true;
        } else if (z == -1) {
            p.sendMessage("Zone not found");
        } else {
            p.sendMessage("You do not own that zone");
        }
        return false;
    }

    @Override
    public boolean validate(SenderContainer senderContainer, CommandCall commandCall) {
        CommandValidationHelper validationHelper = new CommandValidationHelper(this, senderContainer, commandCall);
        return validationHelper.isSenderPlayer() && validationHelper.isValidArgCount(2);
    }
}
