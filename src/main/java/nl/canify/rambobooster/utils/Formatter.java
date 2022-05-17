package nl.canify.rambobooster.utils;

import net.md_5.bungee.api.ChatColor;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Formatter {

    public static final Pattern pattern = Pattern.compile("&#(\\w{5}[0-9a-f])");
    public static String color(String text) {
        Matcher matcher = pattern.matcher(text);
        StringBuffer buffer = new StringBuffer();
        while(matcher.find()) {
            matcher.appendReplacement(buffer, ChatColor.of("#" + matcher.group(1)).toString());
        }
        return ChatColor.translateAlternateColorCodes('&', matcher.appendTail(buffer).toString());
    }

    public static List<String> color(List<String> text) {
        List<String> newList = new ArrayList<>();
        for (String line : text) {
            newList.add(ChatColor.translateAlternateColorCodes('&', line));
        }
        return newList;
    }

}
