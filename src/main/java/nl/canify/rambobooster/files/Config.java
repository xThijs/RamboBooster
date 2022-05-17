package nl.canify.rambobooster.files;

import nl.canify.rambobooster.RamboBooster;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class Config {

    private final FileConfiguration config;
    public static int MINUTES;
    public static String BOOSTER_ACTIVATED;
    public static String BOOSTER_DEACTIVATED;
    public static String TIP_PLAYER;
    public static String TIPPED_PLAYER;
    public static String ALREADY_ACTIVE;
    public static String NO_BOOSTERS;
    public static String NO_ACTIVE;
    public static String NO_ACTIVE_PLAYER;
    public static String ALREADY_TIPPED;
    public static String TIP_COMMAND;
    public static String SELF_TIP;
    public static List<String> TIP_HELP;

    public static String GUI_TITLE;
    public static String GUI_BOOSTER_NAME;
    public static List<String> GUI_BOOSTER_LORE;
    public static String GUI_BOOSTER_HELP_NAME;
    public static List<String> GUI_BOOSTER_HELP_LORE;

    private RamboBooster plugin;

    public Config(RamboBooster plugin) {
        this.plugin = plugin;
        config = plugin.getConfig();
        config.options().copyDefaults();
        plugin.saveDefaultConfig();

        MINUTES = config.getInt("minutes");
        BOOSTER_ACTIVATED = config.getString("messages.booster-activated");
        BOOSTER_DEACTIVATED = config.getString("messages.booster-deactivated");
        TIP_PLAYER = config.getString("messages.tip-player");
        TIPPED_PLAYER = config.getString("messages.tipped-player");
        ALREADY_ACTIVE = config.getString("messages.already-active");
        NO_BOOSTERS = config.getString("messages.no-boosters");
        NO_ACTIVE = config.getString("messages.no-active");
        NO_ACTIVE_PLAYER = config.getString("messages.no-active-player");
        ALREADY_TIPPED = config.getString("messages.already-tipped");
        SELF_TIP = config.getString("messages.self-tip");
        TIP_COMMAND = config.getString("tip-command");
        TIP_HELP = config.getStringList("messages.tip-help");

        GUI_TITLE = config.getString("gui.title");
        GUI_BOOSTER_NAME = config.getString("gui.booster.name");
        GUI_BOOSTER_LORE = config.getStringList("gui.booster.lore");
        GUI_BOOSTER_HELP_NAME = config.getString("gui.booster-help.name");
        GUI_BOOSTER_HELP_LORE = config.getStringList("gui.booster-help.lore");

    }

}
