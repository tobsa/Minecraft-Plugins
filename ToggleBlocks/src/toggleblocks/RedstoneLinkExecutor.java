package toggleblocks;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RedstoneLinkExecutor implements CommandExecutor {
    private ToggleBlocks plugin;
    
    public RedstoneLinkExecutor(ToggleBlocks plugin) {
        this.plugin = plugin;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player))
            return true;

        Player player = (Player) sender;

        if(args.length != 1) {
            player.sendMessage(ToggleBlocks.CHAT_ERROR + "Invalid arguments. Usage: /toggleblocksrlink <name>");
            return true;
        }
        
        RegionManager regionManager = plugin.getRegionManager();
        Region region = regionManager.getRegion(player.getPlayerListName(), args[0]);
        if(region == null) {
            player.sendMessage(ToggleBlocks.CHAT_ERROR + "Region '" + ToggleBlocks.CHAT_HIGHLIGHT + args[0] + ToggleBlocks.CHAT_ERROR + "' doesn't exists!");
            return true;
        }
        
        LinkBlock linkBlock = new LinkBlock(player.getTargetBlock(null, 10), LinkType.Redstone);
        region.setLinkBlock(linkBlock);
        player.sendMessage(ToggleBlocks.CHAT_NORMAL + "A redstone link for region '" + ToggleBlocks.CHAT_HIGHLIGHT + args[0] + ToggleBlocks.CHAT_NORMAL + "' has been set!");
        
        plugin.getConfig().set("toggleblocks." + region.getName() + ".link.x", linkBlock.getBlock().getX());
        plugin.getConfig().set("toggleblocks." + region.getName() + ".link.y", linkBlock.getBlock().getY());
        plugin.getConfig().set("toggleblocks." + region.getName() + ".link.z", linkBlock.getBlock().getZ());
        plugin.getConfig().set("toggleblocks." + region.getName() + ".link.type", linkBlock.getLinkType().toString());
        plugin.saveConfig();
        
        return true;
    }
    
}
