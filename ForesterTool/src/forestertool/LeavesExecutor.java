package forestertool;

import basepack.BasePack;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LeavesExecutor implements CommandExecutor {
    private Tool tool;

    public LeavesExecutor(Tool tool) {
        this.tool = tool;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player))
            return true;

        Player player = (Player) sender;
        
        if(args.length != 1) {
            player.sendMessage(Message.invalidArguments(command.getUsage()));
            return true;
        }
        
        Integer leaves = BasePack.getInteger(args[0]);
        if(leaves == null) {
            player.sendMessage(Message.invalidNumber(args[0]));
            return true;
        }
        
        tool.setLeaves(leaves);
        FileManager.save(tool);
        player.sendMessage(Message.leaves(leaves));
        
        return true;
    }

}
