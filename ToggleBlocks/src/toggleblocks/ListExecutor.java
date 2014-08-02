package toggleblocks;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ListExecutor implements CommandExecutor {
    private RegionManager regionManager;
    
    public ListExecutor(RegionManager regionManager) {
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
        
        player.sendMessage(PlayerMessage.listHeader());
        
        int number = 1;
        for(Region region : regionManager.getRegions(player.getName()))
            player.sendMessage((number++) + ". " + region.getName());
        
        return true;
    }
    
}
