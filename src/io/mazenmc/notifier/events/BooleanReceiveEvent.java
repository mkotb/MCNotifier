package io.mazenmc.notifier.events;

import io.mazenmc.notifier.client.NotifierClient;

/**
 * Created by mazen on 6/16/14.
 */
public class BooleanReceiveEvent extends MessageReceiveEvent<Boolean>{

    public BooleanReceiveEvent(Boolean received, NotifierClient client) {
        super(received, client);
    }
}
