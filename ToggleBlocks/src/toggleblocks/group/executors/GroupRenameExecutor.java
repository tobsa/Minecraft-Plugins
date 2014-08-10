package toggleblocks.group.executors;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import toggleblocks.FileManager;
import toggleblocks.Message;
import toggleblocks.group.Group;
import toggleblocks.group.GroupManager;

public class GroupRenameExecutor implements CommandExecutor {
    private GroupManager groupManager;
    
    public GroupRenameExecutor(GroupManager groupManager) {
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
                                
        Group group = groupManager.getGroup(args[0], player.getName());
        if(group == null) {
            player.sendMessage(Message.missingGroup(args[0]));
            return true;
        }
        
        if(groupManager.getGroup(args[1], player.getName()) != null) {
            player.sendMessage(Message.groupExists(args[1]));
            return true;
        }
               
        groupManager.renameGroup(group, args[1]);
        FileManager.save(groupManager);
        player.sendMessage(Message.groupRenamed(args[0], args[1]));
        
        return true;
    }
}
