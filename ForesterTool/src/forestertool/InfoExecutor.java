package forestertool;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class InfoExecutor implements CommandExecutor {
    private Tool tool;

    public InfoExecutor(Tool tool) {
        this.tool = tool;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player))
            return true;

        Player player = (Player) sender;
        
        if(args.length != 0) {
            player.sendMessage(Message.invalidArguments(command.getUsage()));
            return true;
        }
        
        player.sendMessage(Message.radius(tool.getRadius()));
        player.sendMessage(Message.leaves(tool.getLeaves()));
        player.sendMessage(Message.air(tool.getAir()));
        player.sendMessage(Message.replace(tool.getReplace()));
        player.sendMessage(Message.distance(tool.getDistanceMode()));
        
        return true;
    }

}
