package toggleblocks.executors;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import toggleblocks.Message;
import toggleblocks.Region;
import toggleblocks.RegionManager;

public class ToggleOffExecutor implements CommandExecutor {
    private RegionManager regionManager;
    
    public ToggleOffExecutor(RegionManager regionManager) {
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
        
        region.toggleOff();
        player.sendMessage(Message.toggledOff(args[0]));
        
        return true;
    }
    
}
//