package io.mazenmc.notifier.events;

import io.mazenmc.notifier.client.NotifierClient;

/**
 * Created by mazen on 6/16/14.
 */
public class CharacterReceiveEvent extends MessageReceiveEvent<Character>{

    public CharacterReceiveEvent(Character received, NotifierClient client) {
        super(received, client);
    }
}
