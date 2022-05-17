package nl.canify.rambobooster.utils;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class Formatter {

    public static String color(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public static List<String> color(List<String> text) {
        List<String> newList = new ArrayList<>();
        for (String line : text) {
            newList.add(ChatColor.translateAlternateColorCodes('&', line));
        }
        return newList;
    }

}
