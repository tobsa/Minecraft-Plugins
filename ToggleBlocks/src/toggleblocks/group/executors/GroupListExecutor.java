package toggleblocks.group.executors;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import toggleblocks.Message;
import toggleblocks.Region;
import toggleblocks.group.Group;
import toggleblocks.group.GroupManager;

public class GroupListExecutor implements CommandExecutor {
    private GroupManager groupManager;
    
    public GroupListExecutor(GroupManager groupManager) {
        this.groupManager = groupManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player))
            return true;
        
        Player player = (Player)sender;
                
        if(args.length > 1) {
            player.sendMessage(Message.invalidArguments(command.getUsage()));
            return true;
        }
        
        if(args.length == 0) {
            player.sendMessage(Message.grouplistHeader());

            int count = 1;
            for(Group group : groupManager.getGroups())
                if(group.getPlayerName().equalsIgnoreCase(player.getName()))
                    player.sendMessage(Message.groupitemList(count++, group.getName(), group.get().size()));

            return true;
        } 
        
        Group group = groupManager.getGroup(args[0], player.getName());
        if(group == null) {
            player.sendMessage(Message.missingGroup(args[0]));
            return true;
        }
        
        player.sendMessage(Message.groupnameHeader(group.getName()));
        
        int count = 1;
        for(Region region: group.get())
            player.sendMessage(count++ + ". " + region.getName());
        
        return true;
    }
}
