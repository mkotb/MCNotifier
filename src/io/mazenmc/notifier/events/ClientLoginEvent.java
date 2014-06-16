package io.mazenmc.notifier.events;

import io.mazenmc.notifier.client.NotifierClient;
import io.mazenmc.notifier.event.NotifierEvent;

/**
 * Created by mazen on 6/16/14.
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
