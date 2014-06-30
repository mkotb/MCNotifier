/*
* Copyright (C) 2014 Mazen K.
* This file is part of MCNotifier.
*
* MCNotifier for Bukkit is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, version 3 to be exact
*
* MCNotifier is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with MCNotifier. If not, see <http://www.gnu.org/licenses/>.
*/

package io.mazenmc.notifier.client;

import io.mazenmc.notifier.Notifier;
import io.mazenmc.notifier.NotifierPlugin;
import io.mazenmc.notifier.events.*;
import io.mazenmc.notifier.packets.Packet;
import io.mazenmc.notifier.packets.PacketEncryptKey;
import io.mazenmc.notifier.packets.PacketForceLogout;
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
    private UUID initKey;

    /**
     * Constructor for a new NotifierClient
     * @param socket The socket which will be stored for API use
     * @param inputStream The input stream that will be used to communicate with the client
     * @param outputStream The output stream that will be used to communicate with the client
     * @param username The username that has been defined in the config.yml
     *  @param initKey The key which has been generated at login
     * @throws IOException
     */
    public NotifierClient(Socket socket, DataInputStream inputStream, DataOutputStream outputStream, String username, UUID initKey) throws IOException {
        this.socket = socket;
        this.outputStream = outputStream;
        this.inputStream = inputStream;
        this.username = username;
        encryptionKey = UUID.randomUUID();
        clientThread = new NotifierClientThread();
        this.initKey = initKey;
        login();
    }

    /**
     * Gets the input stream
     * @return The input stream
     */
    public DataInputStream getInputStream() {
        return inputStream;
    }

    /**
     * Gets the socket
     * @return The socket
     */
    public Socket getSocket() {
        return socket;
    }

    /**
     * Gets the output stream
     * @return The output stream
     */
    public DataOutputStream getOutputStream() {
        return outputStream;
    }

    /**
     * Gets the username of the client
     * @return The client stream
     */
    public String getUsername() {
        return username;
    }

    /**
     * Writes a packet to the client
     * @param packet The packet you wish to write to the client
     */
    public void write(Packet packet) {
        try{
            getEventHandler().callEvent(new PacketSendEvent(packet));
            outputStream.writeUTF(encrypt(packet.toString(), encryptionKey));
            flush();
        }catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    private void writeInit(Packet packet) {
        try{
            getEventHandler().callEvent(new PacketSendEvent(packet));
            outputStream.writeUTF(encrypt(packet.toString(), initKey));
            flush();
        }catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Flush all buffered data in the input stream
     */
    public void flush() {
        try{
            outputStream.flush();
        }catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Gets the ID of the client
     * @return ID of the client
     */
    public int getID() {
        return clients.indexOf(this);
    }

    /**
     * Login the client if haven't already. I recommend not to use this.
     */
    public void login() {
        clients.add(this);
        writeInit(new PacketEncryptKey(Notifier.generatePacketArgs(encryptionKey.toString())));
        clientThread.start();
        NotifierPlugin.getEventHandler().callEvent(new ClientLoginEvent(this));
    }

    /**
     * Logout the client
     */
    public void logout() {
        clients.remove(this);
        clientThread.interrupt();

        NotifierPlugin.getEventHandler().callEvent(new ClientLogoutEvent(this));
    }

    /**
     * Gets a copy of the client
     * @return The client
     */
    public NotifierClient copy() {
        return this;
    }

    /**
     * Simulate a packet as-if the client has sent it
     * @param packet The packet you wish to simulate
     */
    public void simulatePacket(Packet packet) {
        getEventHandler().callEvent(new PacketReceiveEvent(packet.toString().split(" "), this));
    }

    /**
     * Finds the client instance by their username
     * @param username The username that has been defined in the config.yml
     * @return Found client
     */
    public static NotifierClient getClient(String username) {
        for(NotifierClient client : getClients()) {
            if(client.getUsername().equals(username))
                return client;
        }

        return null;
    }

    /**
     * Get all signed in clients
     * @return All signed in clients
     */
    public static List<NotifierClient> getClients() {
        return clients;
    }

    /**
     * Finds the client instance by their ID
     * @param id The ID you wish to use to find
     * @return Found client
     */
    public static NotifierClient getClient(int id) {
        return getClients().get(id);
    }

    class NotifierClientThread extends Thread {

        @Override
        public void run() {
            while(true) {
                String rtn;

                try{
                    rtn = decrypt(getInputStream().readUTF(), encryptionKey);
                }catch(SocketTimeoutException ex) {
                    logout();
                    write(new PacketForceLogout(ex.getMessage().split(" ")));
                    break;
                }catch(EOFException ex) {
                    logout();
                    write(new PacketForceLogout(ex.getMessage().split(" ")));
                    break;
                }catch(Exception ex) {
                    write(new PacketReceiveError(ex.getMessage().split(" ")));
                    ex.printStackTrace();
                    continue;
                }

                getEventHandler().callEvent(new PacketReceiveEvent(rtn.split(" "), copy()));
            }
        }
    }
}
