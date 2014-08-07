package puzzlepack;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TSize implements CommandExecutor {
        
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {        
        if (!(sender instanceof Player))
            return true;

        Player player = (Player)sender;
                
        if(args.length > 2) {
            player.sendMessage("INvalid args!");
            return true;
        }
        
        Integer size = getInteger(args[0]);
        if(size == null) {
            player.sendMessage("Invalid size number!");
            return true;
        }
        
        if(args.length == 2) {
            Integer air = getInteger(args[1]);
            if(air == null) {
                player.sendMessage("Invalid air number!");
                return true;
            }
            
            PuzzlePack.air = air;
            player.sendMessage("Air: " + air + " set!");
        }
        
        PuzzlePack.size = size;
        player.sendMessage("Size: " + size + " set!");
        
        
        return true;
    }
    
    private Integer getInteger(String arg) {
        try {
            return Integer.valueOf(arg);
        } catch(NumberFormatException ex) {
            return null;
        }
    }
}
