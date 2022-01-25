package spicy.lazarus.util.client;

import net.minecraft.client.MinecraftClient;
import com.google.gson.*;
import spicy.lazarus.Lazarus;
import spicy.lazarus.command.CommandManager;
import spicy.lazarus.module.Module;
import spicy.lazarus.module.ModuleManager;
import spicy.lazarus.module.setting.Setting;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;


public class ConfigUtil {
    public static String fileSeparator = System.getProperty("file.separator");
    public static final File configDir = new File(MinecraftClient.getInstance().runDirectory, "lazarus");
    private static FileWriter writer;

    public static void setupConfig() {
        if (!configDir.exists()){
            createConfigDirectory(); //checked
        }
        //else if (!configDir.isDirectory()){
        //not sure what this does lmao
        //if (configDir.delete()) createConfigDirectory(); //checked
        //else Neon.LOGGER.error("Failed to delete file blocking config directory from being created!");
        //}
    }

    private static void createConfigDirectory() {
        try {
            Files.createDirectories(configDir.toPath());
            Lazarus.LOGGER.info(configDir.toString());
            Files.createDirectories(new File(configDir + fileSeparator + "default").toPath());
        } catch (IOException e) {
            Lazarus.LOGGER.error("Could not create configuration directory!");
            e.printStackTrace();
        }
    }

    public static void loadConfig() throws FileNotFoundException {
        loadConfig("default");
    }

    public static void saveConfig() {
        saveConfig("default");
    }

    public static void saveConfig(String name) {
        saveModuleConfig(name);
        //saveFriendConfig(name);
        saveSystemConfig();
    }

    public static void loadConfig(String name) throws FileNotFoundException {
        loadModuleConfig(name);
        //loadFriendConfig(name);
        loadSystemConfig();
    }

    private static void saveSystemConfig() {
        JsonObject obj = new JsonObject();
        if (!CommandManager.getPrefix().equals(".")) obj.addProperty("prefix", CommandManager.getPrefix());

        writeObjToFile("system", obj);
    }

    public static void loadSystemConfig() throws FileNotFoundException {
        JsonObject jsonObject = readJson("system");

        if (jsonObject.get("prefix") != null) CommandManager.setPrefix(jsonObject.get("prefix").getAsString());
    }

    public static void saveModuleConfig(String name) {
        JsonObject obj = new JsonObject();
        JsonObject modules = new JsonObject();
        for (Module m : ModuleManager.getModules()) {
            JsonObject module = new JsonObject();
            for (Setting s : m.getSettings()) {
                //removed this cause it breaks things and doesnt really do much
                //if (!s.getStringValue().equals(s.getStringDefaultValue()) && m.getSettings().contains(s)) {
                module.addProperty(s.getName(), s.getStringValue());
                //}
            }
            if (m.getBind() != null) module.addProperty("bind", m.getBind());
            //if (!m.getDrawn()) module.addProperty("drawn", false);
            if (m.getEnabled()) module.addProperty("enabled", true);
            if (!module.entrySet().isEmpty()) modules.add(m.getName(), module);
        }
        if (!modules.entrySet().isEmpty()) obj.add("modules", modules);

        writeObjToFile(name + fileSeparator + "modules", obj);
    }

    private static void loadModuleConfig(String name) {
        try {
            JsonObject jsonObject = readJson(name + fileSeparator + "modules");
            for (Module m : ModuleManager.getModules()) {
                if (jsonObject.get("modules") != null) {
                    JsonObject a = (JsonObject) jsonObject.get("modules");
                    JsonObject b = (JsonObject) a.get(m.getName());
                    if (a.has(m.getName())) {
                        for (Setting s : m.getSettings()) {
                            if (b.has(s.getName())) {
                                try {
                                    s.setStringValue(b.get(s.getName()).getAsString());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        if (b.get("bind") != null) m.setBind(b.get("bind").getAsString());
                        //if (b.get("drawn") != null) m.setDrawn(b.get("drawn").getAsBoolean());
                        if (b.get("enabled") != null) m.setEnabled(b.get("enabled").getAsBoolean());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    public static void saveFriendConfig(String name) {
        JsonObject obj = new JsonObject();
        JsonArray arr = new JsonArray();
        for (String s : FriendManager.getFriends())
            arr.add(s);
        obj.add("friends", arr);

        writeObjToFile(name + fileSeparator +"friends", obj);
    }
    */

    /*
    public static void loadFriendConfig(String name) {
        try {
            JsonArray arr = (JsonArray) readJson(name + fileSeparator + "friends").get("friends");
            for (JsonElement s : arr) {
                FriendManager.addFriend(s.getAsString());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
     */

    private static void writeObjToFile(String file, JsonElement obj) {
        try {
            writer = new FileWriter(configDir + fileSeparator + file + ".json");
            Gson builder = new GsonBuilder().setPrettyPrinting().create();
            writer.write(builder.toJson(obj));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static JsonObject readJson(String file) throws FileNotFoundException {
        JsonParser parser = new JsonParser();
        return (JsonObject) parser.parse(new FileReader(configDir + fileSeparator + file + ".json"));
    }

    public static List<String> getConfigs() {
        List<String> configs = new ArrayList<>();
        Collections.addAll(configs, Objects.requireNonNull(configDir.list((current, name) -> new File(current, name).isDirectory())));
        return configs;
    }
}
