package nl.canify.rambobooster.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import nl.canify.rambobooster.boosters.inventories.BoosterInventory;
import nl.canify.rambobooster.files.DataManager;
import nl.canify.rambobooster.utils.Formatter;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

@CommandAlias("booster")
public class BoosterCommand extends BaseCommand {

    @Default
    @Description("Open de booster GUI")
    public static void onBooster(Player player, String[] args) {
        BoosterInventory.open(player);
    }

    @Subcommand("give")
    @CommandPermission("rambobooster.admin")
    @CommandCompletion("@players @range:1-100")
    public static void onGive(Player player, String[] args) {
        int amount;
        try {
            amount = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            player.sendMessage(Formatter.color("&cFoute syntax. Gebruik: /booster give <player> <amount>"));
            return;
        }

        OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
        if (!target.hasPlayedBefore()) {
            player.sendMessage(Formatter.color("&cDe speler &4" + target.getName() + " &cis nog nooit de server gejoined."));
            return;
        }

        DataManager.addBooster(target.getUniqueId(), amount);
        player.sendMessage(Formatter.color("&aJe hebt &f" + amount + " &abooster(s) aan &f" + target.getName() + " &agegeven."));
    }

}
