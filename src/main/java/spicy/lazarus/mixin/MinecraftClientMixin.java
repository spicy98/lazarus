package spicy.lazarus.mixin;

import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import spicy.lazarus.Lazarus;
import spicy.lazarus.events.world.TickEvent;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin {
    @Inject(method = "tick", at = @At("HEAD"))
    public void onPreTick(CallbackInfo info) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.world != null && mc.player != null) {
            TickEvent.PreTick preTick = new TickEvent.PreTick();
            Lazarus.EVENT_BUS.post(preTick);
        }
    }

    @Inject(method = "tick", at = @At("TAIL"))
    public void onPostTick(CallbackInfo info) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.world != null && mc.player != null) {
            TickEvent.PostTick postTick = new TickEvent.PostTick();
            Lazarus.EVENT_BUS.post(postTick);
        }
    }
}
