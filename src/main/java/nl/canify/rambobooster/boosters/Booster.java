package nl.canify.rambobooster.boosters;

import nl.canify.rambobooster.RamboBooster;
import nl.canify.rambobooster.files.Config;
import nl.canify.rambobooster.files.DataManager;
import nl.canify.rambobooster.utils.Formatter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Booster {

    private final Booster booster;
    private final UUID owner;
    private final long startTime;
    private final long endTime;
    private final long difference;
    private final BossBar bossBar;

    private final BoosterManager manager;
    private final BukkitTask task;

    private List<UUID> tipped;

    public Booster(UUID owner) {
        this.owner = owner;
        this.manager = RamboBooster.getInstance().getManager();
        this.startTime = System.currentTimeMillis();
        this.endTime = startTime + (60_000L * Config.MINUTES);
        this.difference = endTime - startTime;
        booster = this;
        tipped = new ArrayList<>();

        DataManager.subtractBooster(owner);

        bossBar = Bukkit.createBossBar(
                ChatColor.GREEN + RamboBooster.getInstance().getServer().getOfflinePlayer(owner).getName() + "'s " + ChatColor.WHITE + "booster " + ChatColor.RED + "(60:00)",
                BarColor.GREEN,
                BarStyle.SEGMENTED_20);
        for (Player player : Bukkit.getOnlinePlayers()) {
            bossBar.addPlayer(player);
        }

        Bukkit.broadcastMessage(Formatter.color(Config.BOOSTER_ACTIVATED)
                .replace("%booster_owner%", Bukkit.getOfflinePlayer(owner).getName())
                .replace("%nl%", "\n"));

        task = new BukkitRunnable() {
            @Override
            public void run() {
                String title = bossBar.getTitle();
                double differenceNow = (double) endTime - (double) System.currentTimeMillis();
                int seconds = (int) (differenceNow / 1000) % 60 ;
                int minutes = (int) ((differenceNow / (1000*60)) % 60);
                bossBar.setTitle(ChatColor.GREEN + RamboBooster.getInstance().getServer().getOfflinePlayer(owner).getName() + "'s " + ChatColor.WHITE + "booster " +
                        ChatColor.RED + "(" + (minutes >= 10 ? "" : "0") + minutes + ":" + (seconds >= 10 ? "" : "0") + seconds +")");

                double percentage = differenceNow / difference;
                if (differenceNow < 0) {
                    Bukkit.broadcastMessage(Formatter.color(Config.BOOSTER_DEACTIVATED).replace("%booster_owner%", Bukkit.getOfflinePlayer(owner).getName()).replace("%nl%", "\n"));
                    bossBar.removeAll();
                    cancel();
                    manager.removeBooster(booster);
                    return;
                }
                bossBar.setProgress(percentage);
            }
        }.runTaskTimer(RamboBooster.getInstance(), 0, 20);
        manager.addBooster(this);
    }

    public void cancelTask() {task.cancel();}

    public UUID getOwnerUUID() {
        return owner;
    }

    public void addTipper(UUID tipper) {
        tipped.add(tipper);
    }

    public boolean hasTipped(UUID uuid) {
        return tipped.contains(uuid);
    }

}
