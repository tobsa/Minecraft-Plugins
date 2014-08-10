package toggleblocks.executors;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import toggleblocks.FileManager;
import toggleblocks.Message;
import toggleblocks.Region;
import toggleblocks.RegionManager;

public class DelinkExecutor implements CommandExecutor {
    private RegionManager regionManager;
    
    public DelinkExecutor(RegionManager regionManager) {
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
        
        Region region = regionManager.get(args[0], player.getPlayerListName());
        if(region == null) {
            player.sendMessage(Message.missingRegion(args[0]));
            return true;
        }
        
        region.setLinkBlock(null);
        FileManager.save(regionManager);
        player.sendMessage(Message.getLinkDeleted(region.getName()));
                
        return true;
    }
    
}
