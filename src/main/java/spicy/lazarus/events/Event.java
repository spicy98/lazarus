package spicy.lazarus.events;

import meteordevelopment.orbit.ICancellable;

public class Event implements ICancellable {
    private boolean cancelled = false;

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }
}
