package io.mazenmc.notifier.packets;

public class PacketReader {

    private static final PacketReader instance = new PacketReader();

    /**
     * Get instance of PacketReader
     * @return instance of PacketReader
     */
    public static PacketReader getInstance() {
        return instance;
    }

    /**
     * Read the packet through string data provided by client
     * @param data The String data provided by the client
     * @return Found client
     * @throws java.lang.IllegalArgumentException If ID found in the data is not associated with a packet
     * @throws java.lang.NumberFormatException If ID placeholder contains something other than a number
     */
    public Packet readPacket(String[] data) {
        int id = Integer.parseInt(data[0]);

        String[] args = new String[data.length-1];

        for(int i = 1; i < data.length; i++) {
            args[i-1] = data[i];
        }

        try{
            return Packet.getPacket(id).getConstructor(String[].class).newInstance(args);
        }catch(Exception ex) {
            throw new IllegalArgumentException("ID " + id + " is not associated with a packet!");
        }
    }
}
