package executors;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import toggleblocks.PlayerMessage;
import toggleblocks.Region;
import toggleblocks.RegionManager;
import toggleblocks.ToggleBlocks;

public class DelinkExecutor implements CommandExecutor {
    private ToggleBlocks plugin;
    
    public DelinkExecutor(ToggleBlocks plugin) {
        this.plugin = plugin;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player))
            return true;

        Player player = (Player) sender;

        if(args.length != 1) {
            player.sendMessage(PlayerMessage.getInvalidArguments(command.getUsage()));
            return true;
        }
        
        RegionManager regionManager = plugin.getRegionManager();
        Region region = regionManager.getRegion(player.getPlayerListName(), args[0]);
        if(region == null) {
            player.sendMessage(PlayerMessage.getMissingRegion(args[0]));
            return true;
        }
        
        region.setLinkBlock(null);
        player.sendMessage(PlayerMessage.getLinkDeleted(region.getName()));
        
        plugin.getConfig().set("toggleblocks." + region.getName() + ".link", null);
        plugin.saveConfig();
        
        return true;
    }
    
}
