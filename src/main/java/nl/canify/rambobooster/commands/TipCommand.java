package nl.canify.rambobooster.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import nl.canify.rambobooster.boosters.Booster;
import nl.canify.rambobooster.boosters.BoosterManager;
import nl.canify.rambobooster.files.Config;
import nl.canify.rambobooster.utils.Formatter;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

@CommandAlias("tip")
public class TipCommand extends BaseCommand {

    private BoosterManager manager;

    public TipCommand(BoosterManager manager) {
        this.manager = manager;
    }

    @Default
    @Description("Tip Command")
    public void onDefault(Player player, String args[]) {
        if (args.length != 1) {
            for (String line : Config.TIP_HELP) {
                player.sendMessage(Formatter.color(line));
            }
        } else {
            if (!manager.isActiveBooster()) { player.sendMessage(Formatter.color(Config.NO_ACTIVE)); return;}
            OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
            Booster booster = manager.getActiveBooster();
            if (booster.getOwnerUUID().equals(target.getUniqueId())) {
                if (booster.getOwnerUUID().equals(player.getUniqueId())) {  player.sendMessage(Formatter.color(Config.SELF_TIP)); return; }
                if (booster.hasTipped(player.getUniqueId())) {player.sendMessage(Formatter.color(Config.ALREADY_TIPPED)); return;
                } else {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), Config.TIP_COMMAND);
                    booster.addTipper(player.getUniqueId());
                    player.sendMessage(Formatter.color(Config.TIP_PLAYER).replace("%booster_owner%", Bukkit.getOfflinePlayer(booster.getOwnerUUID()).getName()));
                    Player owner = Bukkit.getPlayer(booster.getOwnerUUID());
                    if (owner == null) return;
                    owner.sendMessage(Formatter.color(Config.TIPPED_PLAYER).replace("%tipper%", player.getName()));
                }
            } else {
                player.sendMessage(Formatter.color(Config.NO_ACTIVE_PLAYER));
                return;
            }
        }
    }
}
