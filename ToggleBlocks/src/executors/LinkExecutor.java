package executors;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import toggleblocks.LinkBlock;
import toggleblocks.LinkType;
import toggleblocks.PlayerMessage;
import toggleblocks.Region;
import toggleblocks.RegionManager;
import toggleblocks.ToggleBlocks;

public class LinkExecutor implements CommandExecutor {
    private ToggleBlocks plugin;
    
    public LinkExecutor(ToggleBlocks plugin) {
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
        
        LinkBlock linkBlock = new LinkBlock(player.getTargetBlock(null, 10), LinkType.Interact);
        
        region.setLinkBlock(linkBlock);
        player.sendMessage(PlayerMessage.getLinkSet(region.getName()));
        
        plugin.getConfig().set("toggleblocks." + region.getName() + ".link.x", linkBlock.getBlock().getX());
        plugin.getConfig().set("toggleblocks." + region.getName() + ".link.y", linkBlock.getBlock().getY());
        plugin.getConfig().set("toggleblocks." + region.getName() + ".link.z", linkBlock.getBlock().getZ());
        plugin.getConfig().set("toggleblocks." + region.getName() + ".link.type", linkBlock.getLinkType().toString());
        plugin.saveConfig();
        
        return true;
    }
    
}
