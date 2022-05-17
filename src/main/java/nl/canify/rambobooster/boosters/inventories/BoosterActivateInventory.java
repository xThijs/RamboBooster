package nl.canify.rambobooster.boosters.inventories;

import nl.canify.rambobooster.utils.Formatter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BoosterActivateInventory {

    private static Inventory inventory;

    private final ItemStack yes;
    private final ItemMeta yesMeta;
    private final ItemStack no;
    private final ItemMeta noMeta;

    public BoosterActivateInventory() {
        inventory = Bukkit.createInventory(null, InventoryType.HOPPER, Formatter.color("&aWeet je het zeker?"));

        yes = new ItemStack(Material.GREEN_WOOL, 1);
        yesMeta = yes.getItemMeta();
        yesMeta.setDisplayName(Formatter.color("&aJa"));
        yesMeta.setLocalizedName("yes");
        yes.setItemMeta(yesMeta);
        inventory.setItem(0, yes);

        no = new ItemStack(Material.RED_WOOL, 1);
        noMeta = no.getItemMeta();
        noMeta.setDisplayName(Formatter.color("&cNee"));
        noMeta.setLocalizedName("no");
        no.setItemMeta(noMeta);
        inventory.setItem(4, no);
    }

    public static void open(Player player) {
        player.openInventory(inventory);
    }

    public static Inventory get() {
        return inventory;
    }

}
