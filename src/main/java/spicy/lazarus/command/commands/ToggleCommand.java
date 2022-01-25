package spicy.lazarus.command.commands;

import spicy.lazarus.command.Command;
import spicy.lazarus.module.Module;
import spicy.lazarus.module.ModuleManager;
import spicy.lazarus.util.client.ChatUtil;

public class ToggleCommand extends Command {
    public ToggleCommand() {
        super("toggle", "Toggles modules.", "[modules]", "t");
    }

    @Override
    public void exec(String[] args, String alias) {
        if (args.length == 0) sendInvalidSyntax(alias);
        else {
            Module module = ModuleManager.getModule(args[0]);
            if(module != null) {
                module.toggle();
                ChatUtil.printInfo(args[0] + " " + module.getEnabled());
                return;
            } else {
                sendUnknownModule(args[0]);
            }
        }
    }
}