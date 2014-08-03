package area;

import areacollider.PlayerMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AreaDeleteExecutor implements CommandExecutor {
    private AreaManager areaManager;
    
    public AreaDeleteExecutor(AreaManager areaManager) {
        this.areaManager = areaManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player))
            return true;
        
        Player player = (Player)sender;
                
        if(args.length != 1) {
            player.sendMessage(PlayerMessage.invalidArguments(command.getUsage()));
            return true;
        }
        
        Area area = areaManager.getArea(player.getName(), args[0]);
        if(area == null) {
            player.sendMessage(PlayerMessage.missingArea(args[0]));
            return true;
        }
        
        areaManager.removeArea(area.getName());
        player.sendMessage(PlayerMessage.areaDeleted(args[0]));
       
        return true;
    }
}
