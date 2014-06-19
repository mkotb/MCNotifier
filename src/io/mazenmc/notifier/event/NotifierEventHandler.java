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

    public void callEvent(NotifierEvent event) {
        if(!eventData.containsKey(event.getClass())) {
            eventData.put(event.getClass(), new ArrayList<NotifierListener>());
        }

        List<NotifierListener> list = eventData.get(event.getClass());

        for(int i = 0; i < list.size(); i++) {
            NotifierListener listener = list.get(i);

            for(Method method : listener.getClass().getDeclaredMethods()) {
                if(method.getParameterTypes().length == 1 && method.getParameterTypes()[0] == event.getClass() && method.isAnnotationPresent(NotifierMethod.class)) {
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

    public void registerListener(NotifierListener listener) {
        List<Class<? extends NotifierEvent>> listeningEvents = new ArrayList<>();

        for(Method method : listener.getClass().getDeclaredMethods()) {
            if(method.getParameterTypes().length == 1 && method.getParameterTypes()[0].isAssignableFrom(NotifierEvent.class) && method.isAnnotationPresent(NotifierMethod.class)) {
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
