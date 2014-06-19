package io.mazenmc.notifier.events;

import io.mazenmc.notifier.client.NotifierClient;
import io.mazenmc.notifier.event.NotifierEvent;

/**
 * Event which would be called when a client has logged out
 */
public class ClientLogoutEvent extends NotifierEvent{

    private NotifierClient client;

    public ClientLogoutEvent(NotifierClient client) {
        this.client = client;
    }

    public NotifierClient getClient() {
        return client;
    }
}
