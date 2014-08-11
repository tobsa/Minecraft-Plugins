package forestertool;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DistanceOnExecutor implements CommandExecutor {
    private Tool tool;

    public DistanceOnExecutor(Tool tool) {
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
                
        tool.setDistanceMode(true);
        FileManager.save(tool);
        player.sendMessage(Message.distance(true));
        
        return true;
    }

}
