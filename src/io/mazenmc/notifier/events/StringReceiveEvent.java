package io.mazenmc.notifier.events;

import io.mazenmc.notifier.client.NotifierClient;

/**
 * Created by mazen on 6/16/14.
 */
public class StringReceiveEvent extends MessageReceiveEvent<String>{

    public StringReceiveEvent(String received, NotifierClient client) {
        super(received, client);
    }
}
