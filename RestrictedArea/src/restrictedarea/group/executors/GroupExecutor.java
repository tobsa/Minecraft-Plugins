package restrictedarea.group.executors;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import restrictedarea.FileManager;
import restrictedarea.Message;
import restrictedarea.group.Group;
import restrictedarea.group.GroupManager;

public class GroupExecutor implements CommandExecutor {
    private GroupManager groupManager;
    
    public GroupExecutor(GroupManager groupManager) {
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
                        
        Group group = groupManager.getGroup(args[0], player.getName());
        if(group != null) {
            player.sendMessage(Message.groupExists(args[0]));
            return true;
        }
        
        groupManager.addGroup(new Group(args[0], player.getName()));
        FileManager.save(groupManager);
        player.sendMessage(Message.groupCreated(args[0]));

        return true;
    }
}
