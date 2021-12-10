package me.justkilling.simpleslimechunks;

import me.justkilling.simpleslimechunks.Commands.getslimechunk;
import me.justkilling.simpleslimechunks.Events.ClickEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class SimpleSlimechunks extends JavaPlugin {

    private static SimpleSlimechunks _instance;

    public static SimpleSlimechunks getInstance(){
        if(_instance == null)
        {
            _instance = new SimpleSlimechunks();
        }

        return _instance;
    }
    @Override
    public void onEnable() {
        // Plugin startup logic
        _instance= this;
        ItemManager.init();



        this.reloadConfig();
        this.saveDefaultConfig();

        this.getCommand("getslimechunk").setExecutor(new getslimechunk());
        getServer().getPluginManager().registerEvents(new ClickEvent(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
