package spicy.lazarus.event.client;

import net.minecraft.client.util.InputUtil;
import spicy.lazarus.event.Event;

public class KeyEvent extends Event {
    private static final KeyEvent INSTANCE = new KeyEvent();

    public InputUtil.Key key;

    public static KeyEvent getInstance(InputUtil.Key key) {
        INSTANCE.key = key;
        return INSTANCE;
    }
}
