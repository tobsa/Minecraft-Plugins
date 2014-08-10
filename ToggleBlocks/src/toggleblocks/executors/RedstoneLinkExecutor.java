package toggleblocks.executors;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import toggleblocks.FileManager;
import toggleblocks.LinkBlock;
import toggleblocks.LinkType;
import toggleblocks.Message;
import toggleblocks.Region;
import toggleblocks.RegionManager;

public class RedstoneLinkExecutor implements CommandExecutor {
    private RegionManager regionManager;
    
    public RedstoneLinkExecutor(RegionManager regionManager) {
        this.regionManager = regionManager;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player))
            return true;

        Player player = (Player) sender;

        if(args.length != 1) {
            player.sendMessage(Message.invalidArguments(command.getUsage()));
            return true;
        }
        
        Region region = regionManager.get(args[0], player.getName());
        if(region == null) {
            player.sendMessage(Message.missingRegion(args[0]));
            return true;
        }
        
        LinkBlock linkBlock = new LinkBlock(player.getTargetBlock(null, 6), LinkType.Redstone);
        
        region.setLinkBlock(linkBlock);
        FileManager.save(regionManager);
        player.sendMessage(Message.redstoneLinkSet(region.getName()));
                
        return true;
    }
    
}
