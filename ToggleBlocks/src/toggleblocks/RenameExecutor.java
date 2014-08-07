package toggleblocks;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RenameExecutor implements CommandExecutor {
    private RegionManager regionManager;
    
    public RenameExecutor(RegionManager regionManager) {
        this.regionManager = regionManager;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player))
            return true;

        Player player = (Player) sender;

        if(args.length != 2) {
            player.sendMessage(PlayerMessage.invalidArguments(command.getUsage()));
            return true;
        }
        
        Region region = regionManager.getRegion(player.getName(), args[0]);
        if(region == null) {
            player.sendMessage(PlayerMessage.missingRegion(args[0]));
            return true;
        }
        
        if(regionManager.getRegion(player.getName(), args[1]) != null) {
            player.sendMessage(PlayerMessage.regionExists(args[1]));
            return true;
        }
        
        regionManager.renameRegion(region, args[1]);
        FileManager.save(regionManager);
        player.sendMessage(PlayerMessage.regionRenamed(args[0], args[1]));

        return true;
    }
}
