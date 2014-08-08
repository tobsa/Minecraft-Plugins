package restrictedarea.executors;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import puzzlepack.PuzzlePack;
import restrictedarea.Area;
import restrictedarea.AreaManager;
import restrictedarea.FileManager;
import restrictedarea.Message;

public class IndexExecutor implements CommandExecutor {
    private AreaManager areaManager;
    
    public IndexExecutor(AreaManager areaManager) {
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
        
        Integer index = PuzzlePack.getInteger(args[1]);
        if(index == null) {
            player.sendMessage(Message.invalidNumber(args[1]));
            return true;
        }
        
        int size = areaManager.get().size();
        if(index < 1 || index > size) {
            player.sendMessage(Message.invalidIndex(args[1], size));
            return true;
        }
        
        areaManager.setIndex(area, index - 1);
        FileManager.save(areaManager);
        player.sendMessage(Message.indexUpdated(area.getName(), index));

        return true;
    }
}
