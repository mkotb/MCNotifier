package io.mazenmc.notifier.util;

import java.util.HashMap;

public class SettingsManager {

    private final YMLFile config = new YMLFile("config", null);
    private final HashMap<String, char[]> userData = new HashMap<>();

    public SettingsManager() {
        for(String s : config.getConfig().getStringList("users")) {
            String[] data = s.split(":");

            userData.put(data[0], data[1].toCharArray());
        }
    }

    public HashMap<String, char[]> getUserData() {
        return userData;
    }

    public boolean isSuspiciousCommand(String command) {
        return config.getConfig().getStringList("suspicious-commands").contains(command);
    }
}
