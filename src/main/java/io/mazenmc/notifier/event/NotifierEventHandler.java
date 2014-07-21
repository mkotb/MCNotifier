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

package io.mazenmc.notifier.event;

import io.mazenmc.notifier.NotifierPlugin;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;

public class NotifierEventHandler {

    private HashMap<Class<? extends NotifierEvent>, List<NotifierListener>> eventData;

    public NotifierEventHandler() {
        eventData = new HashMap<>();
    }

    /**
     * Calls the event mentioned
     * @param event The event you wish to call
     */
    public void callEvent(NotifierEvent event) {
        if(!eventData.containsKey(event.getClass())) {
            eventData.put(event.getClass(), new ArrayList<NotifierListener>());
        }

        List<NotifierListener> list = eventData.get(event.getClass());

        for(int i = 0; i < list.size(); i++) {
            NotifierListener listener = list.get(i);

            for(Method method : listener.getClass().getMethods()) {
                if(method.getParameterTypes().length == 1 && method.getParameterTypes()[0].getName().equals(event.getClass().getName()) && method.isAnnotationPresent(NotifierMethod.class)) {
                    try{
                        method.invoke(listener, event);
                    }catch(IllegalAccessException | InvocationTargetException e) {
                        String methodName = method.getName();
                        String listenerClassName = listener.getClass().getName();
                        String eventName = event.getClass().getName();

                        NotifierPlugin.getPlugin().getLogger().log(Level.SEVERE, "Unable to invoke method " + methodName + " in " + listenerClassName + " while calling event " + eventName + ". Caused by - " + e.getClass().getName(), e);
                    }
                }
            }
        }
    }

    /**
     * Registers a listener to be called when an event is
     * @param listener The listener you wish to register
     */
    public void registerListener(NotifierListener listener) {
        List<Class<? extends NotifierEvent>> listeningEvents = new ArrayList<>();

        for(Method method : listener.getClass().getMethods()) {
            if(method.getParameterTypes().length == 1 && NotifierEvent.class.isAssignableFrom(method.getParameterTypes()[0]) && method.isAnnotationPresent(NotifierMethod.class)) {
                listeningEvents.add(method.getParameterTypes()[0].asSubclass(NotifierEvent.class));
            }
        }

        for(Class<? extends NotifierEvent> clzz : listeningEvents) {
            if(!eventData.containsKey(clzz)) {
                List<NotifierListener> lnl = new ArrayList<>();
                lnl.add(listener);
                eventData.put(clzz, lnl);

                continue;
            }

            eventData.get(clzz).add(listener);
        }
    }
}
