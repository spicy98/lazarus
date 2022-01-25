package spicy.lazarus.module;

import org.reflections.Reflections;

import java.util.ArrayList;

public class ModuleManager {
    private static final ArrayList<Module> modules = new ArrayList<>();

    public static void init() {
        addModules("client");
        addModules("world");
    }

    public static Module getModule(String name) {
        for(Module module : modules) {
            if(module.getName().equals(name))  {
                return module;
            }
        }

        return null;
    }

    public static ArrayList<Module> getModules() {
        return modules;
    }

    private static void addModules(String p) {
        try {
            for (Class<? extends Module> m : new Reflections("spicy.lazarus.module.categories." + p).getSubTypesOf(Module.class))
                modules.add(m.getDeclaredConstructor().newInstance());
        } catch (Exception ignored) {}
    }
}
