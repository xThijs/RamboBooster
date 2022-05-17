package nl.canify.rambobooster.listeners;

import com.gmail.nossr50.events.experience.McMMOPlayerPreXpGainEvent;
import nl.canify.rambobooster.boosters.BoosterManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerExpChangeEvent;

public class PlayerXpGainListener implements Listener {

    private BoosterManager manager;
    public PlayerXpGainListener(BoosterManager manager) {
        this.manager = manager;
    }

    @EventHandler
    public void onAddExperience(McMMOPlayerPreXpGainEvent event) {
        if (manager.isActiveBooster()) {
            event.setXpGained(event.getXpGained() * 2);
        }
    }

    @EventHandler
    public void onAddExperience(PlayerExpChangeEvent event) {
        if (manager.isActiveBooster()) {
            event.setAmount(event.getAmount() * 2);
        }
    }

}
