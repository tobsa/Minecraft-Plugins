package toggleblocks.executors;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import toggleblocks.FileManager;
import toggleblocks.Message;
import toggleblocks.Region;
import toggleblocks.RegionManager;

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
            player.sendMessage(Message.invalidArguments(command.getUsage()));
            return true;
        }
        
        Region region = regionManager.get(args[0], player.getName());
        if(region == null) {
            player.sendMessage(Message.missingRegion(args[0]));
            return true;
        }
        
        if(regionManager.get(args[1], player.getName()) != null) {
            player.sendMessage(Message.regionExists(args[1]));
            return true;
        }
        
        regionManager.renameItem(region, args[1]);
        FileManager.save(regionManager);
        player.sendMessage(Message.regionRenamed(args[0], args[1]));

        return true;
    }
}
