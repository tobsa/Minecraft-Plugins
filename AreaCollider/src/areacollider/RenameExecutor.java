package areacollider;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RenameExecutor implements CommandExecutor {
    private AreaManager areaManager;
    
    public RenameExecutor(AreaManager areaManager) {
        this.areaManager = areaManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player))
            return true;
        
        Player player = (Player)sender;
                
        if(args.length != 2) {
            player.sendMessage(PlayerMessage.invalidArguments(command.getUsage()));
            return true;
        }
        
        Area area = areaManager.getArea(player.getName(), args[0]);
        if(area == null) {
            player.sendMessage(PlayerMessage.missingArea(args[0]));
            return true;
        }
        
        if(areaManager.getArea(player.getName(), args[1]) != null) {
            player.sendMessage(PlayerMessage.areaExists(args[1]));
            return true;
        }
        
        areaManager.renameArea(area, args[1]);
        FileManager.save(areaManager);
        player.sendMessage(PlayerMessage.areaRenamed(area.getName(), args[1]));
        
        return true;
    }
}
