package io.mazenmc.notifier.events;

import io.mazenmc.notifier.client.NotifierClient;

/**
 * Created by mazen on 6/16/14.
 */
public class NumberReceiveEvent extends MessageReceiveEvent<Number>{

    public NumberReceiveEvent(Number received, NotifierClient client) {
        super(received, client);
    }
}
