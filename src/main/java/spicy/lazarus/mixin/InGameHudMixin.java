package spicy.lazarus.mixin;

import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import spicy.lazarus.Lazarus;
import spicy.lazarus.event.client.Render2DEvent;

@Mixin(InGameHud.class)
public class InGameHudMixin {
    @Inject(method = "render", at = @At("RETURN"))
    public void render(MatrixStack matrixStack, float partial, CallbackInfo info) {
        Render2DEvent event = Lazarus.EVENT_BUS.post(Render2DEvent.getInstance(matrixStack));
    }
}
