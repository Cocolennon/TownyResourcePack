package me.senkoco.townyresourcepacks.commands;

import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.Town;
import me.senkoco.townyresourcepacks.utils.metadata.MetaData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static me.senkoco.townyresourcepacks.utils.metadata.MetaData.setResourcePackLink;

public class CommandResourcePack implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player)sender;
        Resident res = TownyAPI.getInstance().getResident(player);
        if(!res.hasTown()) {
            sender.sendMessage("§6[Towny Resource Packs] §cYou aren't in a town!");
            return false;
        }
        Town town = res.getTownOrNull();
        if(!res.isMayor()){
            sender.sendMessage("§6[Towny Resource Packs] §cYou aren't the mayor of your town!");
            return false;
        }
        if(args.length == 0) {
            sender.sendMessage("§6[Towny Resource Packs] §cPlease provide a URL!");
            return false;
        }
        if(args[0].equalsIgnoreCase("clear")){
            setResourcePackLink(town, "clear");
            sender.sendMessage("§6[Towny Resource Packs] §eSuccessfully cleared your town's resource pack!");
            return true;
        }
        if(!args[0].startsWith("http")){
            sender.sendMessage("§6[Towny Resource Packs] §cPlease provide a valid URL!");
            return false;
        }

        setResourcePackLink(town, args[0]);
        sender.sendMessage("§6[Towny Resource Packs] §eYou have successfully set your town's resource pack!\n§eYou may have to exit and re-enter your claims for the resource pack to set!");
        return true;
    }
}
