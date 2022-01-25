package spicy.lazarus.command.commands;

import spicy.lazarus.command.Command;

public class TestCommand extends Command {
    public TestCommand() {
        super("test", "Command for testing purposes", "");
    }

    @Override
    public void exec(String[] args, String alias) {
        assert mc.player != null;
        mc.player.sendChatMessage("WTF");
    }
}
