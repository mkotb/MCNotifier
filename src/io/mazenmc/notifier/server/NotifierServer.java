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
import java.net.SocketTimeoutException;
import java.util.Arrays;
import java.util.logging.Level;

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

                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

                //Run auth. process
                String[] d = ois.readUTF().split(":");

                String username = d[0];
                char[] password = d[1].toCharArray();

                if(!NotifierPlugin.getSettingsManager().getUserData().containsKey(username)) {
                    oos.writeObject(new PacketLoginError("Username/password is incorrect!"));
                    NotifierPlugin.getPlugin().getLogger().info(username + " failed to log in, incorrect details");
                    continue;
                }

                if(!Arrays.equals(NotifierPlugin.getSettingsManager().getUserData().get(username), password)) {
                    oos.writeObject(new PacketLoginError("Username/password is incorrect!"));
                    NotifierPlugin.getPlugin().getLogger().info(username + " failed to log in, incorrect details");
                    continue;
                }

                new NotifierClient(socket, username);
                oos.writeObject(new PacketLoginSuccess());
                NotifierPlugin.getPlugin().getLogger().info(username + " has logged in!");
            }catch(SocketTimeoutException ex) {
                try{
                    server.close();

                    server = new ServerSocket(5932);
                    server.setSoTimeout(Integer.MAX_VALUE);
                }catch(Exception e) {e.printStackTrace();}

                ex.printStackTrace();
            }catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
