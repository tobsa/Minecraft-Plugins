package executors;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import toggleblocks.PlayerMessage;
import toggleblocks.Region;
import toggleblocks.RegionManager;
import toggleblocks.ToggleBlocks;

public class ListExecutor implements CommandExecutor {
    private ToggleBlocks plugin;
    
    public ListExecutor(ToggleBlocks plugin) {
        this.plugin = plugin;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player))
            return true;

        Player player = (Player) sender;

        if(args.length != 0) {
            player.sendMessage(PlayerMessage.getInvalidArguments(command.getUsage()));
            return true;
        }
        
        player.sendMessage(PlayerMessage.getListHeader());
        RegionManager regionManager = plugin.getRegionManager();
        
        int number = 1;
        for(Region region : regionManager.getRegions(player.getPlayerListName()))
            player.sendMessage((number++) + ". " + region.getName());
        
        return true;
    }
    
}
