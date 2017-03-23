package com.wenld.flea.util.eventbus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * <p/>
 * Author: 温利东 on 2017/3/23 16:41.
 * blog: http://blog.csdn.net/sinat_15877283
 * github: https://github.com/LidongWen
 */

public class EventBusWenld {
    private static EventBusWenld ourInstance;
    private Map<Class<?>, CopyOnWriteArrayList<SubscriberMethodWenld>> cacheMap;

    public static EventBusWenld getDefault() {
        if (ourInstance == null) {
            synchronized (EventBusWenld.class) {
                if (ourInstance == null) {
                    ourInstance = new EventBusWenld();
                }
            }
        }
        return ourInstance;
    }

    private EventBusWenld() {
        cacheMap = new HashMap<>();
    }

    public void register(Object subscriber) {
        Class<?> subscriberClass = subscriber.getClass();
        List<SubscriberMethodWenld> subscriberMethods = cacheMap.get(subscriberClass);
        synchronized (this) {
           if(subscriberMethods==null){
//               subscribe(subscriber,);
           }
        }
    }
    public void post(Object obj) {

    }
}
