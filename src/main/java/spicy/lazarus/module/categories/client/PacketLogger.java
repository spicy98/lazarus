package spicy.lazarus.module.categories.client;

import spicy.lazarus.event.packets.PacketEvent;
import meteordevelopment.orbit.EventHandler;
import spicy.lazarus.module.Module;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.beans.Introspector;
import java.util.ArrayList;

public class PacketLogger extends Module {
    public PacketLogger() {
        super("PacketLogger", "Logs all incoming packet traffic to console.");
    }

    @EventHandler
    private void onPacketReceive(PacketEvent.Receive event) throws IntrospectionException {
        boolean incoming = false;
        ArrayList<String> output = new ArrayList<>();

        for(PropertyDescriptor propertyDescriptor : Introspector.getBeanInfo(event.packet.getClass()).getPropertyDescriptors()) {
            String part;
            try {
                part = "| " + propertyDescriptor.getReadMethod().toString() + " --> " + propertyDescriptor.getReadMethod().invoke(event.packet);
                incoming = part.contains("s2c");
                //Fix this shit later
                part = part.replaceAll("net.minecraft.network.packet.s2c.play.", "").replaceAll("net.minecraft.network.packet.c2s.play.", "");
                part = part.replaceAll("public", "").replaceAll("final", "");
            } catch (Exception e) {
                return;
            }

            output.add(part);
        }

        System.out.printf("\n----------%s----------\n", (incoming) ? "INCOMING" : "OUTGOING");
        System.out.print("| " + event.packet.toString() + "\n");
        for(String string : output) {
            System.out.print(string + "\n");
        }
        System.out.printf("----------------------------\n");
    }
}
