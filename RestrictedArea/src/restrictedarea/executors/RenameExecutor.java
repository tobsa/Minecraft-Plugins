package restrictedarea.executors;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import restrictedarea.Area;
import restrictedarea.AreaManager;
import restrictedarea.FileManager;
import restrictedarea.Message;
import restrictedarea.group.GroupManager;

public class RenameExecutor implements CommandExecutor {
    private AreaManager areaManager;
    private GroupManager groupManager;
    
    public RenameExecutor(AreaManager areaManager, GroupManager groupManager) {
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
        
        if(areaManager.get(args[1]) != null) {
            player.sendMessage(Message.areaExists(args[1]));
            return true;
        }
        
        areaManager.renameItem(area, args[1]);
        groupManager.renameAreaInGroups(player.getName(), args[0], args[1]);
        
        FileManager.save(areaManager);
        FileManager.save(groupManager);
        
        player.sendMessage(Message.areaRenamed(args[0], args[1]));

        return true;
    }
}
