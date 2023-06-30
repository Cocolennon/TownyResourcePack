package me.senkoco.townyresourcepacks;

import com.palmergames.bukkit.towny.TownyCommandAddonAPI;
import com.palmergames.bukkit.towny.TownyCommandAddonAPI.CommandType;
import me.senkoco.townyresourcepacks.commands.CommandResourcePack;
import me.senkoco.townyresourcepacks.listeners.PlayerEnterTown;
import me.senkoco.townyresourcepacks.listeners.PlayerExitTown;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("Plugin enabled!");
        registerCommandsAndListeners();
    }

    private void registerCommandsAndListeners(){
        TownyCommandAddonAPI.addSubCommand(CommandType.TOWN_SET, "resource-pack", new CommandResourcePack());
        getServer().getPluginManager().registerEvents(new PlayerExitTown(), this);
        getServer().getPluginManager().registerEvents(new PlayerEnterTown(), this);
    }
}
