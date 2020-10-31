package io.github.ellismatthew4.empireeconomy.cmd;

import org.bukkit.command.Command;

import java.util.ArrayList;
import java.util.List;

public class CommandCall {
    public final Command command;
    public final String alias;
    public final List<CommandArgument> args;

    public CommandCall(Command command, String alias, String[] args) {
        this.command = command;
        this.alias = alias;
        List<CommandArgument> temp = new ArrayList<>();
        for(String arg: args) {
            temp.add(new CommandArgument(arg));
        }
        this.args = temp;
    }

    public CommandArgument getArg(int index) {
        return this.args.get(0);
    }
}
