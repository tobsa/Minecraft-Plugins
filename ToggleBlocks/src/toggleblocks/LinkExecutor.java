package toggleblocks;

import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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
            player.sendMessage(ToggleBlocks.CHAT_ERROR + "Invalid arguments. Usage: /toggleblockslink <name>");
            return true;
        }
        
        RegionManager regionManager = plugin.getRegionManager();
        Region region = regionManager.getRegion(player.getPlayerListName(), args[0]);
        if(region == null) {
            player.sendMessage(ToggleBlocks.CHAT_ERROR + "Region '" + ToggleBlocks.CHAT_HIGHLIGHT + args[0] + ToggleBlocks.CHAT_ERROR + "' doesn't exists!");
            return true;
        }
        
        Block linkBlock = player.getTargetBlock(null, 6);
        region.setLinkBlock(linkBlock);
        player.sendMessage(ToggleBlocks.CHAT_NORMAL + "A link for region '" + ToggleBlocks.CHAT_HIGHLIGHT + args[0] + ToggleBlocks.CHAT_NORMAL + "' has been set!");
        
        plugin.getConfig().set("toggleblocks." + region.getName() + ".link.x", linkBlock.getX());
        plugin.getConfig().set("toggleblocks." + region.getName() + ".link.y", linkBlock.getY());
        plugin.getConfig().set("toggleblocks." + region.getName() + ".link.z", linkBlock.getZ());
        plugin.saveConfig();
        
        return true;
    }
    
}
