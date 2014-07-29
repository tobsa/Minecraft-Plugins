package toggleblocks;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EditExecutor implements CommandExecutor {
    private ToggleBlocks plugin;
    
    public EditExecutor(ToggleBlocks plugin) {
        this.plugin = plugin;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player))
            return true;

        Player player = (Player) sender;

        if(args.length != 1) {
            player.sendMessage(ToggleBlocks.CHAT_ERROR + "Invalid arguments. Usage: /toggleblocksedit <name>");
            return true;
        }
        
        RegionManager regionManager = plugin.getRegionManager();
        Region region = regionManager.getRegion(player.getPlayerListName(), args[0]);
        if(region == null) {
            player.sendMessage(ToggleBlocks.CHAT_ERROR + "Region '" + ToggleBlocks.CHAT_HIGHLIGHT + args[0] + ToggleBlocks.CHAT_ERROR + "' doesn't exists!");
            return true;
        }
        
        Region editRegion = regionManager.getEditRegion(player);
        if(editRegion != null)
            player.sendMessage(ToggleBlocks.CHAT_NORMAL + "Region '" + ToggleBlocks.CHAT_HIGHLIGHT + editRegion.getName() + ToggleBlocks.CHAT_NORMAL  + "' is no longer in edit mode!");
        
        if(region == editRegion) {
            regionManager.setEditRegion(player, null);
            return true;
        }
        
        regionManager.setEditRegion(player, region);
        player.sendMessage(ToggleBlocks.CHAT_NORMAL + "Region '" + ToggleBlocks.CHAT_HIGHLIGHT + args[0] + ToggleBlocks.CHAT_NORMAL + "' is now in edit mode!");
        
        return true;
    }
    
}
