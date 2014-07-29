package toggleblocks;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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
            player.sendMessage(ToggleBlocks.CHAT_ERROR + "Invalid arguments. Usage: /toggleblocklist");
            return true;
        }
        
        player.sendMessage(ToggleBlocks.CHAT_HEADER + "======= Regions =======");
        RegionManager regionManager = plugin.getRegionManager();
        for(Region region : regionManager.getRegions(player.getPlayerListName()))
            player.sendMessage(ToggleBlocks.CHAT_HIGHLIGHT + region.getName());
        
        return true;
    }
    
}
