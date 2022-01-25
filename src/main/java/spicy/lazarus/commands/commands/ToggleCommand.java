package spicy.lazarus.commands.commands;

import spicy.lazarus.Lazarus;
import spicy.lazarus.commands.Command;
import spicy.lazarus.modules.Module;
import spicy.lazarus.utils.client.ChatUtil;

public class ToggleCommand extends Command {
    public ToggleCommand() {
        super("toggle", "Toggles modules.", "[modules]", "t");
    }

    @Override
    public void exec(String[] args, String alias) {
        if (args.length == 0) sendInvalidSyntax(alias);
        else {
            Module module = Lazarus.moduleManager.getModule(args[0]);
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