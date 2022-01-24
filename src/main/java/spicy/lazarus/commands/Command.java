package spicy.lazarus.commands;

import net.minecraft.client.MinecraftClient;
import spicy.lazarus.Lazarus;
import spicy.lazarus.utils.ChatUtils;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class Command {

    protected final MinecraftClient mc = MinecraftClient.getInstance();

    private final String name;
    private final String description;
    // <necessary1|necessary2> <necessary> [optional1|optional2] [optional] [optionalList]... syntax
    private final String syntax;
    private final String[] aliases;

    public static final int ERRORID = 3498;
    public static final int SUCCESSID = 3499;

    public Command(String command, String description, String syntax, String... aliases) {
        this.name = command;
        this.description = description;
        this.syntax = syntax;
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(aliases));
        arrayList.add(command);
        this.aliases = arrayList.toArray(new String[0]);
    }

    public final String getName() {
        return name;
    }

    public final String getFullName() {
        return CommandManager.getPrefix() + getName();
    }

    public final String getDescription() {
        return description;
    }

    public final String getSyntax(String alias) {
        if (Arrays.asList(getAliases()).contains(alias)) return alias + " " + syntax;
        else return getName() + " " + syntax;
    }

    public final String getFullSyntax(String alias) {
        return CommandManager.getPrefix() + getSyntax(alias);
    }

    public final String[] getAliases() {
        return aliases;
    }

    public static Command of(String string) {
        if (CommandManager.aliasExists(string)) for (Command c : CommandManager.getCommandList())
            if (c.getAliases().length != 0)
                for (String s : c.getAliases()) if (s != null && s.equalsIgnoreCase(string)) return c;
        CommandManager.sendUnknownCommand(string);
        return null;
    }

    public void sendInvalidSyntax(String alias) {
        if (Arrays.asList(getAliases()).contains(alias)) ChatUtils.printError("Invalid syntax. Correct syntax is '" + getFullSyntax(alias) + "");
        else ChatUtils.printError("Invalid syntax. Correct syntax is '" + getFullSyntax("") + "");
    }

    public void sendUnknownModule(String m) {
        ChatUtils.printError("Module '" + m + "' found", Command.ERRORID);
    }

    public abstract void exec(String[] args, String alias);
}