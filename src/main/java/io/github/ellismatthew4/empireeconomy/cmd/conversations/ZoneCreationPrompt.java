package io.github.ellismatthew4.empireeconomy.cmd.conversations;

import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.bukkit.conversations.ValidatingPrompt;

public class ZoneCreationPrompt extends ValidatingPrompt {
    private int cost;

    public ZoneCreationPrompt(int cost) {
        this.cost = cost;
    }

    @Override
    public String getPromptText(ConversationContext context) {
        return "This operation will cost you $" + cost + ". Type confirm to continue.";
    }

    @Override public boolean blocksForInput(ConversationContext context) {
        return true;
    }

    @Override
    protected boolean isInputValid(ConversationContext context, String input) {
        return input.toLowerCase().equals("confirm");
    }

    @Override
    protected Prompt acceptValidatedInput(ConversationContext context, String input) {
        return Prompt.END_OF_CONVERSATION;
    }
}
