package io.mazenmc.notifier;

import io.mazenmc.notifier.client.NotifierClient;
import io.mazenmc.notifier.event.NotifierEventHandler;
import io.mazenmc.notifier.util.SettingsManager;

public class Notifier {
    public static NotifierEventHandler getEventHandler() {
        return NotifierPlugin.getEventHandler();
    }

    public static NotifierClient getClient(int id) {
        return NotifierClient.getClient(id);
    }

    public static NotifierClient getClient(String username) {
        return NotifierClient.getClient(username);
    }

    public static String[] generatePacketArgs(String... args) {
        return args;
    }

    public static String[] emptyPacketArgs() {
        return new String[] {};
    }

    public static SettingsManager getSettingsManager() {
        return NotifierPlugin.getSettingsManager();
    }
}
