package nl.canify.rambobooster.listeners;

import nl.canify.rambobooster.boosters.Booster;
import nl.canify.rambobooster.boosters.inventories.BoosterActivateInventory;
import nl.canify.rambobooster.boosters.inventories.BoosterInventory;
import nl.canify.rambobooster.boosters.BoosterManager;
import nl.canify.rambobooster.files.DataManager;
import nl.canify.rambobooster.utils.Formatter;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

public class PlayerClickInventoryListener implements Listener {

    private BoosterManager manager;

    public PlayerClickInventoryListener(BoosterManager manager) {
        this.manager = manager;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {

        Player player = (Player) event.getWhoClicked();

        if (event.getInventory().getHolder() instanceof BoosterInventory) {
            event.setCancelled(true);
            if (event.getRawSlot() == 11) {
                if (manager.isActiveBooster()) {
                    player.closeInventory();
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_PLACE, 10, 10);
                    player.sendMessage(Formatter.color("Er is al een booster actief..."));
                    return;
                }
                if (DataManager.getBoosterAmount(player.getUniqueId()) > 0) { BoosterActivateInventory.open(player); return; }
                player.closeInventory();
                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_PLACE, 10, 10);
                player.sendMessage(Formatter.color("Je hebt geen boosters..."));
            }
        } else if (event.getInventory().getType() == InventoryType.HOPPER && event.getInventory().equals(BoosterActivateInventory.get())) {
            event.setCancelled(true);
            if (event.getSlot() == 0) {
                new Booster(player.getUniqueId());
                player.closeInventory();
            } else if (event.getSlot() == 4) {
                player.closeInventory();
            }
            player.updateInventory(); // Haalt ghost item weg
        }

    }

}
