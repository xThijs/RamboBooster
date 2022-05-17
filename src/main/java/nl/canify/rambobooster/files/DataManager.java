package nl.canify.rambobooster.files;

import nl.canify.rambobooster.RamboBooster;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DataManager {

    private static YamlConfiguration data;
    private static File file;
    private static RamboBooster main;
    private static Map<UUID, Integer> boosterAmountMap;

    public static void createFile(RamboBooster plugin) {
        main = plugin;
        file = new File(plugin.getDataFolder(), "data.yml");
        if (!file.exists()) {
            plugin.saveResource("data.yml", false);
            data.createSection("data");
        }
        data = YamlConfiguration.loadConfiguration(file);
        save();
        boosterAmountMap = new HashMap<>();
        loadDataToMap();
    }

    public static int getBoosterAmount(UUID uuid) {
        if (!boosterAmountMap.containsKey(uuid)) return 0;
        return boosterAmountMap.get(uuid);
    }

    public static int getBoosterAmountGUI(UUID uuid) {
        if (!boosterAmountMap.containsKey(uuid)) return 1;
        return boosterAmountMap.get(uuid);
    }

    public static void subtractBooster(UUID uuid) {
        if (boosterAmountMap.get(uuid) == 1) {
            boosterAmountMap.remove(uuid);
            data.set("data." + uuid, null);
            save();
            return;
        }
        boosterAmountMap.put(uuid, boosterAmountMap.get(uuid) - 1);
        data.set("data." + uuid, boosterAmountMap.get(uuid));
        save();
    }

    public static void addBooster(UUID uuid, int amount) {
        if (boosterAmountMap.containsKey(uuid)) {
            boosterAmountMap.put(uuid, boosterAmountMap.get(uuid) + amount);
        } else {
            boosterAmountMap.put(uuid, amount);
        }
        data.set("data." + uuid, boosterAmountMap.get(uuid));
        data.getConfigurationSection("data").set(String.valueOf(uuid), boosterAmountMap.get(uuid));
        save();

    }

    private static void loadDataToMap() {
        for (String key : data.getConfigurationSection("data").getKeys(false)) {
            boosterAmountMap.put(UUID.fromString(key), data.getInt("data." + key));
        }
    }

    private static void save() {
        Bukkit.getScheduler().runTaskAsynchronously(RamboBooster.getInstance(), () -> {
            try {
                data.save(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }



}
