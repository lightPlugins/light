package de.lightplugins.light.api.util;

import de.lightplugins.light.Light;
import org.bukkit.Bukkit;

public class DebugPrinting {

    public void print(String message) {
        Bukkit.getConsoleSender().sendMessage(Light.consolePrefix + message);
    }
}
