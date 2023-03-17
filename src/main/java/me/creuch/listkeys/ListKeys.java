package me.creuch.listkeys;

import me.creuch.listkeys.commands.Klucze;
import org.bukkit.plugin.java.JavaPlugin;

public final class ListKeys extends JavaPlugin {
    public static ListKeys instance;

    @Override
    public void onEnable() {
        instance = this;
        this.saveDefaultConfig();
        getCommand("klucze").setExecutor(new Klucze());
        Messages.sendConsole("&7Plugin has been enabled");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static ListKeys getInstance() {
        return instance;
    }
}
