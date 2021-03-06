package toggleblocks.group.executors;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import toggleblocks.FileManager;
import toggleblocks.Message;
import toggleblocks.group.Group;
import toggleblocks.group.GroupManager;

public class GroupDeleteExecutor implements CommandExecutor {
    private GroupManager groupManager;
    
    public GroupDeleteExecutor(GroupManager groupManager) {
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
        if(group == null) {
            player.sendMessage(Message.missingGroup(args[0]));
            return true;
        }
               
        groupManager.removeGroup(group);
        FileManager.save(groupManager);
        player.sendMessage(Message.groupRemoved(args[0]));
        
        return true;
    }
}
