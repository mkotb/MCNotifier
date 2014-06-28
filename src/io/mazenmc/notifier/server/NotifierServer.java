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

package io.mazenmc.notifier.server;

import io.mazenmc.notifier.Notifier;
import io.mazenmc.notifier.NotifierPlugin;
import io.mazenmc.notifier.client.NotifierClient;
import io.mazenmc.notifier.packets.PacketLoginError;
import io.mazenmc.notifier.packets.PacketLoginSuccess;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Arrays;
import java.util.UUID;
import java.util.logging.Level;

import static io.mazenmc.notifier.util.Encrypter.*;

public class NotifierServer extends Thread{

    private ServerSocket server;

    public NotifierServer() throws IOException {
        server = new ServerSocket(5932);

        server.setSoTimeout(Integer.MAX_VALUE);

        NotifierPlugin.getPlugin().getLogger().info("Server on port 5932 has been started");
    }

    @Override
    public void run() {
        while(true) {
            Socket socket;
            try{
                socket = server.accept();

                DataOutputStream oos = new DataOutputStream(socket.getOutputStream());
                DataInputStream ois = new DataInputStream(socket.getInputStream());

                //Send init. encryption key
                UUID initKey = UUID.randomUUID();
                oos.writeUTF(initKey.toString());

                //Run auth. process
                byte[] r = {};
                ois.readFully(r);

                String[] d;

                try{
                    d = decrypt(r, initKey).split(":");
                }catch(Exception ex) {
                    try{
                        oos.write(encrypt(new PacketLoginError(Notifier.generatePacketArgs("Unable to decrypt login information!")).toString(), initKey));
                    }catch(Exception ignored) {}

                    continue;
                }

                String username = d[0];
                char[] password = d[1].toCharArray();

                if(!NotifierPlugin.getSettingsManager().getUserData().containsKey(username)) {
                    oos.write(encrypt(new PacketLoginError(Notifier.generatePacketArgs("Username/password is incorrect!")).toString(), initKey));
                    oos.flush();
                    continue;
                }

                if(!Arrays.equals(NotifierPlugin.getSettingsManager().getUserData().get(username), password)) {
                    oos.write(encrypt(new PacketLoginError(Notifier.generatePacketArgs("Username/password is incorrect!")).toString(), initKey));
                    oos.flush();
                    continue;
                }

                oos.write(encrypt(new PacketLoginSuccess(Notifier.emptyPacketArgs()).toString(), initKey));
                oos.flush();

                new NotifierClient(socket, ois, oos, username, initKey);

                NotifierPlugin.getPlugin().getLogger().info(username + " has logged in!");
            }catch(SocketTimeoutException ex) {
                try{
                    server.close();

                    server = new ServerSocket(5932);
                    server.setSoTimeout(Integer.MAX_VALUE);
                }catch(Exception e) {e.printStackTrace();}

                ex.printStackTrace();
            }catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
