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

package io.mazenmc.notifier;

import io.mazenmc.notifier.client.NotifierClient;
import io.mazenmc.notifier.event.NotifierEventHandler;
import io.mazenmc.notifier.util.SettingsManager;

public class Notifier {

    /**
     * Gets EventHandlers instance from NotifierPlugin
     * @return EventHandler instance
     */
    public static NotifierEventHandler getEventHandler() {
        return NotifierPlugin.getEventHandler();
    }

    /**
     * Gets a client by their ID
     * @param id The ID to get the client by
     * @return Found client
     */
    public static NotifierClient getClient(int id) {
        return NotifierClient.getClient(id);
    }

    /**
     * Get the client by their username
     * @param username The String that the user goes by
     * @return Found client
     */
    public static NotifierClient getClient(String username) {
        return NotifierClient.getClient(username);
    }

    /**
     * Allows for a more code-clean method of creating packet arguments
     * @param args The arguments you wish to create
     * @return Generated packet arguments
     */
    public static String[] generatePacketArgs(String... args) {
        return args;
    }

    /**
     * Generates an empty set of packet arguments
     * @return Packet arguments
     */
    public static String[] emptyPacketArgs() {
        return new String[] {};
    }

    /**
     * Build a string
     * @return Built string
     */
    public static String buildString(Object... objects) {
        StringBuilder sb = new StringBuilder("");

        for(Object obj : objects) {
            sb.append(obj);
        }

        return sb.toString();
    }

    /**
     * Gets SettingsManager instance from NotifierPlugin
     * @return SettingManagers instance
     */
    public static SettingsManager getSettingsManager() {
        return NotifierPlugin.getSettingsManager();
    }
}
