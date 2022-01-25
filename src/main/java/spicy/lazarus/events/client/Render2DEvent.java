package spicy.lazarus.events.client;

import net.minecraft.client.util.math.MatrixStack;
import spicy.lazarus.events.Event;

public class Render2DEvent extends Event {
    private static final Render2DEvent INSTANCE = new Render2DEvent();

    public MatrixStack matrixStack;

    public static Render2DEvent getInstance(MatrixStack matrixStack) {
        INSTANCE.matrixStack = matrixStack;
        return INSTANCE;
    }
}
