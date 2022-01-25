package spicy.lazarus.module;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.InputUtil;
import spicy.lazarus.Lazarus;
import spicy.lazarus.module.setting.Setting;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class Module {
    protected final MinecraftClient mc = MinecraftClient.getInstance();

    private final String name, description;
    private boolean enabled;
    private String bind = null;

    public Module(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        if(this.enabled != enabled && !this.enabled) Lazarus.EVENT_BUS.subscribe(this);
        if(this.enabled != enabled && this.enabled) Lazarus.EVENT_BUS.unsubscribe(this);
        this.enabled = enabled;
    }

    public String getBind() {
        return this.bind;
    }

    public void setBind(String bind) {
        this.bind = bind;
    }

    public final ArrayList<Setting> getSettings() {
        return getSettings(Setting.class);
    }

    @SuppressWarnings("unchecked")
    public final <T extends Setting> ArrayList<T> getSettings(Class<T> klass) {
        ArrayList<T> settings = new ArrayList<>();
        Field[] fields = this.getClass().getDeclaredFields();
        try {
            for (Field f : fields) {
                settings.add((T) f.get(this));
            }
        } catch (IllegalAccessException ignored) {}
        return settings;
    }

    public void toggle() {
        if (enabled) {
            enabled = false;
            Lazarus.EVENT_BUS.unsubscribe(this);
        } else {
            enabled = true;
            Lazarus.EVENT_BUS.subscribe(this);
        }
    }

    public void onEnable() {}

    public void onDisable() {}
}
