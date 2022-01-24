package spicy.lazarus.commands;

import org.reflections.Reflections;
import spicy.lazarus.Lazarus;
import spicy.lazarus.utils.ChatUtils;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class CommandManager {
    private static String PREFIX = "$";

    public static String getPrefix() { return PREFIX; }

    public static void setPrefix(String prefix) { PREFIX = prefix; }

    private static final List<Command> commandList = new ArrayList<>();

    public static void init() { ;
        try {
            for (Class<? extends Command> c : new Reflections("spicy.lazarus.commands").getSubTypesOf(Command.class))
                commandList.add(c.getDeclaredConstructor().newInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static List<Command> getCommandList() {
        return commandList;
    }

    public static boolean commandExists(Command command) {
        return commandList.contains(command) && command != null;
    }

    public static boolean aliasExists(String alias) {
        for (Command c : commandList) {
            if (Arrays.asList(c.getAliases()).contains(alias)) return true;
        }
        return false;
    }

    public static void runCommand(String raw) {
        String dupe = raw;
        List<String> argsList = new ArrayList<>();
        int count = 0;
        int total = 0;
        while (true) {
            Matcher matcher = Pattern.compile("[^\\\\]\"").matcher(dupe);
            if (!matcher.find()) break;
            count++;
            int i = matcher.start();
            total += i + 1;
            dupe = dupe.substring(i + 1);
            dupe = dupe.trim();
        }
        if (count % 2 != 0) {
            Lazarus.LOGGER.error("Unterminated quotations found beginning at character " + (total + 1));
            return;
        }
        Matcher m = Pattern.compile("([^\"]\\S*|\".+?[^\\\\]\")\\s*").matcher(raw);
        while (m.find()) {
            String tmp = m.group(1).replaceAll("([^\\\\])\"", "$1").replaceAll("\\\\\"", "\"");
            argsList.add(tmp.startsWith("\"") ? tmp.substring(1) : tmp);
        }
        String[] args =  argsList.toArray(new String[0]);
        Command command = Command.of(args[0]);
        if (command == null) {
            CommandManager.sendUnknownCommand(args[0]);
            return;
        }
        if (commandExists(command)) {
            try {
                command.exec(Arrays.copyOfRange(args, 1, args.length), args[0]);
            } catch (Exception e) {
                Lazarus.LOGGER.error(e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public static void sendUnknownCommand(String string) {
        ChatUtils.printError("Invalid command '" + CommandManager.getPrefix() + string + "' Do " + CommandManager.getPrefix() + "help to see all commands.");
    }
}