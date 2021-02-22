package me.meloni.melonos.Modules.Listeners;

import me.meloni.melonos.MelonOS;

import java.util.ArrayList;
import java.util.List;

public class ListenerManager {
    private final MelonOS instance;
    public ListenerManager(MelonOS instance) {
        this.instance = instance;
    }

    public void registerEvents() {
        for (Listener listener : listeners()) {
            instance.getPluginManager().registerEvents(listener, instance);
        }
    }
    public List<Listener> listeners() {
        List<Listener> listeners = new ArrayList<>();

        listeners.add(new PlayerListener());
        listeners.add(new CommandListener());

        return listeners;
    }
}
