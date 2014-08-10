package toggleblocks.executors;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import toggleblocks.FileManager;
import toggleblocks.Message;
import toggleblocks.Region;
import toggleblocks.RegionManager;

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
            player.sendMessage(Message.invalidArguments(command.getUsage()));
            return true;
        }
                   
        Region region = regionManager.get(args[0], player.getName());
        if(region != null) {
            Region editRegion = regionManager.getEditRegion(player.getName());
            if(editRegion == null) {
                regionManager.setEditRegion(player.getName(), region);
                player.sendMessage(Message.editmodeYes(region.getName()));
            }
            else {
                if(region == editRegion) {                  
                    regionManager.setEditRegion(player.getName(), null);
                    player.sendMessage(Message.editmodeNo(region.getName()));
                    FileManager.save(regionManager);
                } 
                else {
                    player.sendMessage(Message.editmodeNo(editRegion.getName()));
                    regionManager.setEditRegion(player.getName(), region);
                    player.sendMessage(Message.editmodeYes(region.getName()));
                }
            }
            
            return true;
        }
        
        region = new Region(args[0], player.getName());
        regionManager.add(region);
        player.sendMessage(Message.regionCreated(args[0]));
        
        Region editRegion = regionManager.getEditRegion(player.getName());
        if(editRegion != null)
            player.sendMessage(Message.editmodeNo(editRegion.getName()));
        
        regionManager.setEditRegion(player.getName(), region);
        player.sendMessage(Message.editmodeYes(args[0]));

        return true;
    }
    
}
