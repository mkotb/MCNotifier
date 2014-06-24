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

package io.mazenmc.notifier.listeners.bukkit;

import io.mazenmc.notifier.Notifier;
import io.mazenmc.notifier.client.NotifierClient;
import io.mazenmc.notifier.packets.Packet;
import io.mazenmc.notifier.packets.PacketPlayerSwear;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

import java.util.Arrays;
import java.util.List;

public class SwearWarning implements Listener{

    private static final List<String> swearWords = Arrays.asList("tit", "fuck", "cunt", "shit", "bitch", "pussy", "dick", "penis", "vagina", "cum", "motherfucker", "anus", "ass", "axwound", "bastard", "beaner", "blowjob",
            "boner", "bullshit", "carpetmuncher", "chesticle", "chink", "clit", "cock", "coon", "cooter", "dipshit", "douche", "dildo", "faggit", "faggot", "fuck", "ginger", "gooch", "honkey", "hoe", "heeb", "jackass", "jizz",
            "jerk off", "junglebunny", "kike", "kooch", "kunt", "lameass", "lardass", "mcfagget", "muff", "munging", "negro", "nigaboo", "nigga", "nigger", "niggers", "niglet", "nutsack", "paki", "panooch", "packer", "prick",
            "queef", "queer", "renob", "rimjob", "sandnigger", "schlong", "scrote", "shit", "skullfuck", "tard", "testicle", "thundercunt", "twatwaffle", "unclefucker", "vag", "vjayjay", "vajayjay", "wank", "wankjob", "wetback",
            "whore", "whorebag", "whoreface", "wog");


    @EventHandler
    public void onChat(PlayerChatEvent event) {
        String message = event.getMessage();

        for(String s : message.split(" ")) {
            for(String swear : swearWords) {
                if(s.contains(swear)) {
                    PacketPlayerSwear packet = new PacketPlayerSwear(Notifier.generatePacketArgs(event.getPlayer().getName(), String.valueOf(Packet.SPLITTER), swear, String.valueOf(Packet.SPLITTER), message));

                    for(NotifierClient client : NotifierClient.getClients()) {
                        client.write(packet);
                    }
                }
            }
        }
    }
}
