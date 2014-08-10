package toggleblocks.group.executors;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import toggleblocks.FileManager;
import toggleblocks.Message;
import toggleblocks.Region;
import toggleblocks.RegionManager;
import toggleblocks.group.Group;
import toggleblocks.group.GroupManager;

public class GroupIncludeExecutor implements CommandExecutor {
    private RegionManager regionManager;
    private GroupManager groupManager;
    
    public GroupIncludeExecutor(RegionManager regionManager, GroupManager groupManager) {
        this.regionManager = regionManager;
        this.groupManager = groupManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player))
            return true;
        
        Player player = (Player)sender;
                
        if(args.length != 2) {
            player.sendMessage(Message.invalidArguments(command.getUsage()));
            return true;
        }
                        
        Region region = regionManager.get(args[0], player.getName());
        if(region == null) {
            player.sendMessage(Message.missingRegion(args[0]));
            return true;
        }
        
        Group group = groupManager.getGroup(args[1], player.getName());
        if(group == null) {
            player.sendMessage(Message.missingGroup(args[1]));
            return true;
        }
               
        if(group.contains(region)) {
            player.sendMessage(Message.regionExistsInGroup(region.getName(), group.getName()));
            return true;
        }
        
        group.add(region);
        FileManager.save(groupManager);
        player.sendMessage(Message.regionPlacedInGroup(region.getName(), group.getName()));
        
        return true;
    }
}
