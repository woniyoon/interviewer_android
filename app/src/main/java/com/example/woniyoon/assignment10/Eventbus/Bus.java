package com.example.woniyoon.assignment10.Eventbus;

import org.greenrobot.eventbus.EventBus;

public class Bus {

    private static EventBus eventBus;

    public static EventBus getBus() {
        if (eventBus == null) {
            eventBus = EventBus.getDefault();
        }
        return eventBus;
    }

}