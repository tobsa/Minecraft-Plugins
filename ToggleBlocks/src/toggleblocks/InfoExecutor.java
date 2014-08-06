package toggleblocks;

import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class InfoExecutor implements CommandExecutor {
    private RegionManager regionManager;
    
    public InfoExecutor(RegionManager regionManager) {
        this.regionManager = regionManager;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player))
            return true;

        Player player = (Player) sender;

        if(args.length != 0) {
            player.sendMessage(PlayerMessage.invalidArguments(command.getUsage()));
            return true;
        }
         
        Block block = player.getTargetBlock(null, 6);
        Region region = regionManager.getRegionByLinkBlock(player.getName(), block);
        if(region == null) {
            player.sendMessage(PlayerMessage.missingLink(block));
            return true;
        }
        
        LinkBlock linkBlock = region.getLinkBlock();
        player.sendMessage(PlayerMessage.infoHeader());
        player.sendMessage(PlayerMessage.regionInfoBlock(linkBlock));
        player.sendMessage(PlayerMessage.linkType(linkBlock.getLinkType()));
        player.sendMessage(PlayerMessage.regionName(region.getName()));
                
        return true;
    }
    
}
