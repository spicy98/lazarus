package spicy.lazarus.modules;

import org.reflections.Reflections;
import spicy.lazarus.Lazarus;
import spicy.lazarus.events.lazarus.ActiveModulesChangedEvent;

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

    void addActive(Module module) {
        synchronized (enabled) {
            if (!enabled.contains(module)) {
                enabled.add(module);
                Lazarus.EVENT_BUS.post(ActiveModulesChangedEvent.get());
            }
        }
    }

    void removeActive(Module module) {
        synchronized (enabled) {
            if (enabled.remove(module)) {
                Lazarus.EVENT_BUS.post(ActiveModulesChangedEvent.get());
            }
        }
    }

    private void addModules(String p) {
        try {
            for (Class<? extends Module> m : new Reflections("spicy.lazarus.modules." + p).getSubTypesOf(Module.class))
                modules.add(m.getDeclaredConstructor().newInstance());
        } catch (Exception ignored) {}
    }
}
