package io.mazenmc.notifier.events;

import io.mazenmc.notifier.client.NotifierClient;
import io.mazenmc.notifier.event.NotifierEvent;

/**
 * Event which will be called when a client has logged in
 */
public class ClientLoginEvent extends NotifierEvent{

    private NotifierClient client;

    public ClientLoginEvent(NotifierClient client) {
        this.client = client;
    }

    public NotifierClient getClient() {
        return client;
    }
}
