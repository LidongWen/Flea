package com.wenld.flea.util.eventbus;

import org.greenrobot.eventbus.SubscriberMethod;
import org.reactivestreams.Subscription;

/**
 * <p/>
 * Author: 温利东 on 2017/3/24 14:10.
 * blog: http://blog.csdn.net/sinat_15877283
 * github: https://github.com/LidongWen
 */

public class SubscriptionWenld {
    final Object subscriber;        // Object
    final SubscriberMethodWenld subscriberMethod;       //方法
    /**
     * Becomes false as soon as {@link EventBus#unregister(Object)} is called, which is checked by queued event delivery
     * {@link EventBus#invokeSubscriber(PendingPost)} to prevent race conditions.
     */
    volatile boolean active;

    SubscriptionWenld(Object subscriber, SubscriberMethodWenld subscriberMethod) {
        this.subscriber = subscriber;
        this.subscriberMethod = subscriberMethod;
        active = true;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Subscription) {
            SubscriptionWenld otherSubscription = (SubscriptionWenld) other;
            return subscriber == otherSubscription.subscriber
                    && subscriberMethod.equals(otherSubscription.subscriberMethod);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return subscriber.hashCode() + subscriberMethod.methodString.hashCode();
    }
}
