package io.mazenmc.notifier.client;

import io.mazenmc.notifier.NotifierPlugin;
import io.mazenmc.notifier.events.*;
import io.mazenmc.notifier.packets.Packet;
import io.mazenmc.notifier.packets.PacketEncryptKey;
import io.mazenmc.notifier.packets.PacketReceiveError;

import java.io.*;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static io.mazenmc.notifier.NotifierPlugin.*;
import static io.mazenmc.notifier.util.Encrypter.*;

public class NotifierClient {

    private static  List<NotifierClient> clients = new ArrayList<>();
    private Socket socket;
    private DataOutputStream outputStream;
    private DataInputStream inputStream;
    private String username;
    private NotifierClientThread clientThread;
    private UUID encryptionKey;

    public NotifierClient(Socket socket, DataInputStream inputStream, DataOutputStream outputStream, String username) throws IOException {
        this.socket = socket;
        this.outputStream = outputStream;
        this.inputStream = inputStream;
        this.username = username;
        encryptionKey = UUID.randomUUID();
        clientThread = new NotifierClientThread();
        login();
    }

    public DataInputStream getInputStream() {
        return inputStream;
    }

    public Socket getSocket() {
        return socket;
    }

    public DataOutputStream getOutputStream() {
        return outputStream;
    }

    public String getUsername() {
        return username;
    }

    public void write(Packet packet) {
        try{
            getEventHandler().callEvent(new PacketSendEvent(packet));
            outputStream.write(encrypt(packet.toString(), encryptionKey));
            flush();
        }catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    @Deprecated
    public void writeNoEncryption(Packet packet) {
        try{
            getEventHandler().callEvent(new PacketSendEvent(packet));
            outputStream.writeUTF(packet.toString());
            flush();
        }catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    public void flush() {
        try{
            outputStream.flush();
        }catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    public int getID() {
        return clients.indexOf(this);
    }

    public void login() {
        clients.add(this);
        writeNoEncryption(new PacketEncryptKey(encryptionKey));
        clientThread.start();
        NotifierPlugin.getEventHandler().callEvent(new ClientLoginEvent(this));
    }

    public NotifierClient copy() {
        return this;
    }

    public void performCommand(String command) {
        //TODO: Finish this method
    }

    public static NotifierClient getClient(String username) {
        for(NotifierClient client : getClients()) {
            if(client.getUsername().equals(username))
                return client;
        }

        return null;
    }

    public static List<NotifierClient> getClients() {
        return clients;
    }

    public static NotifierClient getClient(int id) {
        return getClients().get(id);
    }

    class NotifierClientThread extends Thread {

        @Override
        public void run() {
            while(true) {
                String rtn;

                try{
                    byte[] bytes = getInputStream().readUTF().getBytes();

                    rtn = decrypt(bytes, encryptionKey);
                }catch(SocketTimeoutException ex) {
                    clients.remove(copy());
                    //TODO: Send logout packet
                    break;
                }catch(EOFException ex) {
                    clients.remove(copy());
                    //TODO: Send logout packet
                    break;
                }catch(Exception ex) {
                    write(new PacketReceiveError(new String[] {ex.getMessage()}));
                    ex.printStackTrace();
                    continue;
                }

                getEventHandler().callEvent(new PacketReceiveEvent(rtn.split(" "), copy()));
            }
        }
    }
}
