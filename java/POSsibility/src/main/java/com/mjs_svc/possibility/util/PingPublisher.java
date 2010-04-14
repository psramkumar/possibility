package com.mjs_svc.possibility.util;

import java.util.*;

/**
 *
 * @author Matthew Scott
 * @version $Id;
 */
public class PingPublisher {
    private static Vector<PingSubscriber> subscribers =
            new Vector<PingSubscriber>();

    public static void ping() {
        for (PingSubscriber subscriber : subscribers) {
            subscriber.onPing();
        }
    }

    public static void add(PingSubscriber s) {
        subscribers.add(s);
    }

    public static void remove(PingSubscriber s) {
        subscribers.remove(s);
    }
}
