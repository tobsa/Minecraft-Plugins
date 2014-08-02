package toggleblocks;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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
            player.sendMessage(PlayerMessage.invalidArguments(command.getUsage()));
            return true;
        }
        
        Region region = regionManager.getRegion(player.getPlayerListName(), args[0]);
        if(region == null) {
            player.sendMessage(PlayerMessage.missingRegion(args[0]));
            return true;
        }
        
        region.setLinkBlock(null);
        FileManager.save(regionManager);
        player.sendMessage(PlayerMessage.getLinkDeleted(region.getName()));
                
        return true;
    }
    
}
