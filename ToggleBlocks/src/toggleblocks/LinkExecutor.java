package toggleblocks;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LinkExecutor implements CommandExecutor {
    private RegionManager regionManager;
    
    public LinkExecutor(RegionManager regionManager) {
        this.regionManager = regionManager;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player))
            return true;

        Player player = (Player) sender;

        if(args.length != 1) {
            player.sendMessage(PlayerMessage.invalidArguments(command.getUsage()));
            return true;
        }
        
        Region region = regionManager.getRegion(player.getName(), args[0]);
        if(region == null) {
            player.sendMessage(PlayerMessage.missingRegion(args[0]));
            return true;
        }
        
        LinkBlock linkBlock = new LinkBlock(player.getTargetBlock(null, 10), LinkType.Interact);
        
        region.setLinkBlock(linkBlock);
        FileManager.save(regionManager);
        player.sendMessage(PlayerMessage.linkSet(region.getName()));
                
        return true;
    }
    
}
