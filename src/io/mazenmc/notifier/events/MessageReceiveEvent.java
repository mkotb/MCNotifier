package io.mazenmc.notifier.events;

import io.mazenmc.notifier.client.NotifierClient;
import io.mazenmc.notifier.event.NotifierEvent;

@Deprecated
public class MessageReceiveEvent<T> extends NotifierEvent{

    private T messageRecieved;
    private NotifierClient client;

    public MessageReceiveEvent(T received, NotifierClient client) {
        messageRecieved = received;
        this.client = client;
    }

    public T get() {
        return messageRecieved;
    }

    public NotifierClient getClient() {
        return client;
    }
}
