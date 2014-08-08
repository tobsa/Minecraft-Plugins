package restrictedarea.executors;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import restrictedarea.Area;
import restrictedarea.AreaManager;
import restrictedarea.FileManager;
import restrictedarea.Message;

public class LocationExecutor implements CommandExecutor {
    private AreaManager areaManager;
    
    public LocationExecutor(AreaManager areaManager) {
        this.areaManager = areaManager;
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
                        
        Area area = areaManager.get(args[0], player.getName());
        if(area == null) {
            player.sendMessage(Message.missingArea(args[0]));
            return true;
        }
        
        area.setLocation(player.getLocation());
        FileManager.save(areaManager);
        player.sendMessage(Message.areaLocationUpdated(args[0]));

        return true;
    }
}
