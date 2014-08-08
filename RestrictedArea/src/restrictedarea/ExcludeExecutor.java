package restrictedarea;

import basepack.BasePack;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ExcludeExecutor implements CommandExecutor {
    private AreaManager areaManager;
    
    public ExcludeExecutor(AreaManager areaManager) {
        this.areaManager = areaManager;
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
                        
        Area area = areaManager.get(args[0], player.getName());
        if(area == null) {
            player.sendMessage(Message.missingArea(args[0]));
            return true;
        }
              
        Integer index = BasePack.getInteger(args[1]);
        if(index == null) {
            player.sendMessage(Message.invalidNumber(args[1]));
            return true;
        }
        
        if(index < 0 || index >= area.getSubAreas().size()) {
            player.sendMessage(Message.invalidIndex(args[1], area.getSubAreas().size()));
            return true;
        }
        
        area.removeSubArea(index);
        FileManager.save(areaManager);        
        player.sendMessage(Message.subareaRemoved(index, args[0]));

        return true;
    }
}
