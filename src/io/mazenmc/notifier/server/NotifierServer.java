package io.mazenmc.notifier.server;

import io.mazenmc.notifier.NotifierPlugin;
import io.mazenmc.notifier.client.NotifierClient;
import io.mazenmc.notifier.packets.PacketLoginError;
import io.mazenmc.notifier.packets.PacketLoginSuccess;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.logging.Level;

public class NotifierServer extends Thread{

    private ServerSocket server;

    public NotifierServer() throws IOException {
        server = new ServerSocket(5932);

        server.setSoTimeout(10000);
    }

    @Override
    public void run() {
        while(true) {
            Socket socket;
            try{
                socket = server.accept();

                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

                //Run auth. process
                String[] d = ((String) ois.readObject()).split(":");

                String username = d[0];
                char[] password = d[1].toCharArray();

                if(!NotifierPlugin.getSettingsManager().getUserData().containsKey(username)) {
                    oos.writeObject(new PacketLoginError("Username/password is incorrect!"));
                    continue;
                }

                if(!Arrays.equals(NotifierPlugin.getSettingsManager().getUserData().get(username), password)) {
                    oos.writeObject(new PacketLoginError("Username/password is incorrect!"));
                    continue;
                }

                new NotifierClient(socket, username);
                oos.writeObject(new PacketLoginSuccess());
            }catch(IOException ex) {
                ex.printStackTrace();
            }catch(ClassNotFoundException ex) {
                NotifierPlugin.getPlugin().getLogger().log(Level.SEVERE, "Client sent invalid Object type");
            }
        }
    }
}
