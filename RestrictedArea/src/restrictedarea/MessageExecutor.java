package restrictedarea;

import basepack.BasePack;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MessageExecutor implements CommandExecutor {
    private AreaManager areaManager;
    
    public MessageExecutor(AreaManager areaManager) {
        this.areaManager = areaManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player))
            return true;
        
        Player player = (Player)sender;
                
        if(args.length <= 1) {
            player.sendMessage(Message.invalidArguments(command.getUsage()));
            return true;
        }
                        
        Area area = areaManager.get(args[0], player.getName());
        if(area == null) {
            player.sendMessage(Message.missingArea(args[0]));
            return true;
        }
              
        String message = BasePack.combineArguments(args, 1);
        
        area.setMessage(message);
        FileManager.save(areaManager);        
        player.sendMessage(Message.areaMessageSet(area.getName()));

        return true;
    }
}
