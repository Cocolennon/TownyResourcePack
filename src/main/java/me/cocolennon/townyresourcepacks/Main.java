package me.cocolennon.townyresourcepacks;

import com.palmergames.bukkit.towny.TownyCommandAddonAPI;
import com.palmergames.bukkit.towny.TownyCommandAddonAPI.CommandType;
import me.cocolennon.townyresourcepacks.commands.CommandResourcePack;
import me.cocolennon.townyresourcepacks.commands.TogglePacksCommand;
import me.cocolennon.townyresourcepacks.listeners.PlayerEnterTown;
import me.cocolennon.townyresourcepacks.listeners.PlayerExitTown;
import me.cocolennon.townyresourcepacks.listeners.PlayerJoined;
import me.cocolennon.townyresourcepacks.utils.UpdateChecker;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class Main extends JavaPlugin {
    public static Main instance;
    FileConfiguration config = getConfig();
    private String version;
    private String latestVersion;
    private boolean usingOldVersion = false;

    @Override
    public void onEnable() {
        instance = this;
        checkVersion();
        setupConfig();
        registerCommands();
        registerListeners();
        getLogger().info("Plugin enabled!");
    }

    public void checkVersion() {
        new UpdateChecker(this, 105579).getVersion(cVersion -> {
            version = this.getDescription().getVersion();
            latestVersion = cVersion;
            if (!getVersion().equals(cVersion)) {
                getLogger().info("You are using an older version of Towny Resource Packs, please update to version " + cVersion);
                usingOldVersion = true;
            }
        });
    }

    private void setupConfig() {
        config.addDefault("default-resource-pack", "https://www.curseforge.com/api/v1/mods/457153/files/4572162/download");
        config.options().setHeader(List.of("default-resource-pack: The resource pack that will be used when a player is outside of a town"));
        config.options().copyDefaults(true);
        saveConfig();
    }

    private void registerCommands(){
        TownyCommandAddonAPI.addSubCommand(CommandType.TOWN_SET, "resource-pack", new CommandResourcePack());
        getCommand("toggle-resource-packs").setExecutor(new TogglePacksCommand());
    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new PlayerExitTown(), this);
        getServer().getPluginManager().registerEvents(new PlayerEnterTown(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoined(), this);
    }

    public String getVersion() { return version; }
    public String getLatestVersion(){ return latestVersion; }
    public boolean getUsingOldVersion() { return usingOldVersion; }
    public static Main getInstance() { return instance; }
}
