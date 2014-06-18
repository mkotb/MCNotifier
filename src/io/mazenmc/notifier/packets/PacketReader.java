package io.mazenmc.notifier.packets;

public class PacketReader {

    private static final PacketReader instance = new PacketReader();

    public static PacketReader getInstance() {
        return instance;
    }

    public Packet readPacket(String[] data) {
        int id = Integer.parseInt(data[0]);

        String[] args = new String[data.length-1];

        for(int i = 1; i < data.length; i++) {
            args[i-1] = data[i];
        }

        try{
            return (Packet) Packet.getPacket(id).getConstructor(String[].class).newInstance(args);
        }catch(Exception ex) {
            return null;
        }
    }
}
