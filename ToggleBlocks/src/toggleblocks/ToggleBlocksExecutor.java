package toggleblocks;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ToggleBlocksExecutor implements CommandExecutor {
    private ToggleBlocks plugin;
    
    public ToggleBlocksExecutor(ToggleBlocks plugin) {
        this.plugin = plugin;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player))
            return true;

        Player player = (Player) sender;

        if(args.length != 1) {
            player.sendMessage(ToggleBlocks.CHAT_ERROR + "Invalid arguments. Usage: /toggleblocks <name>");
            return true;
        }
           
        RegionManager regionManager = plugin.getRegionManager();
        
        if(regionManager.getRegion(player.getPlayerListName(), args[0]) != null) {
            player.sendMessage(ToggleBlocks.CHAT_ERROR + "Region '" + ToggleBlocks.CHAT_HIGHLIGHT + args[0] + ToggleBlocks.CHAT_ERROR + "' already exists!");
            return true;
        }
        
        Region region = new Region(plugin, player.getPlayerListName(), args[0]);
        regionManager.addRegion(region);
        player.sendMessage(ToggleBlocks.CHAT_NORMAL + "Region '" + ToggleBlocks.CHAT_HIGHLIGHT + args[0] + ToggleBlocks.CHAT_NORMAL + "' was created!");
        
        if(regionManager.getEditRegion(player) != null)
            player.sendMessage(ToggleBlocks.CHAT_NORMAL + "Region '" + ToggleBlocks.CHAT_HIGHLIGHT + regionManager.getEditRegion(player).getName() + ToggleBlocks.CHAT_NORMAL  + "' is no longer in edit mode!");
        
        regionManager.setEditRegion(player, region);
        player.sendMessage(ToggleBlocks.CHAT_NORMAL + "Region '" + ToggleBlocks.CHAT_HIGHLIGHT + args[0] + ToggleBlocks.CHAT_NORMAL + "' is now in edit mode!");

        return true;
    }
    
}
