package restrictedarea.group.executors;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import restrictedarea.Area;
import restrictedarea.AreaManager;
import restrictedarea.FileManager;
import restrictedarea.Message;
import restrictedarea.group.Group;
import restrictedarea.group.GroupManager;

public class GroupExcludeExecutor implements CommandExecutor {
    private AreaManager areaManager;
    private GroupManager groupManager;
    
    public GroupExcludeExecutor(AreaManager areaManager, GroupManager groupManager) {
        this.areaManager = areaManager;
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
                        
        Area area = areaManager.get(args[0], player.getName());
        if(area == null) {
            player.sendMessage(Message.missingArea(args[0]));
            return true;
        }
        
        Group group = groupManager.getGroup(args[1], player.getName());
        if(group == null) {
            player.sendMessage(Message.missingGroup(args[1]));
            return true;
        }
               
        if(!group.contains(area)) {
            player.sendMessage(Message.areaMissingInGroup(area.getName(), group.getName()));
            return true;
        }
        
        group.remove(area);
        FileManager.save(groupManager);
        player.sendMessage(Message.areaRemovedFromGroup(area.getName(), group.getName()));
        
        return true;
    }
}
