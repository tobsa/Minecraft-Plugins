package toggleblocks;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ToggleBlocksExecutor implements CommandExecutor {
    private RegionManager regionManager;
    
    public ToggleBlocksExecutor(RegionManager regionManager) {
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
        if(region != null) {
            Region editRegion = regionManager.getEditRegion(player.getName());
            if(editRegion == null) {
                regionManager.setEditRegion(player.getName(), region);
                player.sendMessage(PlayerMessage.editmodeYes(region.getName()));
            }
            else {
                if(region == editRegion) {                  
                    regionManager.setEditRegion(player.getName(), null);
                    player.sendMessage(PlayerMessage.editmodeNo(region.getName()));
                    FileManager.save(regionManager);
                } 
                else {
                    player.sendMessage(PlayerMessage.editmodeNo(editRegion.getName()));
                    regionManager.setEditRegion(player.getName(), region);
                    player.sendMessage(PlayerMessage.editmodeYes(region.getName()));
                }
            }
            
            return true;
        }
        
        region = new Region(args[0], player.getName());
        regionManager.addRegion(region);
        player.sendMessage(PlayerMessage.regionCreated(args[0]));
        
        Region editRegion = regionManager.getEditRegion(player.getName());
        if(editRegion != null)
            player.sendMessage(PlayerMessage.editmodeNo(editRegion.getName()));
        
        regionManager.setEditRegion(player.getName(), region);
        player.sendMessage(PlayerMessage.editmodeYes(args[0]));

        return true;
    }
    
}
