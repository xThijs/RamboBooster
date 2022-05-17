package nl.canify.rambobooster.boosters.inventories;

import nl.canify.rambobooster.boosters.BoosterManager;
import nl.canify.rambobooster.files.Config;
import nl.canify.rambobooster.files.DataManager;
import nl.canify.rambobooster.utils.Formatter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class BoosterInventory implements InventoryHolder {

    private static BoosterManager manager;
    private static Inventory inventory;
    private ItemStack help;
    private ItemMeta helpMeta;

    private static ItemStack boosterItem;
    private static ItemMeta boosterMeta;

    public BoosterInventory(BoosterManager manager) {
        inventory = Bukkit.createInventory(this, 27, Formatter.color(Config.GUI_TITLE));
        createHelpItem();
        inventory.setItem(15, help);
        boosterItem = new ItemStack(Material.EXPERIENCE_BOTTLE, 1);
        boosterMeta = boosterItem.getItemMeta();
        boosterMeta.setDisplayName(Formatter.color(Config.GUI_BOOSTER_NAME));
        this.manager = manager;
    }

    public static void open(Player player) {
        createBoosterItem(player);
        inventory.setItem(11, boosterItem);
        player.openInventory(inventory);
    }

    private void createHelpItem() {
        help = new ItemStack(Material.PAPER, 1);
        helpMeta = help.getItemMeta();
        helpMeta.setDisplayName(Formatter.color(Config.GUI_BOOSTER_HELP_NAME));
        helpMeta.setLore(Formatter.color(Config.GUI_BOOSTER_HELP_LORE));
        help.setItemMeta(helpMeta);
    }

    private static void createBoosterItem(Player player) {
        List<String> loreList = new ArrayList<>();
        for (String line : Config.GUI_BOOSTER_LORE) {
            loreList.add(Formatter.color(line).replace("%boosters%", DataManager.getBoosterAmount(player.getUniqueId()) + " booster" + (DataManager.getBoosterAmount(player.getUniqueId()) != 1 ? "s" : "")));
        }
        loreList.add(" ");
        loreList.add(Formatter.color(manager.isActiveBooster() ? "&cEr is op dit moment al een booster actief." : "&aEr is op dit moment geen booster actief."));
        boosterMeta.setLore(loreList);
        boosterItem.setItemMeta(boosterMeta);
    }

    @NotNull
    @Override
    public Inventory getInventory() {
        return inventory;
    }
}

