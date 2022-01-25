package spicy.lazarus.modules;

import org.reflections.Reflections;
import spicy.lazarus.Lazarus;

import java.util.ArrayList;

public class ModuleManager {
    private final ArrayList<Module> modules = new ArrayList<>();
    private final ArrayList<Module> enabled = new ArrayList<>();

    public void init() {
        addModules("client");
        addModules("world");
    }

    public Module getModule(String name) {
        for(Module module : modules) {
            if(module.getName().equals(name))  {
                return module;
            }
        }

        Lazarus.LOGGER.error("Could not find the module: " + name);
        return null;
    }

    private void addModules(String p) {
        try {
            for (Class<? extends Module> m : new Reflections("spicy.lazarus.modules." + p).getSubTypesOf(Module.class))
                modules.add(m.getDeclaredConstructor().newInstance());
        } catch (Exception ignored) {}
    }
}
