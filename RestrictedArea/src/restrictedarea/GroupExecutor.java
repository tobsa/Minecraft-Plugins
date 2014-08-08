package restrictedarea;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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
                        
        Group group = groupManager.getGroup(player.getName(), args[0]);
        if(group != null) {
            player.sendMessage(Message.groupExists(args[0]));
            return true;
        }
        
        groupManager.addGroup(new Group(args[0], player.getName()));
        player.sendMessage(Message.groupCreated(args[0]));

        return true;
    }
}
