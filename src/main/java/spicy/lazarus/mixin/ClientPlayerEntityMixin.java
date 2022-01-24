package spicy.lazarus.mixin;


import com.mojang.authlib.GameProfile;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import spicy.lazarus.commands.Command;
import spicy.lazarus.commands.CommandManager;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin extends AbstractClientPlayerEntity {

    public ClientPlayerEntityMixin(ClientWorld world, GameProfile profile) {
        super(world, profile);
    }

    @Inject(at = @At("HEAD"), method = "sendChatMessage", cancellable = true)
    private void onSendChatMessage(String message, CallbackInfo info) {
        if (message.startsWith(CommandManager.getPrefix())) {
            info.cancel();
            String command = message.substring(CommandManager.getPrefix().length());
            if (CommandManager.commandExists(Command.of(command.split(" ")[0]))) {
                CommandManager.runCommand(command);
            }
        } else {
            //SendChatMsgEvent event = Neon.EVENT_BUS.post(SendChatMsgEvent.getInstance(message));

            //if (event.isCancelled()) info.cancel();
        }

    }
}
