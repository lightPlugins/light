package io.lightplugins.light.api.util;

import io.lightplugins.light.Light;
import org.bukkit.Bukkit;

public class DebugPrinting {

    public void print(String message) {
        Bukkit.getConsoleSender().sendMessage(Light.consolePrefix + message);
    }
}
