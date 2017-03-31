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

    //存放 Object  以及   List<EventType>
    private final Map<Object, List<Class<?>>> typesBySubscriber;

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
        typesBySubscriber = new HashMap<>();
    }

    public void register(Object subscriber) {
//
        List<SubscriberMethodWenld> subscriberMethods = cacheMap.get(subscriber);
        synchronized (this) {
           if(subscriberMethods==null){
               subscriber(subscriber);
           }
        }
    }



    public void unRegister(Object subscriber) {

    }
    public void post(Object obj) {

    }

    private void subscriber(Object subscriber) {

    }
}
