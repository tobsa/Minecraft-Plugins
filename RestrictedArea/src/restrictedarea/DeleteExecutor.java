package restrictedarea;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import restrictedarea.group.GroupManager;

public class DeleteExecutor implements CommandExecutor {
    private AreaManager areaManager;
    private GroupManager groupManager;
    
    public DeleteExecutor(AreaManager areaManager, GroupManager groupManager) {
        this.areaManager = areaManager;
        this.groupManager = groupManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player))
            return true;
        
        Player player = (Player)sender;
                
        if(args.length != 1) {
            player.sendMessage(Message.invalidArguments(command.getUsage()));
            return true;
        }
                        
        Area area = areaManager.get(args[0], player.getName());
        if(area == null) {
            player.sendMessage(Message.missingArea(args[0]));
            return true;
        }
        
        groupManager.removeItemFromGroups(area, player.getName());  
        areaManager.remove(area.getName());
        
        FileManager.save(areaManager);
        FileManager.save(groupManager);
        player.sendMessage(Message.areaRemoved(args[0]));

        return true;
    }
}
