package com.badhand.suitup.events;

public class Event {
    private Events type;
    private Object data;

    public Event(Events type,Object data) {
        this.type = type;
        this.data = data;
    }

    public Events getType() {
        return this.type;
    }
    public Object getData() {
        return this.data;
    }
}
