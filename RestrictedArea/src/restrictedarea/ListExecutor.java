package restrictedarea;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ListExecutor implements CommandExecutor {
    private AreaManager areaManager;
    
    public ListExecutor(AreaManager areaManager) {
        this.areaManager = areaManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player))
            return true;
        
        Player player = (Player)sender;
                
        if(args.length != 0) {
            player.sendMessage(Message.invalidArguments(command.getUsage()));
            return true;
        }
        
        player.sendMessage(Message.listHeader());
        
        int count = 1;
        for(Area area : areaManager.get())
            if(area.getPlayerName().equalsIgnoreCase(player.getName()))
                player.sendMessage(Message.displayArea(count++, area.getName(), area.getSubAreas().size()));

        return true;
    }
}
