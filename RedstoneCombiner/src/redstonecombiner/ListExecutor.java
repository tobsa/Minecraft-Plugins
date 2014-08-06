package redstonecombiner;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ListExecutor implements CommandExecutor {
    private CombinerManager combinerManager;
    
    public ListExecutor(CombinerManager combinerManager) {
        this.combinerManager = combinerManager;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player))
            return true;
        
        Player player = (Player)sender;
        
        if(args.length != 0) {
            player.sendMessage(PlayerMessage.invalidArguments(command.getUsage()));
            return true;
        }
        
        player.sendMessage(PlayerMessage.headerList());
        int number = 1;
        for(Combiner combiner : combinerManager.getCombiners()) {
            if(combiner.getPlayerName().equalsIgnoreCase(player.getName()))
                player.sendMessage(number++ + ". " + combiner.getName());
        }
                
        return true;
    }
    
}
