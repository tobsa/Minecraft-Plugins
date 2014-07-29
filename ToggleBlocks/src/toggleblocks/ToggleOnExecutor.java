package toggleblocks;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ToggleOnExecutor implements CommandExecutor {
    private ToggleBlocks plugin;
    
    public ToggleOnExecutor(ToggleBlocks plugin) {
        this.plugin = plugin;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player))
            return true;

        Player player = (Player) sender;

        if(args.length != 1) {
            player.sendMessage(ToggleBlocks.CHAT_ERROR + "Invalid arguments. Usage: /toggleblockson <name>");
            return true;
        }
        
        RegionManager regionManager = plugin.getRegionManager();
        Region region = regionManager.getRegion(player.getPlayerListName(), args[0]);
        if(region == null) {
            player.sendMessage(ToggleBlocks.CHAT_ERROR + "Region '" + ToggleBlocks.CHAT_HIGHLIGHT + args[0] + ToggleBlocks.CHAT_ERROR + "' doesn't exists!");
            return true;
        }
        
        region.toggleOn();
        
        return true;
    }
    
}
