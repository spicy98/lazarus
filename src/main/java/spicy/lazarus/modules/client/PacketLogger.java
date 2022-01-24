package spicy.lazarus.modules.client;

import meteordevelopment.orbit.EventHandler;
import spicy.lazarus.Lazarus;
import spicy.lazarus.events.packets.PacketEvent;
import spicy.lazarus.modules.Module;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

public class PacketLogger extends Module {
    public PacketLogger() {
        super("PacketLogger", "Logs all incoming packet traffic to console.");
    }

    @Override
    public void onEnable() {
        mc.player.sendChatMessage("LOGGING PACKETS");
    }

    @EventHandler
    private void onPacketReceive(PacketEvent.Receive event) throws IntrospectionException {
        System.out.println("TICKING");
        for(PropertyDescriptor propertyDescriptor : Introspector.getBeanInfo(event.packet.getClass()).getPropertyDescriptors()) {
            String output;
            try {
                output = propertyDescriptor.getReadMethod().invoke(event.packet) + ": " + propertyDescriptor.getReadMethod().toString();
            } catch (Exception e) {
                return;
            }

            System.out.println(output);
        }
    }
}
