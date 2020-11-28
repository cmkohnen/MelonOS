import Listeners.Listener;
import Listeners.PlayerListener;
import org.bukkit.plugin.PluginManager;

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

        return listeners;
    }
}
