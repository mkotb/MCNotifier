package io.mazenmc.notifier.client;

import io.mazenmc.notifier.NotifierPlugin;
import io.mazenmc.notifier.events.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import static io.mazenmc.notifier.NotifierPlugin.*;

public class NotifierClient {

    private static  List<NotifierClient> clients = new ArrayList<>();
    private Socket socket;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;
    private String username;
    private NotifierClientThread clientThread;

    public NotifierClient(Socket socket, String username) throws IOException {
        this.socket = socket;
        outputStream = new ObjectOutputStream(socket.getOutputStream());
        inputStream = new ObjectInputStream(socket.getInputStream());
        this.username = username;
        clientThread = new NotifierClientThread();
        login();
    }

    public ObjectInputStream getInputStream() {
        return inputStream;
    }

    public Socket getSocket() {
        return socket;
    }

    public ObjectOutputStream getOutputStream() {
        return outputStream;
    }

    public String getUsername() {
        return username;
    }

    public void write(Object obj) {
        try{
            outputStream.writeObject(obj);
        }catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    public void write(String str) {
        try{
            outputStream.writeChars(str);
        }catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    public void write(byte[] bytes) {
        try{
            outputStream.write(bytes);
        }catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    public void logout() {
        write("/logout");
    }

    public void login() {
        clients.add(this);
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

    class NotifierClientThread extends Thread {

        @Override
        public void run() {
            while(true) {
                Object obj;

                try{
                    obj = getInputStream().readObject();
                }catch(IOException ex) {
                    ex.printStackTrace();
                    continue;
                }catch(ClassNotFoundException ex) {
                    NotifierPlugin.getPlugin().getLogger().log(Level.SEVERE, "Client sent invalid Object type");
                    continue;
                }

                if(obj instanceof String) {
                    getEventHandler().callEvent(new StringReceiveEvent((String) obj, copy()));
                }else if(obj instanceof Boolean) {
                    getEventHandler().callEvent(new BooleanReceiveEvent((Boolean) obj, copy()));
                }else if(obj instanceof Number) {
                    getEventHandler().callEvent(new NumberReceiveEvent((Number) obj, copy()));
                }else if(obj instanceof Character) {
                    getEventHandler().callEvent(new CharacterReceiveEvent((Character) obj, copy()));
                }else{
                    getEventHandler().callEvent(new ObjectReceiveEvent(obj, copy()));
                }
            }
        }
    }
}
