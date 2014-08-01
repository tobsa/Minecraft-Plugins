package area;

import areacollider.PlayerMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AreaListExecutor implements CommandExecutor {
    private AreaManager areaManager;
    
    public AreaListExecutor(AreaManager areaManager) {
        this.areaManager = areaManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player))
            return true;
        
        Player player = (Player)sender;
                
        if(args.length != 0) {
            player.sendMessage(PlayerMessage.getInvalidArguments(command.getUsage()));
            return true;
        }
        
        player.sendMessage(PlayerMessage.getListHeader());
        
        int number = 1;
        for(Area area : areaManager.getAreas())
            if(area.getPlayerName().equalsIgnoreCase(player.getName()))
                player.sendMessage((number++) + ". " + area.getName());
       
        return true;
    }
}