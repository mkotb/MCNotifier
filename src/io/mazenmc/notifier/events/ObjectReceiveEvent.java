package io.mazenmc.notifier.events;

import io.mazenmc.notifier.client.NotifierClient;

public class ObjectReceiveEvent extends MessageReceiveEvent<Object>{

    public ObjectReceiveEvent(Object received, NotifierClient client) {
        super(received, client);
    }
}
