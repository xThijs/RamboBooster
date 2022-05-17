package nl.canify.rambobooster;

import co.aikar.commands.PaperCommandManager;
import nl.canify.rambobooster.boosters.inventories.BoosterActivateInventory;
import nl.canify.rambobooster.boosters.inventories.BoosterInventory;
import nl.canify.rambobooster.boosters.BoosterManager;
import nl.canify.rambobooster.commands.BoosterCommand;
import nl.canify.rambobooster.commands.TipCommand;
import nl.canify.rambobooster.files.DataManager;
import nl.canify.rambobooster.listeners.PlayerClickInventoryListener;
import nl.canify.rambobooster.listeners.PlayerXpGainListener;
import nl.canify.rambobooster.files.Config;
import org.bukkit.plugin.java.JavaPlugin;

public final class RamboBooster extends JavaPlugin {


    private static RamboBooster instance;

    public static RamboBooster getInstance() {return instance;}
    private Config config;
    private BoosterManager manager;
    public BoosterManager getManager() {return manager;}
    private BoosterInventory boosterInventory;
    private BoosterActivateInventory boosterActivateInventory;


    @Override
    public void onEnable() {
        instance = this;
        config = new Config(instance);
        manager = new BoosterManager();
        boosterInventory = new BoosterInventory(manager);
        boosterActivateInventory = new BoosterActivateInventory();
        getServer().getPluginManager().registerEvents(new PlayerXpGainListener(manager), this);
        getServer().getPluginManager().registerEvents(new PlayerClickInventoryListener(manager), this);

        PaperCommandManager commandManager = new PaperCommandManager(this);
        commandManager.registerCommand(new BoosterCommand());
        commandManager.registerCommand(new TipCommand(manager));
        DataManager.createFile(this);
    }

    @Override
    public void onDisable() {
    }
}
