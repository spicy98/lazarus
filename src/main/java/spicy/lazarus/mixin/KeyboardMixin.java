package spicy.lazarus.mixin;

import net.minecraft.client.Keyboard;
import net.minecraft.client.util.InputUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import spicy.lazarus.Lazarus;
import spicy.lazarus.events.client.KeyEvent;

@Mixin(Keyboard.class)
public abstract class KeyboardMixin {
    @Inject(method = "onKey", at = @At("HEAD"), cancellable = true)
    public void onKeyPress(long window, int key, int scancode, int i, int j, CallbackInfo info) {
        KeyEvent event = Lazarus.EVENT_BUS.post(KeyEvent.getInstance(InputUtil.fromKeyCode(key, scancode)));
    }
}