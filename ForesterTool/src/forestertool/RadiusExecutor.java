package forestertool;

import basepack.BaseMessage;
import basepack.BasePack;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RadiusExecutor implements CommandExecutor {
    private Tool tool;

    public RadiusExecutor(Tool tool) {
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
        
        Integer radius = BasePack.getInteger(args[0]);
        if(radius == null) {
            player.sendMessage(Message.invalidNumber(args[0]));
            return true;
        }
        
        tool.setRadius(radius);
        FileManager.save(tool);
        player.sendMessage(Message.radius(radius));
        
        return true;
    }

}
