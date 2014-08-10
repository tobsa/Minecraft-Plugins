package toggleblocks.executors;

import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import toggleblocks.LinkBlock;
import toggleblocks.Message;
import toggleblocks.Region;
import toggleblocks.RegionManager;

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
            player.sendMessage(Message.invalidArguments(command.getUsage()));
            return true;
        }
         
        Block block = player.getTargetBlock(null, 6);
        Region region = regionManager.getRegionByLinkBlock(player.getName(), block);
        if(region == null) {
            player.sendMessage(Message.missingLink(block));
            return true;
        }
        
        LinkBlock linkBlock = region.getLinkBlock();
        player.sendMessage(Message.infoHeader());
        player.sendMessage(Message.regionInfoBlock(linkBlock));
        player.sendMessage(Message.linkType(linkBlock.getLinkType()));
        player.sendMessage(Message.regionName(region.getName()));
                
        return true;
    }
    
}
