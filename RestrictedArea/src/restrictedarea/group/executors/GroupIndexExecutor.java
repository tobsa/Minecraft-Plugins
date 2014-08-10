package restrictedarea.group.executors;

import basepack.BasePack;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import puzzlepack.PuzzlePack;
import restrictedarea.FileManager;
import restrictedarea.Message;
import restrictedarea.group.Group;
import restrictedarea.group.GroupManager;

public class GroupIndexExecutor implements CommandExecutor {
    private GroupManager groupManager;
    
    public GroupIndexExecutor(GroupManager groupManager) {
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
        
        Integer index = BasePack.getInteger(args[1]);
        if(index == null) {
            player.sendMessage(Message.invalidNumber(args[1]));
            return true;
        }
        
        int size = groupManager.getGroups().size();
        if(index < 1 || index > size) {
            player.sendMessage(Message.invalidIndex(args[1], size));
            return true;
        }
        
        groupManager.setIndex(group, index - 1);
        FileManager.save(groupManager);
        player.sendMessage(Message.groupIndexUpdated(group.getName(), index));

        return true;
    }
}
