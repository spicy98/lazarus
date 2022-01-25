package spicy.lazarus.modules;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.InputUtil;
import spicy.lazarus.Lazarus;

public class Module {
    protected final MinecraftClient mc = MinecraftClient.getInstance();

    private final String name, description;
    private boolean enabled;
    private InputUtil.Key bind = null;

    public Module(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void onEnable() {}

    public void onDisable() {}

    public String getName() {
        return this.name;
    }

    public void setBind(InputUtil.Key key) {
        this.bind = key;
    }

    public InputUtil.Key getBind() {
        return this.bind;
    }

    public boolean getEnabled() {
        return this.enabled;
    }

    public void toggle() {
        if (enabled) {
            enabled = false;
            Lazarus.LOGGER.info(this.name + " DISABLED!");
            Lazarus.EVENT_BUS.unsubscribe(this);
        } else {
            enabled = true;
            Lazarus.LOGGER.info(this.name + " ENABLED!");
            Lazarus.EVENT_BUS.subscribe(this);
        }
    }

    public void set(boolean enabled) {
        this.enabled = enabled;
    }
}
