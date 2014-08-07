package puzzlepack;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TDist implements CommandExecutor {
        
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {        
        if (!(sender instanceof Player))
            return true;

        Player player = (Player)sender;
                
        if(args.length == 0) {
            player.sendMessage("INvalid args!");
            return true;
        }
        
        Integer percent = getInteger(args[0]);
        if(percent == null) {
            player.sendMessage("Invalid number!");
            return true;
        }
        
        PuzzlePack.percent = percent;
        player.sendMessage("Percent: " + percent + " set!");
        
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
