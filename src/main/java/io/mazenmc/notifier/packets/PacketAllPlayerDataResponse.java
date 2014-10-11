package io.mazenmc.notifier.packets;

import io.mazenmc.notifier.Notifier;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class PacketAllPlayerDataResponse extends Packet {

    private static final int ID = 21;

    private List<PacketPlayerDataResponse> responses = new ArrayList<>();

    public PacketAllPlayerDataResponse(String[] args) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            responses.add(new PacketPlayerDataResponse(Notifier.generatePacketArgs(p.getName())));
        }
    }

    public static int getID() {
        return ID;
    }

    @Override
    public void handle() {
        //No handling..
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(ID);
        sb.append(SPLITTER);

        for (PacketPlayerDataResponse response : responses) {
            sb.append(response.toString().substring(5).replaceAll(SPLITTER, "~@~"));
            sb.append(SPLITTER);
        }

        return sb.toString();
    }
}
