package me.senkoco.townyresourcepacks;

import com.palmergames.bukkit.towny.TownyCommandAddonAPI;
import com.palmergames.bukkit.towny.TownyCommandAddonAPI.CommandType;
import me.senkoco.townyresourcepacks.commands.CommandResourcePack;
import me.senkoco.townyresourcepacks.listeners.PlayerEnterTown;
import me.senkoco.townyresourcepacks.listeners.PlayerExitTown;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    public static Main instance;

    @Override
    public void onEnable() {
        getLogger().info("Plugin enabled!");
        registerCommands();
        registerListeners();

        instance = this;
    }

    private void registerCommands(){
        TownyCommandAddonAPI.addSubCommand(CommandType.TOWN_SET, "resource-pack", new CommandResourcePack());
    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new PlayerExitTown(), this);
        getServer().getPluginManager().registerEvents(new PlayerEnterTown(), this);
    }
    public static Main getInstance() { return instance; }
}
