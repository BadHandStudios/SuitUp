package com.badhand.suitup.events;

import java.util.*;

public class EventManager {
    private LinkedList<Event> eventQueue = LinkedList<Event>();


    private static EventManager instance = null;

    private EventManager(){}; // Singleton


    public static EventManager getInstance(){
        if(instance == null) instance = new EventManager();
        return instance;
    }

    public Event pop() {
        return eventQueue.pop();
    }
    public void push(Event e) {
        eventQueue.push(e);
    }
}
